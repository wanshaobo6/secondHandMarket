/**   
* @Title: PictureController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月17日 下午5:37:17 
* @version V1.0   
*/
package com.pxxysecondhand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pxxysecondhand.service.IPictureService;
import com.pxxysecondhand.utils.JsonUtils;
import com.pxxysecondhand.utils.PictureResult;

/**
 * @author  
 *
 */
@Controller
@RequestMapping("/pic")
public class PictureController {
	@Autowired 
	private IPictureService pictureService;
	
	/**
	 * 上传图片
	* @Title: PictureController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author  
	* @date 2018年11月19日 下午7:44:12 
	* @version V1.0
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile uploadFile) {
		PictureResult result = pictureService.uploadFile(uploadFile);
		String str = JsonUtils.objectToJson(result);
		System.out.println(str);
		return str;
	}
}
