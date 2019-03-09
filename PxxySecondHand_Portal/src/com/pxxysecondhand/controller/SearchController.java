/**   
* @Title: SearchController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��22�� ����7:40:51 
* @version V1.0   
*/
package com.pxxysecondhand.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pxxysecondhand.component.ISearchRecorder;
import com.pxxysecondhand.service.ISearchService;
import com.pxxysecondhand.tempPojo.GuessItem;
import com.pxxysecondhand.tempPojo.SearchItem;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CookieUtils;

/**
 * @author  
 *
 */
@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private ISearchService searchService;
	@Autowired
	private IndexController indexController;
	

	/**
	 * orderConditionΪ�������� 0ΪĬ�����ݿ���orderNumn����   1Ϊ��ʱ���ǰ���� 2����ע���Ӷൽ�� 
	 * 							  3.�۸�Ӷൽ�� 4���۸���ٵ��� 5��������ôӸߵ���  
	* @Title: SearchController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��11��22�� ����7:51:09 
	* @version V1.0
	 */
	@RequestMapping("/searchByKeywords")
	public ModelAndView searchByKeywords(
			@RequestParam(name= "keywords",required=true)String keywords  ,
			@RequestParam(defaultValue="index") String prePage,
			@RequestParam(defaultValue="1") int page , 
			@RequestParam(defaultValue="8") int rows ,
			@RequestParam(defaultValue="0")int orderCondition , 
			HttpServletRequest request , 
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		//�ж������ؼ����Ƿ�Ϊ��
		if(StringUtils.isEmpty(keywords)) {
			mv.setViewName("error");
			return mv;
		}
		//��¼������¼
		searchService.createSearchRecord(keywords,request,response);
		//��ѯ�������
		SearchResult<SearchItem> result = searchService.searchByKeywords(keywords, orderCondition, page, rows);
		mv.addObject("keywords",keywords);
		mv.addObject("prePage",prePage);
		mv.setViewName("search");
		mv.addObject("data", result);
		return mv;
	}
	
	/**
	 * �����û�ϲ���Ƽ�
	* @Title: SearchController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author ���ٲ�
	* @date 2019��3��3�� ����7:51:37 
	* @version V1.0
	 */
	@RequestMapping(value="searchByUserHabit",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult searchByUserHabit(HttpServletRequest request  ,HttpServletResponse response) {
		 List<GuessItem> guessItems = searchService.searchByUserHabit(request,response);
		return CommonResult.ok(guessItems);
	}
	
	@RequestMapping(value="getPopulateSearchKeys",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getPopulateSearchKeys() {
		List<String> populateWords = searchService.getPopulateSearchKeys();
		return CommonResult.ok(populateWords);
	}
}
