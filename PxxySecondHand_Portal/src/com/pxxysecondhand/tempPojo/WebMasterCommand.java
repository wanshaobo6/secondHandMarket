/**   
* @Title: WebMasterCommand.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��21�� ����1:07:40 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Arrays;

/**
 * @author  
 *  վ������
 *
 */
public class WebMasterCommand {
	
	private String methodName;
	
	private Object[] args;

	@Override
	public String toString() {
		return "WebMasterCommand [methodName=" + methodName + ", args=" + Arrays.toString(args) + "]";
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	

}
