/**   
* @Title: ItemCatCatController.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��19�� ����7:01:16 
* @version V1.0   
*/
package com.pxxysecondhand.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pxxysecondhand.pojo.ItemCat;
import com.pxxysecondhand.service.IItemCatService;
import com.pxxysecondhand.utils.JsonUtils;

/**
 * @author  
 *
 */
@Controller
@RequestMapping("/itemcat")
public class ItemCatController {
	@Autowired
	private IItemCatService itemCatService;
	

	/**
	 * ���ݸ������ѯ�ӷ���
	* @Title: ItemCatCatController.java 
	* @Package com.pxxysecondhand.controller 
	* @Description: TODO(��һ�仰�������ļ���ʲô) 
	* @author  
	* @date 2018��11��19�� ����7:42:07 
	* @version V1.0
	 */
	@RequestMapping("/selectItemCatListByParentId")
	@ResponseBody
	public List<ItemCat> selectItemCatListByParentId(@RequestParam(defaultValue="0")String parentId){
		List<ItemCat> children = itemCatService.selectItemCatListByParentId(parentId);
		return children;
	}
	
	@RequestMapping(value="queryAllCatgories",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String queryAllCatgories(){
		return JsonUtils.objectToJson(itemCatService.queryAllCatgories());
	}
}
