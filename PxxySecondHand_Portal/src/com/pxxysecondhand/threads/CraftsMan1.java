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
import org.springframework.beans.factory.annotation.Value;

import com.pxxysecondhand.service.ITradeService;

/**
 * @author  
 *   定时执行业务线程
 */
public class CraftsMan1 implements Runnable{
	
	@Autowired
	private ITradeService tradeService;
	
	//到期自动评价时间
	@Value("${AUTO_COMMENT_TIME}")
	private Integer AUTO_COMMENT_TIME;
	
	@Autowired
	private CraftsMan2 craftsMan2;
	
	private Thread th1;
	
	private int ttl;
	
	private String tradeId;
	
	private int operationId;
	
	public CraftsMan1() {
		
	}
	
	public void startTheTask(String tradeId,int operationId,int ttl) {
		//成员变量赋值
		this.ttl = ttl;
		this.tradeId = tradeId;
		this.operationId=operationId;
		//开始定时任务
		th1 = new Thread(this);
		if(operationId==1) {
			System.out.println("CraftsMan1"+th1.getName()+"开启"+ttl/1000/60/60/24+"天无操作自动到确认收货状态的线程");
		}else if(operationId==2){
			System.out.println("CraftsMan1"+th1.getName()+"开启"+ttl/1000/60/60/24+"天无操作自动到完成交易状态的线程");
		}
		th1.start();
	}

	@Override
	public void run() {
		try {
			System.out.println(ttl+"ms后CraftsMan1执行operation"+operationId);
			th1.sleep(ttl);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doSomeOperation(tradeId,operationId,ttl);
	}
	
	private void doSomeOperation(String tradeId,int operationId,int ttl) {
		//operationId为1时7天不操作自动确认，交易状态自动从交易中到确认收货
		if(operationId ==1) {
			int i = tradeService.autoChangeTradingToTraded(tradeId);
			if(i == 1) 
			  craftsMan2.startTheTask(tradeId,2, AUTO_COMMENT_TIME);
		}else if(operationId ==2){
			 tradeService.autoChangeTradedToComplish(tradeId);
		}
	}

}
