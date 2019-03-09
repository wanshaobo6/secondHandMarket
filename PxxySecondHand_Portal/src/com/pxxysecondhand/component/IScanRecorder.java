/**   
* @Title: ScanerRecorder.java 
* @Package com.pxxysecondhand 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��26�� ����7:57:16 
* @version V1.0   
*/
package com.pxxysecondhand.component;

import java.util.List;
import java.util.Map;

import com.pxxysecondhand.tempPojo.ScanRecord;

/**
 * @author  
 *  �û������¼��¼��
 */
public interface IScanRecorder {
	
	
	//����û������¼
	void addRecord(String token, ScanRecord scanneRecord);
	
	//�鿴�û������������Ʒ
	Map<String,List<ScanRecord>> getItemRecordMapByGuessToken(String Guess_token);
	
	//�鿴�����������
	Integer getScanNumByItemId(String itemId);

	//�û���ݹ��������¼����ɾ��
	void deleteRecord(String Token);
	
	public boolean isFetch();
	
	public Map<String, Map<String, List<ScanRecord>>> getRecord();
	
	public void setRecord(Map<String, Map<String, List<ScanRecord>>> record);
	
	public void setFetch(boolean isFetch);
}
