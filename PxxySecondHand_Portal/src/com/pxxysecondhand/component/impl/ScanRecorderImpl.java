/**   
* @Title: ScanRecorderImpl.java 
* @Package com.pxxysecondhand.component.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月26日 下午8:08:25 
* @version V1.0   
*/
package com.pxxysecondhand.component.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.pxxysecondhand.component.IScanRecorder;
import com.pxxysecondhand.tempPojo.ScanRecord;

/**
 * @author  
 *
 */
public class ScanRecorderImpl implements IScanRecorder{
	public ScanRecorderImpl(Map<String, Map<String, List<ScanRecord>>> record, boolean isFetch) {
		super();
		this.record = record;
		this.isFetch = isFetch;
	}



	public ScanRecorderImpl() {
		super();
		// TODO Auto-generated constructor stub
	}



	//用户token 浏览的商品id 
	private Map<String,Map<String,List<ScanRecord>>> record = new HashMap();
	
	//是否拉取浏览记录
	private boolean isFetch = true;
	
	
	public boolean isFetch() {
		return isFetch;
	}



	public Map<String, Map<String, List<ScanRecord>>> getRecord() {
		return record;
	}



	public void setRecord(Map<String, Map<String, List<ScanRecord>>> record) {
		this.record = record;
	}



	public void setFetch(boolean isFetch) {
		this.isFetch = isFetch;
	}



	/* (non-Javadoc)
	 * @see com.pxxysecondhand.component.IScanRecorder#addRecord(java.lang.String, com.pxxysecondhand.tempPojo.ScanRecord)
	 */
	@Override
	public void addRecord(String token, ScanRecord scanRecord) {
		// TODO Auto-generated method stub
		Map<String,List<ScanRecord>> childlist = record.get(token);
		//如果该用户以前不存在
		if(childlist==null) {
			childlist = new HashMap();  //存在
		}
		List<ScanRecord> grandChild = childlist.get(scanRecord.getItemId());
		//以前没浏览过这件item
		if(grandChild==null) {
			grandChild = new ArrayList();
		}
		//添加该记录
		grandChild.add(scanRecord);
		//添加该商品的浏览记录
		childlist.put(scanRecord.getItemId(), grandChild);
		//添加该cookie的所有商品浏览记录
		record.put(token, childlist);
	}
	
	

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.component.IScanRecorder#deleteRecord(java.lang.String)
	 */
	@Override
	public void deleteRecord(String Token) {
		// TODO Auto-generated method stub
		record.remove(Token);
	}



	/* (non-Javadoc)
	 * @see com.pxxysecondhand.component.IScanRecorder#getScanNumByItemId(java.lang.String)
	 */
	@Override
	public Integer getScanNumByItemId(String itemId) {
		// TODO Auto-generated method stub
		Integer num = 0;
		for (Map<String,List<ScanRecord>> map : record.values()) {
			List<ScanRecord> list = map.get(itemId);
			if(list!=null)
				num++;
		}
		return num;
	}



	/* (non-Javadoc)
	 * @see com.pxxysecondhand.component.IScanRecorder#getItemRecordMapByGuessToken()
	 */
	@Override
	public Map<String, List<ScanRecord>> getItemRecordMapByGuessToken(String Guess_token) {
		// TODO Auto-generated method stub
		return record.get(Guess_token);
	}
 
}
