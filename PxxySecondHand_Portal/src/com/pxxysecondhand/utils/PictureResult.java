/**   
* @Title: PictureResult.java 
* @Package com.pxxysecondhand.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月17日 下午5:25:43 
* @version V1.0   
*/
package com.pxxysecondhand.utils;

/**
 * @author  
 *
 */
public class PictureResult {
	
	private int error;
	private String url;
	private String message;
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "PictureResult [error=" + error + ", url=" + url + ", message=" + message + "]";
	}
	

}
