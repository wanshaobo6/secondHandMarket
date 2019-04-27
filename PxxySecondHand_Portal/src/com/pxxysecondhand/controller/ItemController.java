/**   
* @Title: ItemController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月19日 下午9:00:32 
* @version V1.0   
*/
package com.pxxysecondhand.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pxxysecondhand.pojo.Comment;
import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.ICollectionService;
import com.pxxysecondhand.service.ICommentService;
import com.pxxysecondhand.service.IItemService;
import com.pxxysecondhand.tempPojo.ItemDescComment;
import com.pxxysecondhand.tempPojo.ItemDescResult;
import com.pxxysecondhand.tempPojo.MyPublic;
import com.pxxysecondhand.tempPojo.NewestPublic;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CookieUtils;

import cn.itcast.commons.CommonUtils;
import redis.clients.jedis.JedisPool;

/**
 * @author  
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private IItemService itemService;
	
	@Autowired
	private IndexController indexController;
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private ICollectionService collectionService;
	
	
   /**
    * 发布闲置
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(用一句话描述该文件做什么) 
   * @author  
   * @date 2018年11月19日 下午9:05:50 
   * @version V1.0
    */
   @RequestMapping("/addItem")
   @ResponseBody
   public CommonResult addItem(Item item,HttpServletRequest request,HttpServletResponse response) {
	   //获取当前用户的Cookie中的Token
	   Cookie[] cookies = request.getCookies();
	   String Login_Token = "";
	   boolean tokenNotValiable = true;
	   for(Cookie cookie:cookies) {
		   if(cookie.getName().equals("Login_Token")) {
			   Login_Token = cookie.getValue();
			   tokenNotValiable = false;
		   }
	   }
	   if(tokenNotValiable) return CommonResult.build(500, "当前用户未登录");
	   CommonResult result = itemService.addItem(request,response , item, Login_Token);
	   return result;
   }
   /**
    * 显示闲置详细信息
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(用一句话描述该文件做什么) 
   * @author  
   * @date 2018年11月28日 下午8:15:58 
   * @version V1.0
    */
   @RequestMapping("/showItem")
   public ModelAndView showItem(String itemId,@RequestParam(defaultValue="index")String prePage,HttpServletRequest request,HttpServletResponse response) {
	   ModelAndView mv = new ModelAndView();
	   //1.根据itemId查找相应的数据
	   ItemDescResult itemDescResult = itemService.showItem(itemId);
	   //添加是否收藏的值
	   User user = commentService.checkIsLogin(request);
	   if(user!=null) {
		   boolean b = collectionService.checkCollected(user.getId(), itemDescResult.getId());
		   if(b) {
			   itemDescResult.setIsCollected(1);
		   }else {
			   itemDescResult.setIsCollected(0);
		   }
	   }
	   //2.获取所有评论
	   List<ItemDescComment> comments = commentService.getCommentByItemId(itemId);
	   if(itemDescResult == null) {
		   mv.setViewName("error");
		   mv.addObject("prePage", prePage);
		   return  mv;
	   }
	   //3.添加记录刷新客人身份
	   itemService.addTheRecord( request, response, itemDescResult.getId());
	   mv.setViewName("itemDesc");
	   mv.addObject("data", itemDescResult);
	   mv.addObject("comments",comments);
	   mv.addObject("user",user);
	   mv.addObject("prePage", prePage);
	   return mv;
   }
   
   /**
    * 显示我的所有发布
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(用一句话描述该文件做什么) 
   * @author  
   * @date 2018年11月28日 下午8:21:29 
   * @version V1.0
    */
   @RequestMapping("/showMyPublic")
   public ModelAndView showMyPublic(@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="6")int rows,HttpServletRequest request,HttpServletResponse response) {
	   User user = commentService.checkIsLogin(request);
	   ModelAndView mv = new ModelAndView();
	   if(user==null) {
		   mv.setViewName("login");
		   return mv;
	   }
	   SearchResult<MyPublic> result = itemService.showMyPublic(user, page, rows,request);
	  /* for (MyPublic myPublic : result.getItemList()) {
		   System.out.println(myPublic);
	   }*/
	   mv.setViewName("myPublic");
	   mv.addObject("data", result);
	   return mv;
   }
   
   /**
    *获得最新校园发布
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(用一句话描述该文件做什么) 
   * @author  
   * @date 2018年12月16日 下午6:58:17 
   * @version V1.0
    */
   @RequestMapping("/getNewestPublic")
   @ResponseBody
   public CommonResult getNewestPublic(@RequestParam(defaultValue="6")int size) {
	   List<NewestPublic> list = itemService.getNewestPublic(size);
	   return CommonResult.ok(list);
   }
   
   /**
    * 擦亮宝贝     //没加到了登录过滤 500操作有误 501间隔太短 200success
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(用一句话描述该文件做什么) 
   * @author  
   * @date 2018年12月16日 下午10:55:49 
   * @version V1.0
    */
   @RequestMapping("/polishItem")
   @ResponseBody
   public CommonResult polishItem(HttpServletRequest request , @RequestParam(required=true)String itemId) {
	   //验证是否有操作权
	   User user = itemService.checkSellerByItemId(request,itemId);
	   if(user==null)
		   return CommonResult.build(500,"操作有误");
	    //一分钟擦一次
	    CommonResult result = itemService.polishItem(itemId);
	   return  result;
   }
   
   /**
    * 商品的上下架     //没加到了登录过滤
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(用一句话描述该文件做什么) 
   * @author  
   * @date 2018年12月16日 下午10:56:25 
   * @version V1.0
    */
   @RequestMapping("/changeupanddown")
   @ResponseBody
   public CommonResult changeupanddown(HttpServletRequest request , @RequestParam(required=true)String itemId) {
	   //验证是否有操作权
	   User user = itemService.checkSellerByItemId(request,itemId);
	   if(user==null)
		   return CommonResult.build(500,"操作有误");
	  CommonResult result = itemService.changeupanddown(itemId);
	   return result;
   }
   
   
   //数据库中Trade表没有存图片问题
   /**
    * 删除宝贝     //没加到了登录过滤
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(用一句话描述该文件做什么) 
   * @author  
   * @date 2018年12月16日 下午10:57:17 
   * @version V1.0
    */
   @RequestMapping("/deleteItem")
   @ResponseBody
   public CommonResult deleteItem(HttpServletRequest request , @RequestParam(required=true)String itemId) {
	   //验证是否有操作权
	   User user = itemService.checkSellerByItemId(request,itemId);
	   if(user==null) {
		  return  CommonResult.build(500, "操作有误");
	   }
	   try {
		itemService.deleteItem(itemId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			  return  CommonResult.build(500, "系统故障");
		}
	   return  CommonResult.ok();
   }
   
   @RequestMapping("/showmyMessage")
   @ResponseBody
   public ModelAndView showMyMessage(@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="6")int rows,HttpServletRequest request,HttpServletResponse response) {
	   User user = commentService.checkIsLogin(request);
	   ModelAndView mv = new ModelAndView();
	   if(user==null) {
		   mv.setViewName("login");
		   return mv;
	   }
	   SearchResult<Comment> result = itemService.showMyMessage(user, page, rows,request);
	  /* for (MyPublic myPublic : result.getItemList()) {
		   System.out.println(myPublic);
	   }*/
	   mv.setViewName("myMessage");
	   mv.addObject("data", result);
	   return mv;
   }
  }