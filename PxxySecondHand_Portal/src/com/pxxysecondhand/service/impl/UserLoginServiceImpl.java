/**   
* @Title: UserLoginServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年10月28日 下午5:27:10 
* @version V1.0   
*/
package com.pxxysecondhand.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.pxxysecondhand.component.JedisClient;
import com.pxxysecondhand.mapper.UserMapper;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.pojo.UserExample;
import com.pxxysecondhand.pojo.UserExample.Criteria;
import com.pxxysecondhand.service.IItemService;
import com.pxxysecondhand.service.IUserLoginService;
import com.pxxysecondhand.service.IUserRegistService;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CookieUtils;
import com.pxxysecondhand.utils.JsonUtils;

/**
 * @author  
 *
 */
@Service
public class UserLoginServiceImpl implements IUserLoginService {
	@Autowired
	private IUserRegistService userRegistService;
	
	@Autowired
	private IItemService itemService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	//TOKEN过期时间
	@Value("${LOGIN_TOKEN_TIME_OUT}")
	private int LOGIN_TOKEN_TIME_OUT;
	
	//登录验证码Timer计时器前缀
	@Value("${SMS_TIMER_LOGIN_PREFIX}")
	private String SMS_TIMER_LOGIN_PREFIX;
	
	//Redis数据库中登录验证码缓存前缀名
	@Value("${SMS_LOGIN_CODE_PREFIX}")
	private String SMS_LOGIN_CODE_PREFIX;
	
	//#登录成功后存储用户个人资料前缀
	@Value("${USER_ONLINE_TOKEN_PREFIX}")
	private String USER_ONLINE_TOKEN_PREFIX;
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IUserLoginService#doLoginByPass(com.pxxysecondhand.pojo.User, java.lang.String)
	 */
	@Override
	public String doLoginByPass(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		//1.向数据库中查询用户
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userMapper.selectByExample(userExample);
			//1.1不存在该用户
			if(list==null||list.size()==0) {
				return null;
			}
		//2.获取该用户,对比密码
		 User findUser = list.get(0);
		 	//2.1密码不匹配
		 if(!findUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
			 return null;
		 }
		 //获取TOKEN符
		 String Token = DigestUtils.md5DigestAsHex((System.currentTimeMillis()+username).getBytes());
		 
		 //插入redis数据库
		 jedisClient.set(USER_ONLINE_TOKEN_PREFIX+Token,JsonUtils.objectToJson(findUser));
		 //设置该记录的过期时间
		 jedisClient.expire(USER_ONLINE_TOKEN_PREFIX+Token, LOGIN_TOKEN_TIME_OUT);
		return Token;
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IUserLoginService#validateVerifyCode(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean validateVerifyCode(HttpServletRequest request,String verifyCode) {
		// TODO Auto-generated method stub
		String vCode=(String) request.getSession().getAttribute("vCode");
		if(verifyCode==null||verifyCode==""||vCode==null)
			return false;
		if(verifyCode.equalsIgnoreCase(vCode)) {
			return true;
		}else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IUserLoginService#queryByToken(java.lang.String)
	 */
	@Override
	public User queryByToken(HttpServletRequest request,HttpServletResponse response , String token) throws Exception {
		//创建游客token 
		itemService.createGuessTokenIfNotExist(request, response);
		//查询当前用户
		String key = USER_ONLINE_TOKEN_PREFIX+token;
		String jsonData = jedisClient.get(key);
		if(jsonData==null||jsonData.equals("")) {
			return null;
		}
		jedisClient.expire(key, LOGIN_TOKEN_TIME_OUT);
		return JsonUtils.jsonToPojo(jsonData, User.class);
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IUserLoginService#getSmsLoginMessage(java.lang.String, java.lang.String)
	 */
	@Override
	public CommonResult getSmsLoginMessage(String phonenumber, String username) throws Exception {
		//1.判断手机号码格式是否正确
		userRegistService.validatePhonenumberFormat(phonenumber);
		//2.判断手机号码是否注册  没注册则抛出异常
		String bool = userRegistService.validatePhonenumber(phonenumber);
		if(bool.equals("0")) {
			throw new Exception("您还未注册，请先注册");
		}
		//3.发送验证码
		return userRegistService.getSmsMessage(username, phonenumber, SMS_TIMER_LOGIN_PREFIX, SMS_LOGIN_CODE_PREFIX);
	
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IUserLoginService#doLoginBySmscode(java.lang.String, java.lang.String)
	 */
	@Override
	public String doLoginBySmscode(String phonenumber,String smsCode) throws Exception {
		// TODO Auto-generated method stub
		//1.判断手机格式 
		userRegistService.validatePhonenumberFormat(phonenumber);
		//2.通过手机号码查询redis数据库 对比验证码
		String realCode = jedisClient.get(SMS_LOGIN_CODE_PREFIX+phonenumber);
		if(realCode==null||(!realCode.equals(smsCode))) {
			return null;
		}
		//3.验证成功生成唯一标识符TOKEN设置超时时间存入redi数据库中
			//3.1.向数据库中查询用户
				UserExample userExample = new UserExample();
				Criteria criteria = userExample.createCriteria();
				criteria.andPhonenumberEqualTo(phonenumber);
				List<User> list = userMapper.selectByExample(userExample);
				if(list==null||list.size()==0)
					return null;
				User findUser = list.get(0);
			 //3.2获取TOKEN符
		 String Token = DigestUtils.md5DigestAsHex((System.currentTimeMillis()+phonenumber).getBytes());
		 
		    //3.3插入redis数据库
		 jedisClient.set(USER_ONLINE_TOKEN_PREFIX+Token,JsonUtils.objectToJson(findUser));
		 //3.4设置该记录的过期时间
		 jedisClient.expire(USER_ONLINE_TOKEN_PREFIX+Token, LOGIN_TOKEN_TIME_OUT);
		return Token;
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IUserLoginService#userQuit(java.lang.String, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public CommonResult userQuit(String Token,HttpServletRequest request, HttpServletResponse response) throws Exception {
		//清楚redis中登录记录
		jedisClient.expire(USER_ONLINE_TOKEN_PREFIX+Token, 0);
		//清楚浏览器中的Login_Token
		CookieUtils.deleteCookie(request, response, "Login_Token");
		return CommonResult.ok();
	}

}
