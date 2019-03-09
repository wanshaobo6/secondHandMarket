/**   
* @Title: IUserLoginService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO用户注册模块接口
* @author  

* @date 2018年10月28日 下午5:25:40 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.utils.CommonResult;

public interface IUserRegistService {
	public String validatePhonenumber(String phonenumber);
	
	public void validatePhonenumberFormat(String phonenumber)throws Exception;
	
	public String validateUserName(String username);
	
	public void validateUserNameFormat(String phonenumber) throws Exception;
	
	public CommonResult getSmsRegistMessage(String name,String phonenumber)throws Exception;
	
	public CommonResult getSmsMessage(String name, String phonenumber,String smsTimerPrefix,String smsRedisPrefix) throws Exception ;
	
	public void validatePassword(String password) throws Exception;
	
	public Map addUser(User user ,String registCode)throws Exception;
	
	public Map putUserInfoErrorIntoMap(User user);
	
	
}
