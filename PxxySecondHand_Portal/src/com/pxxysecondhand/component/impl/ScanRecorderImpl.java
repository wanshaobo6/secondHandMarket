/**   
* @Title: ScanRecorderImpl.java 
* @Package com.pxxysecondhand.component.impl 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��26�� ����8:08:25 
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



	//�û�token �������Ʒid 
	private Map<String,Map<String,List<ScanRecord>>> record = new HashMap();
	
	//�Ƿ���ȡ�����¼
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
		//������û���ǰ������
		if(childlist==null) {
			childlist = new HashMap();  //����
		}
		List<ScanRecord> grandChild = childlist.get(scanRecord.getItemId());
		//��ǰû��������item
		if(grandChild==null) {
			grandChild = new ArrayList();
		}
		//��Ӹü�¼
		grandChild.add(scanRecord);
		//��Ӹ���Ʒ�������¼
		childlist.put(scanRecord.getItemId(), grandChild);
		//��Ӹ�cookie��������Ʒ�����¼
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
