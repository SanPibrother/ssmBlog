package blog.dao.user_info;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//import org.apache.ibatis.annotations.Param;

import blog.view.UserInfo;
@Repository("IUserInfoDao")
public interface IUserInfoDao {
	/**
	 * 校验用户
	 * @param loginName 登录名
	 * @param passWord 登录密码
	 * @return
	 */
	//两个或以上的参数，必须要写参数注解，不然mybatis无法识别
	UserInfo selectUser(@Param("loginName") String loginName, @Param("passWord") String passWord);

}
