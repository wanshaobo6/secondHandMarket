/**   
* @Title: MailController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月19日 下午3:58:04 
* @version V1.0   
*/
package com.pxxysecondhand.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.ICommentService;
import com.pxxysecondhand.service.IMailService;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
@Controller
@RequestMapping("/mail")    //加入过滤器
public class MailController {
	@Autowired
	private IMailService mailService;
	
	@Autowired
	private ICommentService CommentService;
	/**
	 * 给站长发送邮件
	* @Title: MailController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author  
	* @date 2018年12月19日 下午4:01:23 
	* @version V1.0
	 */
  @RequestMapping(value="/sendMailToWebMaster",method=RequestMethod.GET)
   @ResponseBody
   public CommonResult sendMailToWebMaster(HttpServletRequest request,@RequestParam(required=true)String msg) {
	  User user = CommentService.checkIsLogin(request);
	  CommonResult result = mailService.sendMailToWebMaster(user, msg);
	  return result;
  }
}
