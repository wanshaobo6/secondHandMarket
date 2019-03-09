package com.pxxysecondhand.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.pxxysecondhand.component.JedisClient;
import com.pxxysecondhand.component.SMSCodeTimer;
import com.pxxysecondhand.mapper.UserMapper;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.pojo.UserExample;
import com.pxxysecondhand.pojo.UserExample.Criteria;
import com.pxxysecondhand.service.IUserRegistService;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CommonUtils;
import com.pxxysecondhand.utils.SMSCodeGenerator;
import com.pxxysecondhand.utils.SMSCodeUtil;

@Service
public class UserRegistServiceImpl implements IUserRegistService{
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SMSCodeUtil smsCodeUtil;
	
	@Autowired
	private SMSCodeTimer smsCodeTimer;
	
	@Autowired
	private JedisClient jedisClient;
	
	//手机号码正则表达式
	@Value("${REGEXP_PHONENUMBER}")
	private String REGEXP_PHONENUMBER;
	
	//Redis数据库中注册验证码缓存前缀名
	@Value("${SMS_REGIST_CODE_PREFIX}")
	private String SMS_REGIST_CODE_PREFIX;
	
	//验证码超时消失时间 单位S
	@Value("${SMS_CODE_TIMEOUT}")
	private int SMS_CODE_TIMEOUT;
	
	//注册验证码Timer计时器前缀
	@Value("${SMS_TIMER_REGIST_PREFIX}")
	private String SMS_TIMER_REGIST_PREFIX;
	/**
	 * 验证手机号是否存在
	 * @author 
	 */
	public String validatePhonenumber(String phonenumber) {
		// TODO Auto-generated method stub
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andPhonenumberEqualTo(phonenumber);
		List<User> list = userMapper.selectByExample(userExample);
		if(list!=null&&list.size()==1) {
			return "1";
		}
		return "0";
	}
	
	
	public void validatePhonenumberFormat(String phonenumber) throws Exception{
		//判断空
		if(phonenumber!=null||(!phonenumber.equals(""))) {
			//判断格式
			 
			Pattern p = Pattern.compile(REGEXP_PHONENUMBER);
			Matcher m = p.matcher(phonenumber);
			if(m.find()) {
				return;
			}else {
				throw new Exception("手机号码格式不正确!");
			}
		}else {
			throw new Exception("手机号码不能为空!");
		}
	}
	

	/* 指定验证码类型
	 * @see com.pxxysecondhand.service.IUserRegistService#getSmsRegistMessage(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public CommonResult getSmsRegistMessage(String name, String phonenumber) throws Exception {
		//判断用户手机号格式
		validatePhonenumberFormat(phonenumber);
		String s = validatePhonenumber(phonenumber);
		//判断是否已经注册
		if(s.equals("1")) {
			throw new Exception("用户已经注册");
		}
		//未注册 发送验证码
		return getSmsMessage(name,phonenumber,SMS_TIMER_REGIST_PREFIX,SMS_REGIST_CODE_PREFIX);
	}
	/**
	 * 获取所有类型的手机验证码  smsTimerPrefix是SmSCodeTimer的键前缀
	 * 							smsRedisPrefix是redis的键前缀
	 */
	public CommonResult getSmsMessage(String name, String phonenumber,String smsTimerPrefix,String smsRedisPrefix) throws Exception {
		// TODO Auto-generated method stub
		boolean canIGetSmsCode = smsCodeTimer.canIGetSmsCode(smsTimerPrefix+phonenumber);
		//1.查看用户是否满足接受验证码的要求
		if(canIGetSmsCode) {
			//2.如果满足生成验证码 发送验证码 记录发送时间 保存验证码
			String code = SMSCodeGenerator.generateSmsCode();
			smsCodeUtil.sendSmsMessage(phonenumber, code);
			smsCodeTimer.registSendSmsCode(smsTimerPrefix+phonenumber);
			jedisClient.set(smsRedisPrefix+phonenumber, code);
			jedisClient.expire(smsRedisPrefix+phonenumber, SMS_CODE_TIMEOUT);
			return CommonResult.ok();
		}else {
			return new CommonResult().build(500,"两次发送间隔时间太短");
		}
		

	}
	/**
	 * 验证用户名是否存在
	 */
	@Override
	public String validateUserName(String username) {
		// TODO Auto-generated method stub
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userMapper.selectByExample(userExample);
		if(list!=null&&list.size()==1) {
			return "1";
		}
		return "0";
	}

	//用户名为2-6位
	@Override
	public void validateUserNameFormat(String username) throws Exception {
		// TODO Auto-generated method stub
		//判断空
		if(username!=null) {
			//判断格式
			 if(username.length()<2||username.length()>7) {
				 throw new Exception("用户名长度为2-7");
			 }
		}else {
			throw new Exception("手机号码不能为空!");
		}
		
	}
	/**
	 * 添加用户
	 */
	@Override
	public Map addUser(User user,String registCode) throws Exception {
		// 1.重新验证用户数据格式的正确性
		 Map errorMap = putUserInfoErrorIntoMap(user);
		// 2.验证验证码的正确性
		String correctCode = jedisClient.get(SMS_REGIST_CODE_PREFIX+user.getPhonenumber());
		if(correctCode==null|correctCode.equals("")) {
			errorMap.put("smsCodeError", "验证码不能为空");
		}else if(!registCode.equals(correctCode)) {
			errorMap.put("smsCodeError", "验证码错误!");
		}
		if(errorMap.size()>0)
			return errorMap;
		// 3.密码加密填入
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		// 4.补充完整数据
		Date date = new Date();
		String uuid = CommonUtils.uuidGenerator();
		user.setId(uuid);
		user.setCredit(0);
		user.setCreated(date);
		user.setUpdated(date);
		// 5.添加数据库
		userMapper.insert(user);
		return null;
	}
	/**
	 * 验证用户密码格式
	 */
	public void validatePassword(String password) throws Exception {
		if(password==null||password=="") {
			throw new Exception("密码不能为空");
		}else if(password.length()>20||password.length()<5) {
			throw new Exception("密码的长度必须在5-20之间");
		}else if(password.matches("\\d+")) {
			throw new Exception("密码不能全为数字");
		}
	}
	/**
	 * 将用户提交的数据错误信息放入一个Map中
	 */
	public Map putUserInfoErrorIntoMap(User user) {
		Map<String,String> map = new HashMap();
		//验证用户名正确性
		try {
			validateUserNameFormat(user.getUsername());
		} catch (Exception e) {
			map.put("usernameError", e.getMessage());
		}
		//验证手机号码的正确性
		try {
			validatePhonenumber(user.getPhonenumber());
		}catch(Exception e) {
			map.put("phonenumberError",e.getMessage());
		}
		//验证密码的正确性
		try {
			validatePassword(user.getPassword());
		}catch(Exception e) {
			map.put("passwordError",e.getMessage());
		}
		return map;
	}


	

}
