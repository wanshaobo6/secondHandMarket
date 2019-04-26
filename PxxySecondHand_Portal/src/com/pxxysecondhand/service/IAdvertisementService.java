package com.pxxysecondhand.service;

import java.util.List;

import com.pxxysecondhand.pojo.Advertisement;
import com.pxxysecondhand.utils.CommonResult;

public interface IAdvertisementService {
	
	//查询广告
	public List<Advertisement> queryAdverisement();
	
	//添加广告
	public CommonResult addAdverisement(Advertisement advertisement);
	
	//更新广告
	//public void updateAderisement(Advertisement advertisement);
	
	//删除广告
	public CommonResult deleteAderisement(Integer aid);
	
	
	

}
