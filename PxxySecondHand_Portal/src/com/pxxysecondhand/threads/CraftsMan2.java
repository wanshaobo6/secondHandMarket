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

import com.pxxysecondhand.service.ITradeService;

/**
 * @author  
 *   ��ʱִ��ҵ���߳�
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
		//��Ա������ֵ
		this.ttl = ttl;
		this.tradeId = tradeId;
		this.operationId=operationId;
		th1 = new Thread(this);
		//��ʼ��ʱ����
		if(operationId==2){
			System.out.println("CraftsMan2"+th1.getName()+"����"+ttl/1000/60/60/24+"���޲����Զ�����ɽ���״̬���߳�");
		}
		th1.start();
	}
	@Override
	public void run() {
		try {
			System.out.println(ttl+"ms��CraftsMan2ִ��operation"+operationId);
			th1.sleep(ttl);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doSomeOperation(tradeId,operationId,ttl);
	}
	
	private void doSomeOperation(String tradeId,int operationId,int ttl) {
		//operationIdΪ1ʱ7�첻�����Զ�ȷ�ϣ�����״̬�Զ��ӽ����е�ȷ���ջ�
		if(operationId ==2){
			tradeService.autoChangeTradedToComplish(tradeId);
		}
	}

}
