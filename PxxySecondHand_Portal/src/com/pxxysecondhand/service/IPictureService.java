/**   
* @Title: IPictureService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��17�� ����5:37:55 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import org.springframework.web.multipart.MultipartFile;

import com.pxxysecondhand.utils.PictureResult;

/**
 * @author  
 *
 */
public interface IPictureService {
	
	public PictureResult uploadFile(MultipartFile mulitpartFile);
	
}
