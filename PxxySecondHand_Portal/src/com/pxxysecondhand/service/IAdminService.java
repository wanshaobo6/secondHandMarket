package com.pxxysecondhand.service;

import java.util.List;

import com.pxxysecondhand.pojo.Admin;

public interface IAdminService {
	public List<Admin> adminLogin(String adminname,String password);



	
}
