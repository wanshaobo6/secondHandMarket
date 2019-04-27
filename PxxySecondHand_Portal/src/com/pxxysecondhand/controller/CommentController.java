/**   
* @Title: CommentController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��30�� ����4:14:36 
* @version V1.0   
*/
package com.pxxysecondhand.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.ICommentService;
import com.pxxysecondhand.tempPojo.ItemDescComment;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private ICommentService commentService;
	
	/**
	 * ��õ�ǰ���õ���������
	* @Title: CommentController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��11��30�� ����5:11:31 
	* @version V1.0
	 */
  @RequestMapping(value="/getCommentByItemId",method=RequestMethod.GET)
  @ResponseBody
  public CommonResult getCommentByItemId(HttpServletRequest request,@RequestParam(required=true)String itemId) {
	  //�жϵ�ǰ�û��Ƿ��¼
	  User user = commentService.checkIsLogin(request);
	  if(user==null) {
		  return CommonResult.build(500,"����û�е�¼��½");
	  }
	 List<ItemDescComment> commentList = commentService.getCommentByItemId(itemId);
	 return CommonResult.ok(commentList);
  }
  
  /**
   * ��������
  * @Title: CommentController.java 
  * @Package com.pxxysecondhand.controller 
  * @Description: TODO(��һ�仰�������ļ���ʲô) 
  * @author  
  * @date 2018��12��1�� ����1:58:25 
  * @version V1.0
   */
  @RequestMapping(value="/publicMyComment",method=RequestMethod.GET)
  @ResponseBody
  public CommonResult publicMyComment(HttpServletRequest request,@RequestParam(required=true)String itemId ,@RequestParam(defaultValue="0") String parentId ,@RequestParam(required=true)String Content ) {
	  //1.�鿴�û��Ƿ��¼
	  User user = commentService.checkIsLogin(request);
	  if(user==null)
		  return CommonResult.build(500, "�û�û�е�¼");
	  //2.��������
	  CommonResult result = commentService.publicMyComment(user, itemId, parentId,Content);
	  //3.�鿴jedis���û��Ƿ�����
	  	///////////////////////////----------------------------------------------------------------ûʵ��-------------------------------------
	  	  //��������߶���֪ͨ����
	  	  //�������5����û�鿴����֪ͨ
	  return result;
  }
  
  /**
   * �ݹ�ɾ��������
  * @Title: CommentController.java 
  * @Package com.pxxysecondhand.controller 
  * @Description: TODO(��һ�仰�������ļ���ʲô) 
  * @author  
  * @date 2018��12��1�� ����3:43:18 
  * @version V1.0
   */
  @RequestMapping(value="/deleteMyComment",method=RequestMethod.GET)
  @ResponseBody
  public CommonResult deleteMyComment(HttpServletRequest request , String commentId ) {
	  //1.�鿴�û��Ƿ��¼
	  User user = commentService.checkIsLogin(request);
	  if(user==null)
		  return CommonResult.build(500, "�û�û�е�¼");
	  //ɾ������
	   CommonResult result = commentService.deleteMyComment(user, commentId);
	  return result;
  }
}
