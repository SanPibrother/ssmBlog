package blog.dao.type_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import blog.view.TypeInfo;
@Repository("ITypeInfoDao")
public interface ITypeInfoDao {
	
	
	/**
	 * 查询所有文章分类
	 * @return
	 */
	List<TypeInfo> list();
	
	
	/**
	 * 插入一条数据
	 * @param sort 排序用
	 * @param name 分类的名称
	 * 
	 */
	int insert(@Param("sort")String sort, @Param("name")String name);
	
	
	/**
	 * 更新一条数据
	 * @param id 主键
	 * @param sort 排序用
	 * @param name 分类的名称
	 * 
	 */
	int update(@Param("id")String id,@Param("sort")String sort, @Param("name")String name);
	
	
	/**
	 * 批量删除
	 * @param idArr 主键数组
	 * 如果不加参数注解，在xml中的collection后面就不能写idArr，如果为数组，就行array，集合就写list
	 */
	void delete(@Param("idArr")String[] idArr);
	
	/**
	 * 根据主键查询文章类别
	 * @param typeId
	 * @return
	 */
	
	TypeInfo selectById(String id);
}
