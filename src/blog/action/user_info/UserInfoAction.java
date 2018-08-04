package blog.action.user_info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;

import blog.exception.MyException;
import blog.service.user_info.UserInfoService;
import blog.view.Result;
import blog.view.UserInfo;

@Controller
@RequestMapping("admin")
public class UserInfoAction {
	private Logger log=Logger.getLogger(this.getClass());
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("index.action")
	public String index(){
		return "admin/index"; //直接由index页面跳到login页面
	}
	
	@RequestMapping("login.action")
	public String login(){
		return "admin/login"; //直接由index页面跳到login页面
	}
	
	@RequestMapping("login.json") //登录，ajax请求
	@ResponseBody
	public Result login2(ModelMap map,HttpServletRequest request) throws MyException{
		log.debug("登录开始");
		//1获取参数
		String loginName=request.getParameter("login_name");
		String passWord=request.getParameter("pass_word");
		//2校验参数
		if(StringUtils.isNullOrEmpty(loginName)||StringUtils.isNullOrEmpty(passWord))
			throw new MyException("用户名、密码不能为空");
		UserInfo userInfo=userInfoService.checkUser(loginName, passWord);
		if(userInfo==null)
			throw new MyException("用户名、密码不正确"); //抛出去，异常处理器统一处理
		//3设置session
		request.getSession().setAttribute("userInfo", userInfo);
		return Result.success();
	}
	
	@RequestMapping("login_out.action")
	public String loginout(HttpSession session){
		//销毁session
		session.invalidate();
		return "admin/login";
	}
}
