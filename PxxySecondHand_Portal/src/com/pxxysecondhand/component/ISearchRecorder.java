/**   
* @Title: ISearchRecorder.java 
* @Package com.pxxysecondhand.component 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author ���ٲ�

* @date 2019��3��3�� ����7:58:00 
* @version V1.0   
*/
package com.pxxysecondhand.component;

import java.util.List;
import java.util.Map;

import com.pxxysecondhand.tempPojo.ScanRecord;

/**
 * @author ���ٲ�
 *
 */
public interface ISearchRecorder {
	
	//����û�������¼
	void addRecord(String keyWords , String token);
		
	
	//��ò�ѯ�����Ĺؼ���
	List<String> getPopulteKeywords();
}
