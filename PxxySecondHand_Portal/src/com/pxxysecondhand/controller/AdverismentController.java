package com.pxxysecondhand.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pxxysecondhand.pojo.Admin;
import com.pxxysecondhand.pojo.Advertisement;
import com.pxxysecondhand.service.IAdvertisementService;
import com.pxxysecondhand.utils.CommonResult;

@Controller
@RequestMapping("/adv")
public class AdverismentController {
	
	@Autowired
	private IAdvertisementService advertisementService;
	
	//查询广告
	@RequestMapping("/query")
	@ResponseBody
	public List<Advertisement> queryAdvAll(){ 
		List<Advertisement> adverisementList = this.advertisementService.queryAdverisement();
		return adverisementList;
	}
	
	//新增广告
	@RequestMapping("add")
	@ResponseBody
	public CommonResult addAdv(Advertisement advertisement,HttpServletRequest request){ 
		HttpSession session = request.getSession();
		//操作员的id
		Admin user = (Admin) session.getAttribute("user");
		
		advertisement.setAdname(user.getAdminname());
		
		CommonResult result = this.advertisementService.addAdverisement(advertisement);
		
		return result;

	}
	
	//删除广告
	@RequestMapping("delete")
	@ResponseBody
	public CommonResult deleteAdv(Integer aid){ 
	
		CommonResult result = this.advertisementService.deleteAderisement(aid);
		
		return result;

	}
	
}
