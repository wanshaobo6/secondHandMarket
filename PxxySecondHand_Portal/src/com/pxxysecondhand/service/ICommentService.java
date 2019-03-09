/**   
* @Title: ICommentService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��30�� ����5:04:13 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.tempPojo.ItemDescComment;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
public interface ICommentService {
	
	public List<ItemDescComment> getCommentByItemId(String itemId);
	
	public User checkIsLogin(HttpServletRequest request);
	
	public CommonResult publicMyComment(User user,String itemId , String parentId , String Content);
	
	public CommonResult deleteMyComment(User user,String commentId);
	
	//�����۱�����һЩ����
	public void doSomeOperationToComments(List<ItemDescComment> commentList);
	
	public int countCommentNumByItemId(String itemid);
	
	public int countCommentNotReadByItemId(String itemId);

	public void deleteCommentsByItemId(String itemId);
}
