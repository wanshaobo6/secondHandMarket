/**   
* @Title: CraftsMan.java 
* @Package com.pxxysecondhand.threads 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��6�� ����5:03:23 
* @version V1.0   
*/
package com.pxxysecondhand.threads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pxxysecondhand.service.ITradeService;

/**
 * @author  
 *   ��ʱִ��ҵ���߳�
 */
public class CraftsMan1 implements Runnable{
	
	@Autowired
	private ITradeService tradeService;
	
	//�����Զ�����ʱ��
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
		//��Ա������ֵ
		this.ttl = ttl;
		this.tradeId = tradeId;
		this.operationId=operationId;
		//��ʼ��ʱ����
		th1 = new Thread(this);
		if(operationId==1) {
			System.out.println("CraftsMan1"+th1.getName()+"����"+ttl/1000/60/60/24+"���޲����Զ���ȷ���ջ�״̬���߳�");
		}else if(operationId==2){
			System.out.println("CraftsMan1"+th1.getName()+"����"+ttl/1000/60/60/24+"���޲����Զ�����ɽ���״̬���߳�");
		}
		th1.start();
	}

	@Override
	public void run() {
		try {
			System.out.println(ttl+"ms��CraftsMan1ִ��operation"+operationId);
			th1.sleep(ttl);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doSomeOperation(tradeId,operationId,ttl);
	}
	
	private void doSomeOperation(String tradeId,int operationId,int ttl) {
		//operationIdΪ1ʱ7�첻�����Զ�ȷ�ϣ�����״̬�Զ��ӽ����е�ȷ���ջ�
		if(operationId ==1) {
			int i = tradeService.autoChangeTradingToTraded(tradeId);
			if(i == 1) 
			  craftsMan2.startTheTask(tradeId,2, AUTO_COMMENT_TIME);
		}else if(operationId ==2){
			 tradeService.autoChangeTradedToComplish(tradeId);
		}
	}

}
