/**   
* @Title: IItemCatService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月19日 下午7:26:39 
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
