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
	
	//�ֻ�����������ʽ
	@Value("${REGEXP_PHONENUMBER}")
	private String REGEXP_PHONENUMBER;
	
	//Redis���ݿ���ע����֤�뻺��ǰ׺��
	@Value("${SMS_REGIST_CODE_PREFIX}")
	private String SMS_REGIST_CODE_PREFIX;
	
	//��֤�볬ʱ��ʧʱ�� ��λS
	@Value("${SMS_CODE_TIMEOUT}")
	private int SMS_CODE_TIMEOUT;
	
	//ע����֤��Timer��ʱ��ǰ׺
	@Value("${SMS_TIMER_REGIST_PREFIX}")
	private String SMS_TIMER_REGIST_PREFIX;
	/**
	 * ��֤�ֻ����Ƿ����
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
		//�жϿ�
		if(phonenumber!=null||(!phonenumber.equals(""))) {
			//�жϸ�ʽ
			 
			Pattern p = Pattern.compile(REGEXP_PHONENUMBER);
			Matcher m = p.matcher(phonenumber);
			if(m.find()) {
				return;
			}else {
				throw new Exception("�ֻ������ʽ����ȷ!");
			}
		}else {
			throw new Exception("�ֻ����벻��Ϊ��!");
		}
	}
	

	/* ָ����֤������
	 * @see com.pxxysecondhand.service.IUserRegistService#getSmsRegistMessage(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public CommonResult getSmsRegistMessage(String name, String phonenumber) throws Exception {
		//�ж��û��ֻ��Ÿ�ʽ
		validatePhonenumberFormat(phonenumber);
		String s = validatePhonenumber(phonenumber);
		//�ж��Ƿ��Ѿ�ע��
		if(s.equals("1")) {
			throw new Exception("�û��Ѿ�ע��");
		}
		//δע�� ������֤��
		return getSmsMessage(name,phonenumber,SMS_TIMER_REGIST_PREFIX,SMS_REGIST_CODE_PREFIX);
	}
	/**
	 * ��ȡ�������͵��ֻ���֤��  smsTimerPrefix��SmSCodeTimer�ļ�ǰ׺
	 * 							smsRedisPrefix��redis�ļ�ǰ׺
	 */
	public CommonResult getSmsMessage(String name, String phonenumber,String smsTimerPrefix,String smsRedisPrefix) throws Exception {
		// TODO Auto-generated method stub
		boolean canIGetSmsCode = smsCodeTimer.canIGetSmsCode(smsTimerPrefix+phonenumber);
		//1.�鿴�û��Ƿ����������֤���Ҫ��
		if(canIGetSmsCode) {
			//2.�������������֤�� ������֤�� ��¼����ʱ�� ������֤��
			String code = SMSCodeGenerator.generateSmsCode();
			smsCodeUtil.sendSmsMessage(phonenumber, code);
			smsCodeTimer.registSendSmsCode(smsTimerPrefix+phonenumber);
			jedisClient.set(smsRedisPrefix+phonenumber, code);
			jedisClient.expire(smsRedisPrefix+phonenumber, SMS_CODE_TIMEOUT);
			return CommonResult.ok();
		}else {
			return new CommonResult().build(500,"���η��ͼ��ʱ��̫��");
		}
		

	}
	/**
	 * ��֤�û����Ƿ����
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

	//�û���Ϊ2-6λ
	@Override
	public void validateUserNameFormat(String username) throws Exception {
		// TODO Auto-generated method stub
		//�жϿ�
		if(username!=null) {
			//�жϸ�ʽ
			 if(username.length()<2||username.length()>7) {
				 throw new Exception("�û�������Ϊ2-7");
			 }
		}else {
			throw new Exception("�ֻ����벻��Ϊ��!");
		}
		
	}
	/**
	 * ����û�
	 */
	@Override
	public Map addUser(User user,String registCode) throws Exception {
		// 1.������֤�û����ݸ�ʽ����ȷ��
		 Map errorMap = putUserInfoErrorIntoMap(user);
		// 2.��֤��֤�����ȷ��
		String correctCode = jedisClient.get(SMS_REGIST_CODE_PREFIX+user.getPhonenumber());
		if(correctCode==null|correctCode.equals("")) {
			errorMap.put("smsCodeError", "��֤�벻��Ϊ��");
		}else if(!registCode.equals(correctCode)) {
			errorMap.put("smsCodeError", "��֤�����!");
		}
		if(errorMap.size()>0)
			return errorMap;
		// 3.�����������
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		// 4.������������
		Date date = new Date();
		String uuid = CommonUtils.uuidGenerator();
		user.setId(uuid);
		user.setCredit(0);
		user.setCreated(date);
		user.setUpdated(date);
		// 5.������ݿ�
		userMapper.insert(user);
		return null;
	}
	/**
	 * ��֤�û������ʽ
	 */
	public void validatePassword(String password) throws Exception {
		if(password==null||password=="") {
			throw new Exception("���벻��Ϊ��");
		}else if(password.length()>20||password.length()<5) {
			throw new Exception("����ĳ��ȱ�����5-20֮��");
		}else if(password.matches("\\d+")) {
			throw new Exception("���벻��ȫΪ����");
		}
	}
	/**
	 * ���û��ύ�����ݴ�����Ϣ����һ��Map��
	 */
	public Map putUserInfoErrorIntoMap(User user) {
		Map<String,String> map = new HashMap();
		//��֤�û�����ȷ��
		try {
			validateUserNameFormat(user.getUsername());
		} catch (Exception e) {
			map.put("usernameError", e.getMessage());
		}
		//��֤�ֻ��������ȷ��
		try {
			validatePhonenumber(user.getPhonenumber());
		}catch(Exception e) {
			map.put("phonenumberError",e.getMessage());
		}
		//��֤�������ȷ��
		try {
			validatePassword(user.getPassword());
		}catch(Exception e) {
			map.put("passwordError",e.getMessage());
		}
		return map;
	}


	

}
