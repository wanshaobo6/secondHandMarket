/**   
* @Title: TradeInterceptor.java 
* @Package com.pxxysecondhand.interceptor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月6日 下午4:47:56 
* @version V1.0   
*/
package com.pxxysecondhand.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.ICommentService;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
public class TradeInterceptor implements HandlerInterceptor{
	@Autowired
	private ICommentService commentService;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	//拦截未登录的用户
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		// TODO Auto-generated method stub
		//1.验证用户身份
				User user = commentService.checkIsLogin(request);
				if(user==null) {
					request.setAttribute("prePage","userCenter");
					request.getRequestDispatcher("/login.html").forward(request, response);
					return false;
				}
		return true;
	}

}
