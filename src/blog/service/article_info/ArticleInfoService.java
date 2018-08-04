package blog.service.article_info;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import blog.dao.article_info.IArticleInfoDao;
import blog.view.ArticleInfo;

@Service("ArticleInfoService")
public class ArticleInfoService {
	@Autowired
	IArticleInfoDao iArticleInfoDao;
	

	/**
	 * 查询所有文章
	 */
	public List<ArticleInfo> list(Map<String, Object> param) {
		
		return iArticleInfoDao.list(param);
	}
	/**
	 * 查询单个文章的信息
	 * @parame id 主键
	 * 有个bug，后台更新的时候调用这个，会导致浏览量加1
	 */
	public ArticleInfo selectById(String id) {
		
		ArticleInfo articleInfo =iArticleInfoDao.selectById(id);
		if(articleInfo!=null){
			//获取当前浏览量
			int nViewCount=articleInfo.getViewCount();
			//浏览量自增
			nViewCount++;
			//更新浏览量
			iArticleInfoDao.updateViewCount(id,nViewCount);
		}
		 return articleInfo;
	}
	/**
	 * 保存文章,编辑文章
	 */
	public void save(ArticleInfo articleInfo) {
		
		SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-dd");
		String now=format.format(new Date());
		//判断是更新还是
		if(StringUtils.isEmpty(articleInfo.getId())){
			//无id，即为新增,该对象一部分属性，需要赋值
			articleInfo.setStatus(1);
			articleInfo.setUpdateTime(now);
			articleInfo.setViewCount(1);
			iArticleInfoDao.insert(articleInfo);
		}else{
			//更新
			articleInfo.setUpdateTime(now);
			 iArticleInfoDao.update(articleInfo);
		}
		
		
	}
	/**
	 * 批量移动文章到某个分类
	 * @param idArr 主键数组
	 * @param typeId 文章分类id
	 */
	public void batchUpdate(Map<String, Object> param) {
		iArticleInfoDao.batchUpdate(param);
		
	}
	/**
	 * 批量删除文章
	 * @param idArr 主键数组
	 */
	public void batchDelete(String[] idArr) {
		iArticleInfoDao.batchDelete(idArr);
		
	}
	/**
	 * 查询某个Type_id下的文章数量
	 * @param idArr
	 * @return
	 */
	public int countByTypeIdArr(String[] TypeIdArr,String status) {
		
		return iArticleInfoDao.countByTypeIdArr(TypeIdArr, status);
	}

}
