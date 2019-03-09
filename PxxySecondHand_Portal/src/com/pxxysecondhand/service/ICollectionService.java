/**   
* @Title: ICollectionService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��2�� ����5:09:45 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import javax.servlet.http.HttpServletRequest;

import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.tempPojo.MyCollections;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
public interface ICollectionService {
	
	//�ñ��ҵ��ղ�����ղ��˾�ȡ��û�ղ��ղ�
	public CommonResult changeMyCollection(User user , String itemId);
	
	//��ѯ�Ƿ��ղ����������
	public boolean checkCollected(String collectorId,String itemId);
	
	//������е��ղ��б�
	public SearchResult<MyCollections> getAllOfMyCollections(HttpServletRequest request,String collectorId,int page,int rows);
}
