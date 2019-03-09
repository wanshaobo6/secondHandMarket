/**   
* @Title: IMessageServcie.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��19�� ����7:29:25 
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
	
	//��õ�userIdΪ��ʱ����ο���Ϣ  ��Ϊʱ�����û���Ϣ
	public SearchResult<WebDynamicMessage> getWebMessageAutomatically(String userId , int page , int size);

	public TempMessage getMessageById(User user, String messageId) throws Exception;
}
