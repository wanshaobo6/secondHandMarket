/**   
* @Title: PictureServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月17日 下午5:39:38 
* @version V1.0   
*/
package com.pxxysecondhand.service.impl;

import java.awt.Image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pxxysecondhand.service.IPictureService;
import com.pxxysecondhand.utils.FastDFSClient;
import com.pxxysecondhand.utils.PictureResult;


@Service
public class PictureServiceImpl implements IPictureService {
	//图片服务器IP地址
	@Value("${IMAGE_SERVER_IP}")
	private String IMAGE_SERVER_IP;

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IPictureService#uploadFile(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public PictureResult uploadFile(MultipartFile mulitpartFile) {
		// TODO Auto-generated method stub
		//1.判断图片是否为空
		 PictureResult pictureResult = new PictureResult();
		  if(mulitpartFile==null) {
			  pictureResult.setError(1);
			  pictureResult.setMessage("请上传图片");
			  return pictureResult;
		  }
		 //2.判断图片尺寸是否合格
		  
		 //3.上传图片
		  try {
			  //取得前缀
			 String filename = mulitpartFile.getOriginalFilename();
			 String extName = filename.substring(filename.lastIndexOf(".")+1);
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:/resources/config/client.conf");
			String str = fastDFSClient.uploadFile(mulitpartFile.getBytes(), extName);
			 pictureResult.setError(0);
			  pictureResult.setUrl("http://"+IMAGE_SERVER_IP+"/"+str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
			  pictureResult.setError(1);
			  pictureResult.setMessage("图片上传失败");
		}
		return pictureResult;
	
	}

}
