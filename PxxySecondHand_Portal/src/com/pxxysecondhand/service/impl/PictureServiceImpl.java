/**   
* @Title: PictureServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��17�� ����5:39:38 
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
	//ͼƬ������IP��ַ
	@Value("${IMAGE_SERVER_IP}")
	private String IMAGE_SERVER_IP;

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IPictureService#uploadFile(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public PictureResult uploadFile(MultipartFile mulitpartFile) {
		// TODO Auto-generated method stub
		//1.�ж�ͼƬ�Ƿ�Ϊ��
		 PictureResult pictureResult = new PictureResult();
		  if(mulitpartFile==null) {
			  pictureResult.setError(1);
			  pictureResult.setMessage("���ϴ�ͼƬ");
			  return pictureResult;
		  }
		 //2.�ж�ͼƬ�ߴ��Ƿ�ϸ�
		  
		 //3.�ϴ�ͼƬ
		  try {
			  //ȡ��ǰ׺
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
			  pictureResult.setMessage("ͼƬ�ϴ�ʧ��");
		}
		return pictureResult;
	
	}

}
