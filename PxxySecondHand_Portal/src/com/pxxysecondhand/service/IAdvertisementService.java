package com.pxxysecondhand.service;

import java.util.List;

import com.pxxysecondhand.pojo.Advertisement;
import com.pxxysecondhand.utils.CommonResult;

public interface IAdvertisementService {
	
	//��ѯ���
	public List<Advertisement> queryAdverisement();
	
	//��ӹ��
	public CommonResult addAdverisement(Advertisement advertisement);
	
	//���¹��
	//public void updateAderisement(Advertisement advertisement);
	
	//ɾ�����
	public CommonResult deleteAderisement(Integer aid);
	
	
	

}
