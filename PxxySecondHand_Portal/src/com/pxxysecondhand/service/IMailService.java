/**   
* @Title: IMailService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月19日 下午3:58:49 
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
