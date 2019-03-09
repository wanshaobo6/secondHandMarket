/**   
* @Title: ItemCategoryImpl.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月19日 下午7:29:07 
* @version V1.0   
*/
package com.pxxysecondhand.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pxxysecondhand.mapper.ItemCatMapper;
import com.pxxysecondhand.pojo.ItemCat;
import com.pxxysecondhand.pojo.ItemCatExample;
import com.pxxysecondhand.pojo.ItemCatExample.Criteria;
import com.pxxysecondhand.service.IItemCatService;

@Service
public class ItemCatServiceImpl implements IItemCatService {
	@Autowired
	private ItemCatMapper itemCatMapper;
	

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IItemCatService#selectItemCatListByParentId(java.lang.String)
	 */
	@Override
	public List<ItemCat> selectItemCatListByParentId(String parentId) {
		// TODO Auto-generated method stub
		ItemCatExample itemCatExample = new ItemCatExample();
		Criteria create = itemCatExample.createCriteria();
		create.andParentidEqualTo(parentId);
		List<ItemCat> itemCats = itemCatMapper.selectByExample(itemCatExample);
		return itemCats;
	}


	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.IItemCatService#queryAllCatgories()
	 */
	@Override
	public List<ItemCat> queryAllCatgories() {
		// TODO Auto-generated method stub
		ItemCatExample example = new ItemCatExample();
		List<ItemCat> parentCategory = itemCatMapper.selectByExample(example);
		parentCategory.forEach(pCat -> { 
			ItemCatExample example1 = new ItemCatExample();
			example1.createCriteria().andParentidEqualTo(pCat.getId());
			List<ItemCat> itemCat = itemCatMapper.selectByExample(example1);
			pCat.setItemCat(itemCat);
		});
		return parentCategory;
	}

}
