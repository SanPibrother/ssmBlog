package test;

import org.junit.Test;

import blog.service.article_info.ArticleInfoService;
import blog.service.type_info.TypeInfoService;

public class TestDBUpdate {
	@Test
	public void test(){

//		 new ArticleInfoService().listNormal();
		 new TypeInfoService().list();
	}
}
