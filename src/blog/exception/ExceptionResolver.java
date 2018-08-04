package blog.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

/*
 * 异常有两种，1种action跳转，另一种Ajax发送的请求
 */
public class ExceptionResolver implements HandlerExceptionResolver {
	// 系统抛出的异常
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		// 定义一个错误信息
		String message = "系统发生未知错误，请稍后再试";
		// 判断该错误是否是预期的错误
		if (ex instanceof MyException)
			message = ((MyException) ex).getMessage();

		// 判断请求类型
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 因为json请求，都有@ResponseBody,判断它是否存在
		ResponseBody responseBody = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
		if (responseBody != null) {
			// 是json请求，就返回json数据
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("code", "999999");
			responseMap.put("message", message);
			String json = new Gson().toJson(responseMap);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			try {
				response.getWriter().write(json);
				response.flushBuffer();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// 返回一个空的视图，表示已经手动生成响应，因为是ajax请求，这里不需要跳转，只需要把错误信息转为json数据发回去，ajax有自己的error()方法
			return new ModelAndView();
		}
		
		// 2.2如果是跳转页面请求，则跳转页面
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", message);
		mv.setViewName("error");
		return mv;
	}
}
