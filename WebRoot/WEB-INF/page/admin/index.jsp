<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
</head>

<body>
	<!--左侧菜单-->
	<div class="admin-left">
		<!-- 左菜单 -->
		<div id="menu">
			<c:import url="menu.jsp"></c:import>
		</div>
	</div>
	
	<!--右侧内容-->
	<div class="admin-right">
		<!--头部-->
		<div id="header">
			<c:import url="header.jsp"></c:import>
		</div>
	</div>
</body>
<!--该函数保证左边菜单能点开 -->
<script>
	javaex.menu({
		id : "menu",	// 菜单id
		isAutoSelected : true
	});
</script>
</html>
