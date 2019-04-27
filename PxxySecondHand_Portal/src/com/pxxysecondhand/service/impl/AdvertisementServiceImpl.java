package com.pxxysecondhand.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.pxxysecondhand.mapper.AdvertisementMapper;
import com.pxxysecondhand.pojo.Advertisement;
import com.pxxysecondhand.pojo.AdvertisementExample;
import com.pxxysecondhand.service.IAdvertisementService;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CommonUtils;

/**
 * 广告管理
 * <p>Title: AdvertisementServiceImpl</p>
 * <p>Description: </p>
 * <p>简单的事情重复做,复杂的事情简单化</p> 
 * @author	吴彬
 * @date	2019年4月26日下午7:35:04
 * @version 1.0
 */
@Service
public class AdvertisementServiceImpl implements IAdvertisementService {
	
	@Autowired
	private AdvertisementMapper advertisementMapper;
	

	@Override
	public List<Advertisement> queryAdverisement() {
		AdvertisementExample example=null;
		List<Advertisement> advertisements = this.advertisementMapper.selectByExample(example);
		// TODO Auto-generated method stub
		if(CollectionUtils.isEmpty(advertisements)) {
			return null;
		}
		return advertisements;
	}

	@Override
	public CommonResult addAdverisement(Advertisement advertisement) {
		// TODO Auto-generated method stub
		String aid = CommonUtils.uuidGenerator();
		advertisement.setAid(Integer.parseInt(aid));
		int i = this.advertisementMapper.insert(advertisement);
		if(i!=1) {
			return CommonResult.build(500, "插入广告失败");
		}
		return CommonResult.ok();
	}

	@Override
	public CommonResult deleteAderisement(Integer aid) {
		// TODO Auto-generated method stub
		int i = this.advertisementMapper.deleteByPrimaryKey(aid);
		if(i!=1) {
			return CommonResult.build(500, "删除广告失败");
		}	
		return CommonResult.ok();

	}

}
