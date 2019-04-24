package com.pxxysecondhand.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pxxysecondhand.pojo.ItemCat;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.ICommentService;
import com.pxxysecondhand.service.IItemCatService;
import com.pxxysecondhand.service.IItemService;
import com.pxxysecondhand.service.IMessageService;
import com.pxxysecondhand.service.ISearchService;
import com.pxxysecondhand.tempPojo.GuessItem;
import com.pxxysecondhand.tempPojo.NewestPublic;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.tempPojo.WebDynamicMessage;
import com.pxxysecondhand.utils.JsonUtils;

@Controller
public class IndexController {
	@Autowired
	private IItemService itemService;
	
	@Autowired
	private IItemCatService itemCatService;
	
	@Autowired
	private IMessageService messageService;
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private ISearchService searchService;
	
	@RequestMapping("/index")
	public ModelAndView showIndex(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		//查看当前用户
		User user = commentService.checkIsLogin(request);
		//获得最新的发布
		List<NewestPublic> lists = itemService.getNewestPublic(6);
		mv.addObject("newestPublics",lists);
		//获得最新的消息
		 SearchResult<WebDynamicMessage> result ;
		 if(user == null)
			 result =  messageService.getWebMessageAutomatically(null, 1, 6);
		 else
			 result =  messageService.getWebMessageAutomatically(user.getId(), 1, 6);
		 //获取用户的推荐
		 List<GuessItem> guessList = searchService.searchByUserHabit(request, response);
		 mv.addObject("guessList",guessList);
		 mv.addObject("messageList", JsonUtils.jsonToList(JsonUtils.objectToJson(result.getItemList()), WebDynamicMessage.class));
		 mv.setViewName("index");
		return mv;
	
	}
	
	@RequestMapping("/{page}")
	public ModelAndView showpage(@PathVariable String page,@RequestParam(defaultValue="index")String prePage,@RequestParam(defaultValue=" ")Object obj ) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("prePage", prePage);
		if(obj!=null)
		if(!StringUtils.isEmpty(obj.toString().trim())) {
			mv.addObject("data", obj);
		}
		mv.setViewName(page);
		return mv;
	}
	
	@RequestMapping("/admin/{page}")
	public String showManagePage(@PathVariable String page , HttpServletRequest request, HttpServletResponse response) {
		String pagePath = "/admin/pages/"+page;
		return pagePath;
	}
}
