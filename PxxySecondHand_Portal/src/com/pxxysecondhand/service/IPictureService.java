/**   
* @Title: IPictureService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月17日 下午5:37:55 
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
