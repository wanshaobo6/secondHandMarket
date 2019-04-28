/**   
* @Title: IItemService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月19日 下午8:36:08 
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
	//添加
	public CommonResult addItem(HttpServletRequest request , HttpServletResponse response , Item item,String token);
	
	//服务器验证表单
	public boolean validateFormDate(Item item);
	
	//查找宝贝详情
	public ItemDescResult showItem(String id);
	
	//查看详情时记录下来
	public void addTheRecord(HttpServletRequest request,HttpServletResponse response,String itemId);
	
	//显示我的发布
	public SearchResult<MyPublic> showMyPublic(User user,int page,int rows,HttpServletRequest request);
	
	//修改闲置的状态
	public int changeItemIsInTrade(String itemId,Integer isInTrade);
	
	//获得最新发布的size条宝贝
	public List<NewestPublic> getNewestPublic(int size);
	
	//检验当前用户是否有操作权限
	public User checkSellerByItemId(HttpServletRequest request, String itemId);
	
	//检查时间 擦亮宝贝
	public CommonResult polishItem(String itemId);
	
	//改变上下架状态
	public CommonResult changeupanddown(String itemId);

	//删除宝贝
	public boolean deleteItem(String itemId) throws Exception;
	
	//创建GuessToken 同时如果返回登录返回Id,未登录返回GuessToken
	public String createGuessTokenIfNotExist(HttpServletRequest request , HttpServletResponse response );
	


}
