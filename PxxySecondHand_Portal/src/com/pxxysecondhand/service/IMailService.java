/**   
* @Title: IMailService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��19�� ����3:58:49 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
public interface IMailService {
	
	CommonResult sendMailToWebMaster(User user , String msg);
	
}
