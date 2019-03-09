/**   
* @Title: IUserLoginService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO用户登录模块接口
* @author  

* @date 2018年10月28日 下午5:25:40 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
public interface IUserLoginService {
	
	public String doLoginByPass(String username,String password) throws Exception;

	public String doLoginBySmscode(String phonenumber,String smsCode) throws Exception;
	
	public boolean validateVerifyCode(HttpServletRequest request,String verifyCode);
	
	public User queryByToken(HttpServletRequest request,HttpServletResponse response , String token)throws Exception;
	
	public CommonResult getSmsLoginMessage(String phonenumber, String username)throws Exception;
	
	public CommonResult userQuit(String Token,HttpServletRequest request,HttpServletResponse response)  throws Exception;
}
