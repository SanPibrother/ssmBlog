<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="menu" class="menu">
	<div class="logo">
		<h1>菜单</h1>
	</div>
	<ul>
		<li class="menu-item">
			<a href="javascript:;"><span class="icon-tags"></span>分类管理<i class="my-icon menu-more"></i></a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/type_info/list.action"><span class="icon-tasks"></span>&nbsp 文章分类</a></li>
			</ul>
		</li>
		<li class="menu-item">
			<a href="javascript:;"><span class="icon-book" ></span>文章管理<i class="my-icon menu-more"></i></a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/article_info/list_normal.action"><span class="icon-folder-open-o"></span>文章列表</a></li>
				<li><a href="${pageContext.request.contextPath}/article_info/list_recycle.action"><span class="icon-delete_forever"></span>回收站</a></li>
			</ul>
		</li>
	</ul>
</div>
