/**   
* @Title: CommentController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月30日 下午4:14:36 
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
	 * 获得当前闲置的所有评论
	* @Title: CommentController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author  
	* @date 2018年11月30日 下午5:11:31 
	* @version V1.0
	 */
  @RequestMapping(value="/getCommentByItemId",method=RequestMethod.GET)
  @ResponseBody
  public CommonResult getCommentByItemId(HttpServletRequest request,@RequestParam(required=true)String itemId) {
	  //判断当前用户是否登录
	  User user = commentService.checkIsLogin(request);
	  if(user==null) {
		  return CommonResult.build(500,"您还没有登录登陆");
	  }
	 List<ItemDescComment> commentList = commentService.getCommentByItemId(itemId);
	 return CommonResult.ok(commentList);
  }
  
  /**
   * 发表评论
  * @Title: CommentController.java 
  * @Package com.pxxysecondhand.controller 
  * @Description: TODO(用一句话描述该文件做什么) 
  * @author  
  * @date 2018年12月1日 下午1:58:25 
  * @version V1.0
   */
  @RequestMapping(value="/publicMyComment",method=RequestMethod.GET)
  @ResponseBody
  public CommonResult publicMyComment(HttpServletRequest request,@RequestParam(required=true)String itemId ,@RequestParam(defaultValue="0") String parentId ,@RequestParam(required=true)String Content ) {
	  //1.查看用户是否登录
	  User user = commentService.checkIsLogin(request);
	  if(user==null)
		  return CommonResult.build(500, "用户没有登录");
	  //2.新增评论
	  CommonResult result = commentService.publicMyComment(user, itemId, parentId,Content);
	  //3.查看jedis中用户是否在线
	  	///////////////////////////----------------------------------------------------------------没实现-------------------------------------
	  	  //如果不在线短信通知短信
	  	  //如果在线5分钟没查看短信通知
	  return result;
  }
  
  /**
   * 递归删除评论树
  * @Title: CommentController.java 
  * @Package com.pxxysecondhand.controller 
  * @Description: TODO(用一句话描述该文件做什么) 
  * @author  
  * @date 2018年12月1日 下午3:43:18 
  * @version V1.0
   */
  @RequestMapping(value="/deleteMyComment",method=RequestMethod.GET)
  @ResponseBody
  public CommonResult deleteMyComment(HttpServletRequest request , String commentId ) {
	  //1.查看用户是否登录
	  User user = commentService.checkIsLogin(request);
	  if(user==null)
		  return CommonResult.build(500, "用户没有登录");
	  //删除评论
	   CommonResult result = commentService.deleteMyComment(user, commentId);
	  return result;
  }
}
