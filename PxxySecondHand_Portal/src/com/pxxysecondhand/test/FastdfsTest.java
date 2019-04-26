package com.pxxysecondhand.test;

/**   
* @Title: FastdfsTest.java 
* @Package com.pxxysecondhand.test 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��17�� ����2:18:23 
* @version V1.0   
*/

import java.util.Date;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

/**
 * @author  
 *
 */
public class FastdfsTest {
	public void testUpload() throws Exception {
		//1.��FastDfs�ṩ��jar����ӵ�������
		//2.��ʼ��ȫ�����ã�����һ�������ļ�
		ClientGlobal.init("D:\\secondHandTrade\\PxxySecondHand_Portal\\src\\resources\\config\\client.conf");
		//3.����һ��TrackerClient����
		TrackerClient trackerClient = new TrackerClient();
		//4.����һ��TrackerServer����
		TrackerServer trackerServer = trackerClient.getConnection();
		//5.����һ��StorageServer����Null
		StorageServer storageServer = null;
		//6.���StorageClient����
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//7.����StorageClient�����ϴ��ļ�
		String[] strings = storageClient.upload_file("C:\\Users\\ \\Pictures\\Camera Roll\\WIN_20170128_14_18_37_Pro.jpg", "jpg", null);
		for(String string:strings) {
			System.out.println(string);
		}
	}
	
	@Test
	public  String getTimeIntervalInImpreciseWay() {
		Date date = new Date("2018-11-21 19:57:44");
		if(date==null)
			return null;
		Date now = new Date();
		long interval = now.getTime()-date.getTime(); 
		if(interval<24*60*60*1000) {
			if(interval<60*60*1000) {
				if(interval<60*1000) {
					//һ�����ڷ��ظո�
					return "�ո�";
				}else {
					//1Сʱ����**����ǰ
					long minute = interval/1000/60;
					return minute+"��ǰ";
				}
			}else {
				//24Сʱ���ڷ���**Сʱǰ
				long hour = interval/60/1000/60;
				return hour+"ʱǰ";
			}
		}else {
			//һ���·��ض�����ǰ
			if(interval<30*24*60*60*1000) {
				long day = interval/24/60/1000/60;
				return day+"��ǰ";
			}else {
				//�����·�һ�ɰ�**��ǰ����
				long month = interval/30/24/60/1000/60;
				return month+"��ǰ";
			}
		}
	}

}
