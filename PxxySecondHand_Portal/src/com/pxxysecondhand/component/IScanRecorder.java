/**   
* @Title: ScanerRecorder.java 
* @Package com.pxxysecondhand 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月26日 下午7:57:16 
* @version V1.0   
*/
package com.pxxysecondhand.component;

import java.util.List;
import java.util.Map;

import com.pxxysecondhand.tempPojo.ScanRecord;

/**
 * @author  
 *  用户浏览记录记录器
 */
public interface IScanRecorder {
	
	
	//添加用户浏览记录
	void addRecord(String token, ScanRecord scanneRecord);
	
	//查看用户浏览的所有商品
	Map<String,List<ScanRecord>> getItemRecordMapByGuessToken(String Guess_token);
	
	//查看宝贝的浏览量
	Integer getScanNumByItemId(String itemId);

	//用户身份过期浏览记录过期删除
	void deleteRecord(String Token);
	
	public boolean isFetch();
	
	public Map<String, Map<String, List<ScanRecord>>> getRecord();
	
	public void setRecord(Map<String, Map<String, List<ScanRecord>>> record);
	
	public void setFetch(boolean isFetch);
}
