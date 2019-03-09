/**   
* @Title: UserLoginController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年10月28日 下午5:25:09 
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
 * O通过用户和密码登录
* @Title: UserLoginController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TOD
* @author  
* @date 2018年10月29日 下午2:41:29 
* @version V1.0
 */
  @RequestMapping(value="/doLoginByPass",method=RequestMethod.POST)
  @ResponseBody
  public CommonResult doLoginByPass(HttpServletRequest request,HttpServletResponse response,User user,String verifyCode) {
	  try {
		  if(!userLoginService.validateVerifyCode(request, verifyCode)) {
			  return CommonResult.build(400, "验证码不正确",user);
		  }
		  String token = userLoginService.doLoginByPass(user.getUsername(), user.getPassword());
		  if(token==null) {
			return CommonResult.build(401, "用户名或密码错误",user);
		  }
		  CookieUtils.setCookie(request, response, "Login_Token", token);
		  return CommonResult.ok();
	  }catch(Exception e) {
		  e.printStackTrace();
		  return CommonResult.build(500, "服务器异常");
	  }
  }
  
  /**
   * 通过手机验证码登录
  * @Title: UserLoginController.java 
  * @Package com.pxxysecondhand.controller 
  * @Description: TODO(用一句话描述该文件做什么) 
  * @author  
  * @date 2018年10月30日 下午2:10:16 
  * @version V1.0
   */
  @RequestMapping(value="/doLoginBySmscode",method=RequestMethod.POST)
  @ResponseBody
  public CommonResult doLoginBySmscode(HttpServletRequest request,HttpServletResponse response,String phonenumber,String username,String smsCode) {
	  try {
	  String token = userLoginService.doLoginBySmscode(phonenumber, smsCode);
	  if(token==null) {
		return CommonResult.build(401, "验证码输入错误");
	  }
	  CookieUtils.setCookie(request, response, "Login_Token", token);
	  return CommonResult.ok();
  }catch(Exception e) {
	  e.printStackTrace();
	  return CommonResult.build(500, e.getMessage());
  }
  }
  /**
   * 显示当前用户
  * @Title: UserLoginController.java 
  * @Package com.pxxysecondhand.controller 
  * @Description: TOP
  * @author  
  * @date 2018年10月29日 下午2:42:50 
  * @version V1.0
   */
  @RequestMapping(value="/showCurrUser/{token}",method=RequestMethod.GET)
  @ResponseBody
  public CommonResult queryByToken(@PathVariable String token,HttpServletRequest request,HttpServletResponse response) {
	  try {
		 User user =  userLoginService.queryByToken(request,response,token);
		 if(user==null) {
			 return CommonResult.build(400, "未登录");
		 }
		 return CommonResult.build(200, "已登录", JsonUtils.objectToJson(user));
	  }catch(Exception e) {
		  e.printStackTrace();
		  return CommonResult.build(500, "服务端异常");
	  }
  }
  /**
	 * 
	* @Title: UserRegistController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: 获取登录手机验证码
	* @author  
	* @date 2018年10月28日 上午11:37:04 
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
	 * 退出登录
	* @Title: UserLoginController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author  
	* @date 2018年11月26日 下午9:07:46 
	* @version V1.0
	 */
	 @RequestMapping(value="/quit/{token}",method=RequestMethod.GET)
	 @ResponseBody
	 public CommonResult userquit(@PathVariable String token,HttpServletRequest request,HttpServletResponse response) {
		 try {
			return userLoginService.userQuit(token, request, response);
		} catch (Exception e) {
			return CommonResult.build(500, "未知错误");
		}
	 }

}
