package blog.service.user_info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.user_info.IUserInfoDao;
import blog.view.UserInfo;

@Service("UserInfoService")
public class UserInfoService {
	@Autowired
	private IUserInfoDao iUserInfoDao;
	/*
	 *校验用户登录
	 */
	public UserInfo checkUser(String loginName,String passWord){
		return iUserInfoDao.selectUser(loginName, passWord);
	}	
}
