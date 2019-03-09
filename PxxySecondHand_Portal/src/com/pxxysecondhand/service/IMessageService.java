/**   
* @Title: IMessageServcie.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月19日 下午7:29:25 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import java.util.List;

import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.Message;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.tempPojo.TempMessage;
import com.pxxysecondhand.tempPojo.WebDynamicMessage;

/**
 * @author  
 *
 */
public interface IMessageService {
	
	//获得当userId为空时获得游客消息  不为时候获得用户消息
	public SearchResult<WebDynamicMessage> getWebMessageAutomatically(String userId , int page , int size);

	public TempMessage getMessageById(User user, String messageId) throws Exception;
}
