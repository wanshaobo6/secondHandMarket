/**   
* @Title: MessageController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��19�� ����7:28:49 
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
import org.springframework.web.servlet.ModelAndView;

import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.Message;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.ICommentService;
import com.pxxysecondhand.service.IMessageService;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.tempPojo.TempMessage;
import com.pxxysecondhand.tempPojo.WebDynamicMessage;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private IMessageService messageService;
	
	@Autowired
	private ICommentService commentService;
	
	 /**
	  * �����վ��̬�б�
	 * @Title: MessageController.java 
	 * @Package com.pxxysecondhand.controller 
	 * @Description: TODO(��һ�仰�������ļ���ʲô) 
	 * @author  
	 * @date 2018��12��19�� ����7:33:45 
	 * @version V1.0
	  */
	 @RequestMapping(value="/getAllWebMessage",method=RequestMethod.GET)
	   public ModelAndView getAllWebMessage(HttpServletRequest request,@RequestParam(defaultValue="1") int page,
			   @RequestParam(defaultValue="6") int size ) {
		 ModelAndView mv = new ModelAndView();
		 User user = commentService.checkIsLogin(request);
		 SearchResult<WebDynamicMessage> result ;
		 if(user == null)
			 result =  messageService.getWebMessageAutomatically(null, page, size);
		 else
			 result =  messageService.getWebMessageAutomatically(user.getId(), page, size);
		 //����messageListҳ�� ���Ǹ�ҳ�治����
		 mv.addObject("result", result);
		  mv.setViewName("messageList");
		  return mv;
	  }
	 
	 /**
	  * ������Ϣid�鿴��Ϣ
	 * @Title: MessageController.java 
	 * @Package com.pxxysecondhand.controller 
	 * @Description: TODO(��һ�仰�������ļ���ʲô) 
	 * @author  
	 * @date 2018��12��19�� ����10:20:35 
	 * @version V1.0
	  */
	 @RequestMapping(value="/getMessageById",method=RequestMethod.GET)
	   public ModelAndView getMessageById(HttpServletRequest request,@RequestParam(required=true) String messageId ) {
		 ModelAndView mv = new ModelAndView();
		 User user = commentService.checkIsLogin(request);
		 TempMessage message = null;
		 try {
			 message = messageService.getMessageById(user,messageId);
		} catch (Exception e) {
			mv.setViewName("error");
			mv.addObject("Msg",e.getMessage());
			return mv;
		}
	 	mv.setViewName("puNews");
		mv.addObject("message",message);
		return mv;
	 }
}
