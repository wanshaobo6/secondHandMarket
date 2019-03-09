package com.pxxysecondhand.component.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.pxxysecondhand.component.ICommonOperationTimer;
import com.pxxysecondhand.component.SMSCodeTimer;
/**
 * ����ģʽ��ֻ֤��һ��ע����
 * xml�ļ�ע��Spring������
 * @author  
 *
 */
public class CommonOperationTimerImpl implements ICommonOperationTimer{
	
	
	private Map<String,Map<String,Long>> commonRegister = new HashMap();
	
	//���ܻ����֤����
	public boolean canIDoAgain(String name, String thing , long timeSecondInterval) {
		// TODO Auto-generated method stub
		Map<String,Long> chidMap = commonRegister.get(thing);
		if(chidMap == null) 
	    	return true;
		Long timemill = chidMap.get(name);
		if(timemill == null)
			return true;
		Long now = new Date().getTime();
		long second = (now - timemill)/1000;
		System.out.println("CommonOperation���β���ʱ����"+second);
		//�ϴβ������ܳ���timeSecondInterval��
		if(second>=timeSecondInterval) { //------------------
			return true;
		}else {
			return false;
		}
	}
	
	//�Ҹո�����
	public void IHaveDoSomeThing(String name,String thing) {
		// TODO Auto-generated method stub
		Map<String, Long> childMap = commonRegister.get(thing);
		if(childMap ==null || childMap.size() ==0) {
			//map����Ԫ��
			childMap = new HashMap<String, Long>();
		}
		childMap.put(name, new Date().getTime());
		commonRegister.put(thing, childMap);
	}

}
