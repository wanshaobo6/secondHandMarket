/**   
* @Title: SearchController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月22日 下午7:40:51 
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
	 * orderCondition为排序条件 0为默认数据库中orderNumn排序   1为按时间从前到后 2按关注量从多到少 
	 * 							  3.价格从多到少 4按价格从少到多 5按买家信用从高到低  
	* @Title: SearchController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author  
	* @date 2018年11月22日 下午7:51:09 
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
		//判断搜索关键词是否为空
		if(StringUtils.isEmpty(keywords)) {
			mv.setViewName("error");
			return mv;
		}
		//记录搜索记录
		searchService.createSearchRecord(keywords,request,response);
		//查询搜索结果
		SearchResult<SearchItem> result = searchService.searchByKeywords(keywords, orderCondition, page, rows);
		mv.addObject("keywords",keywords);
		mv.addObject("prePage",prePage);
		mv.setViewName("search");
		mv.addObject("data", result);
		return mv;
	}
	
	/**
	 * 按照用户喜好推荐
	* @Title: SearchController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author 万少波
	* @date 2019年3月3日 下午7:51:37 
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
