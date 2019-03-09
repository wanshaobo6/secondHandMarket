package com.pxxysecondhand.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.IUserRegistService;
import com.pxxysecondhand.service.impl.UserRegistServiceImpl;
import com.pxxysecondhand.utils.CommonResult;

@Controller
@RequestMapping("/regist")
public class UserRegistController {
	@Autowired
	private IUserRegistService userRegistService;
	
	
	/**
	 * 
	* @Title: UserRegistController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description:验证手机号码格式和是否存在 
	* @author  
	* @date 2018年10月28日 上午11:37:51 
	* @version V1.0
	 */
	@RequestMapping(value="/validatePhonenumber",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public CommonResult validatePhonenumber(String phonenumber) {
		try {
			userRegistService.validatePhonenumberFormat(phonenumber);
			String result = userRegistService.validatePhonenumber(phonenumber);
			return CommonResult.build(200,result );
		} catch (Exception e) {
			return CommonResult.build(500, e.getMessage());
		}
	} 
	
	/**
	 * 
	* @Title: UserRegistController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO验证用户名格式和是否存在
	* @author  
	* @date 2018年10月28日 上午11:37:26 
	* @version V1.0
	 */
	@RequestMapping(value="/validateUsername",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public CommonResult validateUsername(String username) {
		try {
			userRegistService.validateUserNameFormat(username);
			String result = userRegistService.validateUserName(username);
			return CommonResult.build(200,result );
		}catch(Exception e) {
			return CommonResult.build(500, e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: UserRegistController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: 获取手机注册验证码
	* @author  
	* @date 2018年10月28日 上午11:37:04 
	* @version V1.0
	 */
	@RequestMapping(value="/sendSmsMessage",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getRegistSmsMessage(String name,String phonenumber) {
		try {
			CommonResult result = userRegistService.getSmsRegistMessage(name, phonenumber);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return CommonResult.build(500,e.getMessage());
		}
	}
	/**
	 * 
	* @Title: UserRegistController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO用户注册
	* @author  
	* @date 2018年10月28日 上午11:36:56 
	* @version V1.0
	 */
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	public CommonResult addUser(User user,@RequestParam("smscode") String registCode , String prePage) {
		Map<String,String> errors = new HashMap();
		try {
			 errors = userRegistService.addUser(user, registCode);
			if(errors!=null&&errors.size()>0) {
				return CommonResult.build(500,"", errors);
			}else {
				return CommonResult.ok();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.put("fatalError", e.getMessage());
			return CommonResult.build(500,"", errors);
		}
	}

}
