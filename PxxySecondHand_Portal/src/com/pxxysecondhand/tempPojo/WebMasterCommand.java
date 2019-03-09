/**   
* @Title: WebMasterCommand.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月21日 下午1:07:40 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Arrays;

/**
 * @author  
 *  站长命令
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
