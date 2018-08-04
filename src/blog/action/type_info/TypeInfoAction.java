package blog.action.type_info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;

import blog.exception.MyException;
import blog.service.article_info.ArticleInfoService;
import blog.service.type_info.TypeInfoService;
import blog.view.Result;
import blog.view.TypeInfo;
import blog.view.UserInfo;

@Controller
@RequestMapping("type_info")
public class TypeInfoAction {
	@Autowired
	private  TypeInfoService typeInfoService;
	@Autowired
	private ArticleInfoService articleInfoService;
	
	/**
	 * 查询所有文章分类
	 * @param map
	 * @return
	 */
	@RequestMapping("list.action")
	public String list(ModelMap map){
		List<TypeInfo> list=typeInfoService.list();
		map.put("list", list);
		return "admin/type_info/list";
		
	}
	
	/**
	 * 保存分类标签
	 * @param idArr
	 * @param sortArr
	 * @param nameArr
	 * @return
	 */
	@RequestMapping("save.json") 
	@ResponseBody
	public Result save(
			@RequestParam(value="idArr")String[]idArr,
			@RequestParam(value="sortArr")String[]sortArr,
			@RequestParam(value="nameArr")String[] nameArr) {
	
		typeInfoService.save(idArr,sortArr,nameArr);
		return Result.success();
	}
	
	/**
	 * 删除分类
	 * @param idArr
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping("delete.json") 
	@ResponseBody
	public Result delete(
			@RequestParam(value="idArr")String[]idArr) throws MyException {
		
		typeInfoService.delete(idArr);
		return Result.success();
	}
	
}
