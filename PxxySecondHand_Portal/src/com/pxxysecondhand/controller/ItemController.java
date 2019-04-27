/**   
* @Title: ItemController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��19�� ����9:00:32 
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
    * ��������
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(��һ�仰�������ļ���ʲô) 
   * @author  
   * @date 2018��11��19�� ����9:05:50 
   * @version V1.0
    */
   @RequestMapping("/addItem")
   @ResponseBody
   public CommonResult addItem(Item item,HttpServletRequest request,HttpServletResponse response) {
	   //��ȡ��ǰ�û���Cookie�е�Token
	   Cookie[] cookies = request.getCookies();
	   String Login_Token = "";
	   boolean tokenNotValiable = true;
	   for(Cookie cookie:cookies) {
		   if(cookie.getName().equals("Login_Token")) {
			   Login_Token = cookie.getValue();
			   tokenNotValiable = false;
		   }
	   }
	   if(tokenNotValiable) return CommonResult.build(500, "��ǰ�û�δ��¼");
	   CommonResult result = itemService.addItem(request,response , item, Login_Token);
	   return result;
   }
   /**
    * ��ʾ������ϸ��Ϣ
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(��һ�仰�������ļ���ʲô) 
   * @author  
   * @date 2018��11��28�� ����8:15:58 
   * @version V1.0
    */
   @RequestMapping("/showItem")
   public ModelAndView showItem(String itemId,@RequestParam(defaultValue="index")String prePage,HttpServletRequest request,HttpServletResponse response) {
	   ModelAndView mv = new ModelAndView();
	   //1.����itemId������Ӧ������
	   ItemDescResult itemDescResult = itemService.showItem(itemId);
	   //����Ƿ��ղص�ֵ
	   User user = commentService.checkIsLogin(request);
	   if(user!=null) {
		   boolean b = collectionService.checkCollected(user.getId(), itemDescResult.getId());
		   if(b) {
			   itemDescResult.setIsCollected(1);
		   }else {
			   itemDescResult.setIsCollected(0);
		   }
	   }
	   //2.��ȡ��������
	   List<ItemDescComment> comments = commentService.getCommentByItemId(itemId);
	   if(itemDescResult == null) {
		   mv.setViewName("error");
		   mv.addObject("prePage", prePage);
		   return  mv;
	   }
	   //3.��Ӽ�¼ˢ�¿������
	   itemService.addTheRecord( request, response, itemDescResult.getId());
	   mv.setViewName("itemDesc");
	   mv.addObject("data", itemDescResult);
	   mv.addObject("comments",comments);
	   mv.addObject("user",user);
	   mv.addObject("prePage", prePage);
	   return mv;
   }
   
   /**
    * ��ʾ�ҵ����з���
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(��һ�仰�������ļ���ʲô) 
   * @author  
   * @date 2018��11��28�� ����8:21:29 
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
    *�������У԰����
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(��һ�仰�������ļ���ʲô) 
   * @author  
   * @date 2018��12��16�� ����6:58:17 
   * @version V1.0
    */
   @RequestMapping("/getNewestPublic")
   @ResponseBody
   public CommonResult getNewestPublic(@RequestParam(defaultValue="6")int size) {
	   List<NewestPublic> list = itemService.getNewestPublic(size);
	   return CommonResult.ok(list);
   }
   
   /**
    * ��������     //û�ӵ��˵�¼���� 500�������� 501���̫�� 200success
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(��һ�仰�������ļ���ʲô) 
   * @author  
   * @date 2018��12��16�� ����10:55:49 
   * @version V1.0
    */
   @RequestMapping("/polishItem")
   @ResponseBody
   public CommonResult polishItem(HttpServletRequest request , @RequestParam(required=true)String itemId) {
	   //��֤�Ƿ��в���Ȩ
	   User user = itemService.checkSellerByItemId(request,itemId);
	   if(user==null)
		   return CommonResult.build(500,"��������");
	    //һ���Ӳ�һ��
	    CommonResult result = itemService.polishItem(itemId);
	   return  result;
   }
   
   /**
    * ��Ʒ�����¼�     //û�ӵ��˵�¼����
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(��һ�仰�������ļ���ʲô) 
   * @author  
   * @date 2018��12��16�� ����10:56:25 
   * @version V1.0
    */
   @RequestMapping("/changeupanddown")
   @ResponseBody
   public CommonResult changeupanddown(HttpServletRequest request , @RequestParam(required=true)String itemId) {
	   //��֤�Ƿ��в���Ȩ
	   User user = itemService.checkSellerByItemId(request,itemId);
	   if(user==null)
		   return CommonResult.build(500,"��������");
	  CommonResult result = itemService.changeupanddown(itemId);
	   return result;
   }
   
   
   //���ݿ���Trade��û�д�ͼƬ����
   /**
    * ɾ������     //û�ӵ��˵�¼����
   * @Title: ItemController.java 
   * @Package com.pxxysecondhand.controller 
   * @Description: TODO(��һ�仰�������ļ���ʲô) 
   * @author  
   * @date 2018��12��16�� ����10:57:17 
   * @version V1.0
    */
   @RequestMapping("/deleteItem")
   @ResponseBody
   public CommonResult deleteItem(HttpServletRequest request , @RequestParam(required=true)String itemId) {
	   //��֤�Ƿ��в���Ȩ
	   User user = itemService.checkSellerByItemId(request,itemId);
	   if(user==null) {
		  return  CommonResult.build(500, "��������");
	   }
	   try {
		itemService.deleteItem(itemId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			  return  CommonResult.build(500, "ϵͳ����");
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