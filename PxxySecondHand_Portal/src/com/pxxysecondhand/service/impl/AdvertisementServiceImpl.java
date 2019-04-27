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
 * ������
 * <p>Title: AdvertisementServiceImpl</p>
 * <p>Description: </p>
 * <p>�򵥵������ظ���,���ӵ�����򵥻�</p> 
 * @author	���
 * @date	2019��4��26������7:35:04
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
			return CommonResult.build(500, "������ʧ��");
		}
		return CommonResult.ok();
	}

	@Override
	public CommonResult deleteAderisement(Integer aid) {
		// TODO Auto-generated method stub
		int i = this.advertisementMapper.deleteByPrimaryKey(aid);
		if(i!=1) {
			return CommonResult.build(500, "ɾ�����ʧ��");
		}	
		return CommonResult.ok();

	}

}
