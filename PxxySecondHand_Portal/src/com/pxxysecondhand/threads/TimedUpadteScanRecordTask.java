/**   
* @Title: TimedTask.java 
* @Package com.pxxysecondhand.threads 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2019��1��3�� ����1:07:28 
* @version V1.0   
*/
package com.pxxysecondhand.threads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pxxysecondhand.component.IScanRecorder;
import com.pxxysecondhand.component.JedisClient;
import com.pxxysecondhand.tempPojo.ScanRecord;
import com.pxxysecondhand.utils.JsonUtils;

/**  ��ʱ����
 * @author  
 *
 */
public class TimedUpadteScanRecordTask  implements Runnable{
	
	
	private Thread thread ;
	
	@Autowired
	private IScanRecorder ScanRecorder; 
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_SCAN_RECORD_NAME}")
	private String REDIS_SCAN_RECORD_NAME;
	
	@Value("${NEXT_UPDATE_TIME_INTERVAL}")
	private Long NEXT_UPDATE_TIME_INTERVAL;
	
	public TimedUpadteScanRecordTask() {
		thread = new Thread(this);
		thread.start();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ScanRecorder.isFetch()) {
					//ȡ����
				System.out.println("��ȡ�û��������ing...");
				String str = jedisClient.get(REDIS_SCAN_RECORD_NAME);
				Map<String, Map<String, List<ScanRecord>>> newrecord = null;
				if(!StringUtils.isEmpty(str)) {
					 newrecord = JsonUtils.nativeRead(str, new TypeReference<Map<String,Map<String,List<ScanRecord>>>>() {
					});
				}
				if(newrecord == null) {
					newrecord = new HashMap();
				}
				ScanRecorder.setRecord(newrecord);
				ScanRecorder.setFetch(false);
			}else {
				//������
				System.out.println("�����û��������ing...");
				Map<String, Map<String, List<ScanRecord>>> record = ScanRecorder.getRecord();
				jedisClient.set(REDIS_SCAN_RECORD_NAME, JsonUtils.objectToJson(record));
			}
			try {
				thread.sleep(NEXT_UPDATE_TIME_INTERVAL);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
