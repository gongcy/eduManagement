package com.zslin.basic.auth.intercepter;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zslin.basic.auth.annotations.Token;
import com.zslin.basic.tools.TokenTools;

/**
 * 防止重复提交的拦截器
 * @author niit216.com
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			try {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				Method method = handlerMethod.getMethod();
				//获取自定义的注释
				Token annotation = method.getAnnotation(Token.class);
				if (annotation != null) {
					//如果标记为准备工作，则创建防重标记存入到session中
					if (annotation.flag().equals(Token.READY)) {
						request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
					}
					//如果标记为检查，则判断是否为重复提交
					if (annotation.flag().equals(Token.CHECK)) {
						if (isRepeatSubmit(request)) {
							request.setAttribute(TokenTools.REP_SESSION_NAME, true);
						}
						request.getSession(false).removeAttribute("token");
					}
				}
			} catch (Exception e) {
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}
	
	/**
	 * 判断是否为重复提交
	 * @param request
	 * @return
	 */
	private boolean isRepeatSubmit(HttpServletRequest request) {
		//获取session中保存的防重标记
		String serverToken = (String) request.getSession(false).getAttribute("token");
		//如果session中的标记为空，则判断为重复提交
		if (serverToken == null) {
			return true;
		}
		//获取请求中传入的防重标记
		String clinetToken = request.getParameter("token");
		//如果请求中传入的标记为空，也判断为重复提交
		if (clinetToken == null) {
			return true;
		}
		//如果session中的标记与请求中的标记不同，也判断为重复提交
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}
}
