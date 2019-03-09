/**   
* @Title: CollectionController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月2日 下午5:09:04 
* @version V1.0   
*/
package com.pxxysecondhand.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.ICollectionService;
import com.pxxysecondhand.service.ICommentService;
import com.pxxysecondhand.service.IItemService;
import com.pxxysecondhand.tempPojo.MyCollections;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private ICollectionService collectionService;
	
	@Autowired
	private IItemService itemService;
	
	
	/**
	 * 改变收藏的状态
	* @Title: CollectionController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author  
	* @date 2018年12月2日 下午5:20:24 
	* @version V1.0
	 */
	@RequestMapping("/changeMyCollection")
	@ResponseBody
	public CommonResult changeMyCollection(HttpServletRequest request,String itemId) {
		//1.检验用户登录状态
		User user = commentService.checkIsLogin(request);
		if(user==null)
			return CommonResult.build(500, "未登录");
		//2.闲置的有效性检验
		Item item = itemService.getItemByItemId(itemId);
		if(item==null)
			return CommonResult.build(500, "闲置不存在");
		//3.改变状态
		CommonResult result = collectionService.changeMyCollection(user, itemId);
		return result;
	}
	
	/**
	 * 显示我的所有收藏
	* @Title: CollectionController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author  
	* @date 2018年12月3日 下午10:30:12 
	* @version V1.0
	 */
	@RequestMapping("/getAllOfMyCollections")
	private ModelAndView getAllOfMyCollections(HttpServletRequest request,@RequestParam(defaultValue="1") int page , 
			@RequestParam(defaultValue="6") int rows) {
		ModelAndView mv = new ModelAndView();
		//1.检验用户登录状态
			User user = commentService.checkIsLogin(request);
			if(user==null) {
				mv.setViewName("login");
				return mv;
			}
		//2.查询所有的收藏记录
			SearchResult<MyCollections> result = collectionService.getAllOfMyCollections(request,user.getId(),page,rows);
			mv.addObject("data", result);
			mv.setViewName("myCollection");
			return mv;
	}
}
