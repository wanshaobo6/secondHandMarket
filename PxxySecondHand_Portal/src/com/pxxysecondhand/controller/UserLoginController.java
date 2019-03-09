/**   
* @Title: UserLoginController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��10��28�� ����5:25:09 
* @version V1.0   
*/
package com.pxxysecondhand.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.IUserLoginService;
import com.pxxysecondhand.service.IUserRegistService;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CookieUtils;
import com.pxxysecondhand.utils.JsonUtils;

/**
 * @author  
 *
 */
@Controller
@RequestMapping("/login")
public class UserLoginController {
	@Autowired
	private IUserLoginService userLoginService;
	
	
/**
 * Oͨ���û��������¼
* @Title: UserLoginController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TOD
* @author  
* @date 2018��10��29�� ����2:41:29 
* @version V1.0
 */
  @RequestMapping(value="/doLoginByPass",method=RequestMethod.POST)
  @ResponseBody
  public CommonResult doLoginByPass(HttpServletRequest request,HttpServletResponse response,User user,String verifyCode) {
	  try {
		  if(!userLoginService.validateVerifyCode(request, verifyCode)) {
			  return CommonResult.build(400, "��֤�벻��ȷ",user);
		  }
		  String token = userLoginService.doLoginByPass(user.getUsername(), user.getPassword());
		  if(token==null) {
			return CommonResult.build(401, "�û������������",user);
		  }
		  CookieUtils.setCookie(request, response, "Login_Token", token);
		  return CommonResult.ok();
	  }catch(Exception e) {
		  e.printStackTrace();
		  return CommonResult.build(500, "�������쳣");
	  }
  }
  
  /**
   * ͨ���ֻ���֤���¼
  * @Title: UserLoginController.java 
  * @Package com.pxxysecondhand.controller 
  * @Description: TODO(��һ�仰�������ļ���ʲô) 
  * @author  
  * @date 2018��10��30�� ����2:10:16 
  * @version V1.0
   */
  @RequestMapping(value="/doLoginBySmscode",method=RequestMethod.POST)
  @ResponseBody
  public CommonResult doLoginBySmscode(HttpServletRequest request,HttpServletResponse response,String phonenumber,String username,String smsCode) {
	  try {
	  String token = userLoginService.doLoginBySmscode(phonenumber, smsCode);
	  if(token==null) {
		return CommonResult.build(401, "��֤���������");
	  }
	  CookieUtils.setCookie(request, response, "Login_Token", token);
	  return CommonResult.ok();
  }catch(Exception e) {
	  e.printStackTrace();
	  return CommonResult.build(500, e.getMessage());
  }
  }
  /**
   * ��ʾ��ǰ�û�
  * @Title: UserLoginController.java 
  * @Package com.pxxysecondhand.controller 
  * @Description: TOP
  * @author  
  * @date 2018��10��29�� ����2:42:50 
  * @version V1.0
   */
  @RequestMapping(value="/showCurrUser/{token}",method=RequestMethod.GET)
  @ResponseBody
  public CommonResult queryByToken(@PathVariable String token,HttpServletRequest request,HttpServletResponse response) {
	  try {
		 User user =  userLoginService.queryByToken(request,response,token);
		 if(user==null) {
			 return CommonResult.build(400, "δ��¼");
		 }
		 return CommonResult.build(200, "�ѵ�¼", JsonUtils.objectToJson(user));
	  }catch(Exception e) {
		  e.printStackTrace();
		  return CommonResult.build(500, "������쳣");
	  }
  }
  /**
	 * 
	* @Title: UserRegistController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: ��ȡ��¼�ֻ���֤��
	* @author  
	* @date 2018��10��28�� ����11:37:04 
	* @version V1.0
	 */
	@RequestMapping(value="/sendSmsMessage",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getRegistSmsMessage(String phonenumber) {
		try {
			return userLoginService.getSmsLoginMessage(phonenumber, "");
		}catch(Exception e) {
			return CommonResult.build(500, e.getMessage());
		}
	}
	
	/**
	 * �˳���¼
	* @Title: UserLoginController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��11��26�� ����9:07:46 
	* @version V1.0
	 */
	 @RequestMapping(value="/quit/{token}",method=RequestMethod.GET)
	 @ResponseBody
	 public CommonResult userquit(@PathVariable String token,HttpServletRequest request,HttpServletResponse response) {
		 try {
			return userLoginService.userQuit(token, request, response);
		} catch (Exception e) {
			return CommonResult.build(500, "δ֪����");
		}
	 }

}
