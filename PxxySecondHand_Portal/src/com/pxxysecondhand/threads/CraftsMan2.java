/**   
* @Title: CraftsMan.java 
* @Package com.pxxysecondhand.threads 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月6日 下午5:03:23 
* @version V1.0   
*/
package com.pxxysecondhand.threads;

import org.springframework.beans.factory.annotation.Autowired;

import com.pxxysecondhand.service.ITradeService;

/**
 * @author  
 *   定时执行业务线程
 */
public class CraftsMan2 implements Runnable{
	
	@Autowired
	private ITradeService tradeService;
	
	private Thread th1;
	
	private int ttl;
	
	private String tradeId;
	
	private int operationId;
	
	public CraftsMan2() {
		
	}
	
	public void startTheTask(String tradeId,int operationId,int ttl) {
		//成员变量赋值
		this.ttl = ttl;
		this.tradeId = tradeId;
		this.operationId=operationId;
		th1 = new Thread(this);
		//开始定时任务
		if(operationId==2){
			System.out.println("CraftsMan2"+th1.getName()+"开启"+ttl/1000/60/60/24+"天无操作自动到完成交易状态的线程");
		}
		th1.start();
	}
	@Override
	public void run() {
		try {
			System.out.println(ttl+"ms后CraftsMan2执行operation"+operationId);
			th1.sleep(ttl);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doSomeOperation(tradeId,operationId,ttl);
	}
	
	private void doSomeOperation(String tradeId,int operationId,int ttl) {
		//operationId为1时7天不操作自动确认，交易状态自动从交易中到确认收货
		if(operationId ==2){
			tradeService.autoChangeTradedToComplish(tradeId);
		}
	}

}
