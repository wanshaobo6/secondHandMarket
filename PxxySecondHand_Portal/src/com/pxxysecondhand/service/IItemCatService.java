/**   
* @Title: IItemCatService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��19�� ����7:26:39 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import java.util.List;

import com.pxxysecondhand.pojo.ItemCat;

/**
 * @author  
 *
 */
public interface IItemCatService {

	
	public List<ItemCat> selectItemCatListByParentId(String parentId);


	public List<ItemCat> queryAllCatgories();
	
	
}
