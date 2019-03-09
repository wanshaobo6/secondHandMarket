/**   
* @Title: ISearchRecorder.java 
* @Package com.pxxysecondhand.component 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 万少波

* @date 2019年3月3日 下午7:58:00 
* @version V1.0   
*/
package com.pxxysecondhand.component;

import java.util.List;
import java.util.Map;

import com.pxxysecondhand.tempPojo.ScanRecord;

/**
 * @author 万少波
 *
 */
public interface ISearchRecorder {
	
	//添加用户搜索记录
	void addRecord(String keyWords , String token);
		
	
	//获得查询量最多的关键词
	List<String> getPopulteKeywords();
}
