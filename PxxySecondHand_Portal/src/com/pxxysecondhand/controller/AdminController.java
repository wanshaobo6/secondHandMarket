package com.pxxysecondhand.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pxxysecondhand.pojo.Admin;
import com.pxxysecondhand.service.IAdminService;



@Controller
@RequestMapping("adminOpe")
public class AdminController {
	@Autowired
	private IAdminService adminService;
	public String adminLogin  (HttpServletRequest request,String adminname,String password){
		List<Admin> admins =  adminService.adminLogin(adminname, password);
		if(admins == null || admins.size() == 0) {
			return "用户名或密码错误";
		}
		 request.getSession().setAttribute("user", adminname);
		return "yes";
		
	}
	

}
