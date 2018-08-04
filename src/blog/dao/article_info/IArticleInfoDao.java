package blog.dao.article_info;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import blog.view.ArticleInfo;

@Repository
public interface IArticleInfoDao {
	
	/**
	 * 查询所有文章
	 */
	List<ArticleInfo> list(Map<String, Object> param);
	
	
	/**
	 * 根据主键查询单个文章的信息
	 * @param id
	 * @return
	 */
	ArticleInfo selectById(String id);

	/**
	 * 新增文章
	 * @param articleInfo
	 */
	int insert(ArticleInfo articleInfo);

	/**
	 * 更新文章
	 * @param articleInfo
	 * @return
	 */
	int update(ArticleInfo articleInfo);

	/**
	 * 批量更新
	 * @param param
	 */
	void batchUpdate(Map<String, Object> param);

	/**
	 * 根据文章主键批量删除文章
	 * @param idArr
	 */
	void batchDelete(@Param("idArr") String[] idArr);

	/**
	 * 根据文章分类，查询文章的数量
	 * @param typeIdArr 分类id数组
	 * @param status 文章的状态
	 * @return
	 */
	int countByTypeIdArr(@Param("typeIdArr") String[] typeIdArr, @Param("status") String status);

	/**
	 * 根据文章分类批量删除回收站的文章
	 * @param typeIdArr 分类id数组
	 */
	void batchDeleteByTypeIdArr(@Param("typeIdArr") String[] typeIdArr);

	/**
	 * 浏览量自增
	 * @param id 主键
	 * @param nViewCount 浏览量
	 */
	void updateViewCount(@Param("id") String id, @Param("viewCount") int nViewCount);


}
