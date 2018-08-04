package blog.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import blog.view.UserInfo;
/*
 * 拦截器，拦截不登录直接登录后台的url
 */
public class BlogInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String url=request.getRequestURI();
		//对特殊地址放行
		if(url.indexOf("login")>0||url.indexOf("portal")>0){ //如果请求是登录，就放行
			return true;
		}
		HttpSession session=request.getSession();
		UserInfo userInfo=(UserInfo) session.getAttribute("userInfo");
		if(userInfo!=null){ //如果session中身份存在，放行
			return true;
		}
		//这里身份验证失败，重新跳到登录页面
		request.getRequestDispatcher("/WEB-INF/page/admin/login.jsp").forward(request, response);
		//拦截，不往下执行
		return false;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		
		
	}
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

}
