/**   
* @Title: CollectionController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��2�� ����5:09:04 
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
	 * �ı��ղص�״̬
	* @Title: CollectionController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��2�� ����5:20:24 
	* @version V1.0
	 */
	@RequestMapping("/changeMyCollection")
	@ResponseBody
	public CommonResult changeMyCollection(HttpServletRequest request,String itemId) {
		//1.�����û���¼״̬
		User user = commentService.checkIsLogin(request);
		if(user==null)
			return CommonResult.build(500, "δ��¼");
		//2.���õ���Ч�Լ���
		Item item = itemService.getItemByItemId(itemId);
		if(item==null)
			return CommonResult.build(500, "���ò�����");
		//3.�ı�״̬
		CommonResult result = collectionService.changeMyCollection(user, itemId);
		return result;
	}
	
	/**
	 * ��ʾ�ҵ������ղ�
	* @Title: CollectionController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��12��3�� ����10:30:12 
	* @version V1.0
	 */
	@RequestMapping("/getAllOfMyCollections")
	private ModelAndView getAllOfMyCollections(HttpServletRequest request,@RequestParam(defaultValue="1") int page , 
			@RequestParam(defaultValue="6") int rows) {
		ModelAndView mv = new ModelAndView();
		//1.�����û���¼״̬
			User user = commentService.checkIsLogin(request);
			if(user==null) {
				mv.setViewName("login");
				return mv;
			}
		//2.��ѯ���е��ղؼ�¼
			SearchResult<MyCollections> result = collectionService.getAllOfMyCollections(request,user.getId(),page,rows);
			mv.addObject("data", result);
			mv.setViewName("myCollection");
			return mv;
	}
}
