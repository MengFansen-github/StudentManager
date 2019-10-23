package com.mfs.programmer.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mfs.programmer.entity.User;

import net.sf.json.JSONObject;
/**
 * 	登录拦截器
 * @author ThinkPad
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String url = request.getRequestURI();
		//System.out.println("进入拦截器:url="+url);
		Object user =request.getSession().getAttribute("user");
		if (user == null) {
			if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				//ajax请求
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "登录状态已失效，请重新登录");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//表示未登录或者是登录失效
			request.getSession().getAttribute(request.getContextPath()+"/stsyem/login");
			return false;
		}
		return true;
	}

}
