/**   
* @Title: IItemService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��19�� ����8:36:08 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pxxysecondhand.pojo.Comment;
import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.ItemCat;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.tempPojo.ItemDescResult;
import com.pxxysecondhand.tempPojo.MyPublic;
import com.pxxysecondhand.tempPojo.NewestPublic;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
public interface IItemService {
	//
	public Item getItemByItemId(String itemId);
	//���
	public CommonResult addItem(HttpServletRequest request , HttpServletResponse response , Item item,String token);
	
	//��������֤��
	public boolean validateFormDate(Item item);
	
	//���ұ�������
	public ItemDescResult showItem(String id);
	
	//�鿴����ʱ��¼����
	public void addTheRecord(HttpServletRequest request,HttpServletResponse response,String itemId);
	
	//��ʾ�ҵķ���
	public SearchResult<MyPublic> showMyPublic(User user,int page,int rows,HttpServletRequest request);
	
	//�޸����õ�״̬
	public int changeItemIsInTrade(String itemId,Integer isInTrade);
	
	//������·�����size������
	public List<NewestPublic> getNewestPublic(int size);
	
	//���鵱ǰ�û��Ƿ��в���Ȩ��
	public User checkSellerByItemId(HttpServletRequest request, String itemId);
	
	//���ʱ�� ��������
	public CommonResult polishItem(String itemId);
	
	//�ı����¼�״̬
	public CommonResult changeupanddown(String itemId);

	//ɾ������
	public boolean deleteItem(String itemId) throws Exception;
	
	//����GuessToken ͬʱ������ص�¼����Id,δ��¼����GuessToken
	public String createGuessTokenIfNotExist(HttpServletRequest request , HttpServletResponse response );
	


}
