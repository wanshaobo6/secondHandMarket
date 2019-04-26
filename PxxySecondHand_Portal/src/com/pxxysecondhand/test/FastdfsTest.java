package com.pxxysecondhand.test;

/**   
* @Title: FastdfsTest.java 
* @Package com.pxxysecondhand.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月17日 下午2:18:23 
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
		//1.把FastDfs提供的jar包添加到工程中
		//2.初始化全局配置，加载一个配置文件
		ClientGlobal.init("D:\\secondHandTrade\\PxxySecondHand_Portal\\src\\resources\\config\\client.conf");
		//3.创建一个TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		//4.创建一个TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//5.声明一个StorageServer对象Null
		StorageServer storageServer = null;
		//6.获得StorageClient对象
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//7.调用StorageClient对象上传文件
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
					//一分钟内返回刚刚
					return "刚刚";
				}else {
					//1小时返回**分钟前
					long minute = interval/1000/60;
					return minute+"分前";
				}
			}else {
				//24小时返内返回**小时前
				long hour = interval/60/1000/60;
				return hour+"时前";
			}
		}else {
			//一个月返回多少天前
			if(interval<30*24*60*60*1000) {
				long day = interval/24/60/1000/60;
				return day+"天前";
			}else {
				//超过月份一律按**月前计算
				long month = interval/30/24/60/1000/60;
				return month+"月前";
			}
		}
	}

}
