package blog.action.article_info;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import blog.service.article_info.ArticleInfoService;
import blog.service.type_info.TypeInfoService;
import blog.view.ArticleInfo;
import blog.view.Result;
import blog.view.TypeInfo;


@Controller
@RequestMapping("article_info")
public class ArticleInfoAction {
	@Autowired
	private  ArticleInfoService articleInfoService;
	@Autowired
	private TypeInfoService typeInfoService;
	
	/**
	 * 查询所有正常文章(不在回收站),每页显示3条，默认第1页
	 */
	@RequestMapping("list_normal.action")
	public String listNormal(ModelMap map,
			@RequestParam(required = false, value = "typeId") String typeId,
			@RequestParam(required = false, value = "startDate") String startDate,
			@RequestParam(required = false, value = "endDate") String endDate,
			@RequestParam(required = false, value = "keyWord") String keyWord,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="3") int pageSize) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("typeId", typeId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		if (!StringUtils.isEmpty(keyWord)) {
			param.put("keyWord", "%" + keyWord.trim() + "%");
		}
		
		param.put("status", "1");
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleInfo> list = articleInfoService.list(param);
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(list);
		map.put("pageInfo", pageInfo);
		
		// 查询所有文章分类
		map.put("typeList", typeInfoService.list());
		
		map.put("typeId", typeId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("keyWord", keyWord);
		
		return "admin/article_info/list_normal";
	}
	
	/**
	 * 查询所有文章（回收站）
	 * @return
	 */
	@RequestMapping("list_recycle.action")
	public String listRecycle(ModelMap map,
			@RequestParam(required = false, value = "typeId") String typeId,
			@RequestParam(required = false, value = "startDate") String startDate,
			@RequestParam(required = false, value = "endDate") String endDate,
			@RequestParam(required = false, value = "keyWord") String keyWord,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="3") int pageSize) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("typeId", typeId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		if (!StringUtils.isEmpty(keyWord)) {
			param.put("keyWord", "%" + keyWord.trim() + "%");
		}
		
		param.put("status", "0");
		
		// pageHelper分页插件
		// 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleInfo> list = articleInfoService.list(param);
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(list);
		map.put("pageInfo", pageInfo);
		
		// 查询所有文章分类
		map.put("typeList", typeInfoService.list());
		
		map.put("typeId", typeId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("keyWord", keyWord);
		
		return "admin/article_info/list_recycle";
	}
	
	/**
	 * 文章编辑
	 */
	@RequestMapping("edit.action")
	public String edit(ModelMap map,
			@RequestParam(required = false, value = "id") String id) {
		
		// 查询单个文章的信息
		if (!StringUtils.isEmpty(id)) {
			ArticleInfo articleInfo = articleInfoService.selectById(id);
			map.put("articleInfo", articleInfo);
		}
		// 查询所有文章分类
		map.put("typeList", typeInfoService.list());
		map.put("id", id);
		return "admin/article_info/edit";
	}
	

	/**
	 * 保存文章
	 */
	@RequestMapping("save.json")
	@ResponseBody
	public Result save(ArticleInfo articleInfo){
		articleInfoService.save(articleInfo);
		return Result.success();
		
	}
	
	
	/**
	 * 批量移动文章到某个分类
	 * @param idArr 主键数组
	 * @param typeId 文章分类id
	 */
	@RequestMapping("move.json")
	@ResponseBody
	public Result move(@RequestParam(value="idArr") String[] idArr,
			@RequestParam(value="typeId")String typeId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("idArr", idArr);
		param.put("typeId", typeId);
		articleInfoService.batchUpdate(param);
		return Result.success();
		
	}
	
	/**
	 * 批量更新文章的状态
	 * @param idArr
	 * @param typeId
	 * @return
	 */
	@RequestMapping("update_status.json")
	@ResponseBody
	public Result recycle(@RequestParam(value="idArr") String[] idArr,
			@RequestParam(value="status")String status){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("idArr", idArr);
		param.put("status", status);
		articleInfoService.batchUpdate(param);
		return Result.success();
		
	}
	/**
	 * 彻底批量删除文章
	 * @param idArr 主键id
	 * @return
	 */
	@RequestMapping("delete.json")
	@ResponseBody
	public Result delete(
			@RequestParam(value="idArr")String[]idArr){
		
		articleInfoService.batchDelete(idArr);
		return Result.success();
	}
	
	

	
	
	/**
	 * 上传文件到磁盘（物理路径）,图片
	 * @throws IOException 
	 */
	@RequestMapping("upload.json")
	@ResponseBody
	public Result upload(MultipartFile file, HttpServletRequest request) throws IOException {
		
		// 文件原名称
		String szFileName = file.getOriginalFilename();
		// 重命名后的文件名称
		String szNewFileName = "";
		// 根据日期自动创建3级目录
		String szDateFolder = "";
		
		// 上传文件
		if (file!=null && szFileName!=null && szFileName.length()>0) {
			Date date = new Date();
			szDateFolder = new SimpleDateFormat("yyyy/MM/dd").format(date);
			// 存储文件的物理路径
			String szFilePath = "F:\\upload\\" + szDateFolder;
			// 自动创建文件夹
			File f = new File(szFilePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			
			// 新的文件名称
			szNewFileName = UUID.randomUUID() + szFileName.substring(szFileName.lastIndexOf("."));
			// 新文件
			File newFile = new File(szFilePath+"\\"+szNewFileName);
			
			// 将内存中的数据写入磁盘
			file.transferTo(newFile);
		}
	
		return Result.success().add("imgUrl", "http://localhost:8080/upload/"+szDateFolder+"/"+szNewFileName);
	}
}
