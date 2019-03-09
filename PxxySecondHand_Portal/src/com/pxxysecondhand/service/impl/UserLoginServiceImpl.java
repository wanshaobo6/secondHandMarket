/**   
* @Title: UserLoginServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��10��28�� ����5:27:10 
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
	
	//TOKEN����ʱ��
	@Value("${LOGIN_TOKEN_TIME_OUT}")
	private int LOGIN_TOKEN_TIME_OUT;
	
	//��¼��֤��Timer��ʱ��ǰ׺
	@Value("${SMS_TIMER_LOGIN_PREFIX}")
	private String SMS_TIMER_LOGIN_PREFIX;
	
	//Redis���ݿ��е�¼��֤�뻺��ǰ׺��
	@Value("${SMS_LOGIN_CODE_PREFIX}")
	private String SMS_LOGIN_CODE_PREFIX;
	
	//#��¼�ɹ���洢�û���������ǰ׺
	@Value("${USER_ONLINE_TOKEN_PREFIX}")
	private String USER_ONLINE_TOKEN_PREFIX;
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IUserLoginService#doLoginByPass(com.pxxysecondhand.pojo.User, java.lang.String)
	 */
	@Override
	public String doLoginByPass(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		//1.�����ݿ��в�ѯ�û�
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userMapper.selectByExample(userExample);
			//1.1�����ڸ��û�
			if(list==null||list.size()==0) {
				return null;
			}
		//2.��ȡ���û�,�Ա�����
		 User findUser = list.get(0);
		 	//2.1���벻ƥ��
		 if(!findUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
			 return null;
		 }
		 //��ȡTOKEN��
		 String Token = DigestUtils.md5DigestAsHex((System.currentTimeMillis()+username).getBytes());
		 
		 //����redis���ݿ�
		 jedisClient.set(USER_ONLINE_TOKEN_PREFIX+Token,JsonUtils.objectToJson(findUser));
		 //���øü�¼�Ĺ���ʱ��
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
		//�����ο�token 
		itemService.createGuessTokenIfNotExist(request, response);
		//��ѯ��ǰ�û�
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
		//1.�ж��ֻ������ʽ�Ƿ���ȷ
		userRegistService.validatePhonenumberFormat(phonenumber);
		//2.�ж��ֻ������Ƿ�ע��  ûע�����׳��쳣
		String bool = userRegistService.validatePhonenumber(phonenumber);
		if(bool.equals("0")) {
			throw new Exception("����δע�ᣬ����ע��");
		}
		//3.������֤��
		return userRegistService.getSmsMessage(username, phonenumber, SMS_TIMER_LOGIN_PREFIX, SMS_LOGIN_CODE_PREFIX);
	
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IUserLoginService#doLoginBySmscode(java.lang.String, java.lang.String)
	 */
	@Override
	public String doLoginBySmscode(String phonenumber,String smsCode) throws Exception {
		// TODO Auto-generated method stub
		//1.�ж��ֻ���ʽ 
		userRegistService.validatePhonenumberFormat(phonenumber);
		//2.ͨ���ֻ������ѯredis���ݿ� �Ա���֤��
		String realCode = jedisClient.get(SMS_LOGIN_CODE_PREFIX+phonenumber);
		if(realCode==null||(!realCode.equals(smsCode))) {
			return null;
		}
		//3.��֤�ɹ�����Ψһ��ʶ��TOKEN���ó�ʱʱ�����redi���ݿ���
			//3.1.�����ݿ��в�ѯ�û�
				UserExample userExample = new UserExample();
				Criteria criteria = userExample.createCriteria();
				criteria.andPhonenumberEqualTo(phonenumber);
				List<User> list = userMapper.selectByExample(userExample);
				if(list==null||list.size()==0)
					return null;
				User findUser = list.get(0);
			 //3.2��ȡTOKEN��
		 String Token = DigestUtils.md5DigestAsHex((System.currentTimeMillis()+phonenumber).getBytes());
		 
		    //3.3����redis���ݿ�
		 jedisClient.set(USER_ONLINE_TOKEN_PREFIX+Token,JsonUtils.objectToJson(findUser));
		 //3.4���øü�¼�Ĺ���ʱ��
		 jedisClient.expire(USER_ONLINE_TOKEN_PREFIX+Token, LOGIN_TOKEN_TIME_OUT);
		return Token;
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IUserLoginService#userQuit(java.lang.String, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public CommonResult userQuit(String Token,HttpServletRequest request, HttpServletResponse response) throws Exception {
		//���redis�е�¼��¼
		jedisClient.expire(USER_ONLINE_TOKEN_PREFIX+Token, 0);
		//���������е�Login_Token
		CookieUtils.deleteCookie(request, response, "Login_Token");
		return CommonResult.ok();
	}

}
