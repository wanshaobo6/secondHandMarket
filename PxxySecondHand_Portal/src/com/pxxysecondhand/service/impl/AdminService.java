package com.pxxysecondhand.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pxxysecondhand.mapper.AdminMapper;
import com.pxxysecondhand.pojo.Admin;
import com.pxxysecondhand.pojo.AdminExample;
import com.pxxysecondhand.pojo.AdminExample.Criteria;
import com.pxxysecondhand.service.IAdminService;
@Service
public class AdminService implements IAdminService{

	@Autowired
	private AdminMapper adminMapper;
	public List<Admin> adminLogin(String adminname, String password) {
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andAdminnameEqualTo(adminname).andAdminpasswordEqualTo(password);
		return adminMapper.selectByExample(example);
	}

}
