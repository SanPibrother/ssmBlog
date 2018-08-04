package blog.service.type_info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import blog.dao.article_info.IArticleInfoDao;
import blog.dao.type_info.ITypeInfoDao;
import blog.exception.MyException;
import blog.view.TypeInfo;

@Service("TypeInfoService")
public class TypeInfoService {
	@Autowired
	private ITypeInfoDao iTypeInfoDao;
	@Autowired
	private IArticleInfoDao iArticleInfoDao;
	
	/**
	 * 查询所有文章分类
	 * @return
	 */
	public List<TypeInfo> list(){
		return iTypeInfoDao.list();
	}
	
	/**
	 * 批量更新，插入文章分类
	 */
	public void save(String[] idArr, String[] sortArr, String[] nameArr) {
		//遍历第一个数组
		for(int i=0;i<idArr.length;i++){
			//判断这条数据需要更新，还是插入
			if(StringUtils.isEmpty(idArr[i])){
				//没有id，就是需要插入
				iTypeInfoDao.insert(sortArr[i], nameArr[i]);
			}else{
				//更新
				iTypeInfoDao.update(idArr[i],sortArr[i],nameArr[i]);
			}
		}
		
	}

	public void delete(String[] idArr) throws MyException {
		// 判断该文章分类id有没有被使用(只判断非回收站的文章，删除一个被使用的分类，会导致垃圾数据)
		int count = iArticleInfoDao.countByTypeIdArr(idArr, "1");
		if (count > 0) {
			// 被占用，禁止删除
			throw new MyException("存在已被使用的分类，无法删除");

		}
		//先删除该分类下所有回收站文章
		iArticleInfoDao.batchDeleteByTypeIdArr(idArr);
		//删除该分类
		iTypeInfoDao.delete(idArr);

	}
	/**
	 * 根据主键查询文章类别对象
	 * @param typeId
	 * @return
	 */
	public TypeInfo selectById(String typeId) {
		
		return iTypeInfoDao.selectById(typeId);
	}

}
