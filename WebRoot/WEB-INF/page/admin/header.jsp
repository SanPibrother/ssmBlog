<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!--字体图标--><!-- 配置了项目名后，就不用在SpringMvC以及web.xml中配置-->
<link href="${pageContext.request.contextPath }/static/javaex/pc/css/icomoon.css" rel="stylesheet" />
<!--动画-->
<link href="${pageContext.request.contextPath }/static/javaex/pc/css/animate.css" rel="stylesheet" />
<!--骨架样式-->
<link href="${pageContext.request.contextPath }/static/javaex/pc/css/common.css" rel="stylesheet" />
<!--皮肤（缇娜）-->
<link href="${pageContext.request.contextPath }/static/javaex/pc/css/skin/tina.css" rel="stylesheet" />
<!--jquery，不可修改版本-->
<script src="${pageContext.request.contextPath }/static/javaex/pc/lib/jquery-1.7.2.min.js"></script>
<!--全局动态修改-->
<script src="${pageContext.request.contextPath }/static/javaex/pc/js/common.js"></script>
<!--核心组件-->
<script src="${pageContext.request.contextPath }/static/javaex/pc/js/javaex.min.js"></script>
<!--表单验证-->
<script src="${pageContext.request.contextPath }/static/javaex/pc/js/javaex-formVerify.js"></script>


<div class="admin-header">
	<a href="#">
		<img class="logo-img" src="${pageContext.request.contextPath}/static/javaex/pc/images/logo.png">
	</a>
	<ul class="pull-right header-right">

		<li class="menu-title">
			<h1>SanPiBrother的Blog</h1>
		</li>
		<li><a class="pull-left avatar" href="#"><img
				src="${pageContext.request.contextPath}/static/javaex/pc/images/user.jpg" alt=""></a><!-- 头像 -->
			<p class="pull-left margin-left-10">
				欢迎您，<span>管理员</span>
			</p> <label class="margin-left-10 margin-right-10">|</label> <a
			href="${pageContext.request.contextPath}/admin/login_out.action">退出</a>
		</li>
		<li><a href="${pageContext.request.contextPath}/portal/index.action">首页</a></li>
	</ul>
</div>