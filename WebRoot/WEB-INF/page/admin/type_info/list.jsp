<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>文章列表</title>
</head>

<body>
	<!--左侧菜单-->
	<div class="admin-left">
		<!-- 左菜单 -->
		<div id="menu">
			<c:import url="../menu.jsp"></c:import>
		</div>
	</div>

	<!--右侧头部-->
	<div class="admin-right">
		<!--头部-->
		<div id="header">
			<c:import url="../header.jsp"></c:import>
		</div>
	</div>

	<!--右侧内容-->
	<div class="admin-right">
		<!--内容-->
		<div class="admin-content">

			<div class="content-header">
				<div class="breadcrumb">
					<span>分类管理</span> <span class="divider">/</span> <span
						class="active">文章分类</span>
				</div>
			</div>

			<!--全部主体内容-->
			<div class="list-content">
				<!--块元素-->
				<div class="block">
					<!--页面有多个表格时，可以用于标识表格-->
					<h2>文章分类列表</h2>
					<!--右上角的返回按钮-->
					<a href="javascript:history.back();">
						<button class="button wathet"
							style="position: absolute;right: 20px;top: 16px;">
							<span class="icon-arrow_back"></span> 返回
						</button>
					</a>

					<!--正文内容-->
					<div class="main">
						<!--表格上方的搜索操作-->
						<div style="text-align:right;margin-bottom:10px;">
							<div class="input-group">
								<input type="text" class="text" placeholder="提示信息" />
								<button class="button blue">搜索</button>
							</div>
						</div>

						<!--表格上方的操作元素，添加、删除等-->
						<div class="operation-wrap">
							<div class="buttons-wrap">
								<button id="add" class="button blue">
									<span class="icon-plus"></span> 添加
								</button>
								<button id="save" class="button green">
									<span class="icon-border_color"></span> 保存
								</button>
								<button id="delete" class="button red">
									<span class="icon-minus"></span> 删除
								</button>
								
							</div>
						</div>
						<table id="table" class="table color2">
								<thead>
									<tr>
										<th style="width:20px;" align="center"><input type="checkbox" class="fill listen-1"/> </th>
										<th style="width:30%;" align="center">显示排序</th>
										<th align="center">名称</th>
									</tr>
								</thead>
								<tbody >
									<c:forEach items="${list}" var="entity" varStatus="status" >
										<tr >
											<td ><input type="checkbox"   class="fill listen-1-2" name="id" value="${entity.id}" /> </td><!--id放在复选框的位置，只是没显示eryi-->
											<td align="center"><input type="text" style="width:50%;" class="text" name="sort" data-type="正整数" error-msg="必须输入正整数" value="${entity.sort}" /></td>
											<td align="center"  ><input type="text" style="width:40%;text-align:center" class="text" name="name" data-type="必填" placeholder="请输入分类名称" value="${entity.name}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var idArr=new Array();
	var sortArr=new Array();
	var nameArr=new Array();
	//动态添加tr行数据
	$("#add").click(function(){
		var html='';
		html +='<tr>';
		html +='<td><input type="checkbox" class="fill listen-1-2" name="id" value="${entity.id}" /> </td>';
		html +='<td><input type="text" style="width:50%;" class="text" name="sort" data-type="正整数" error-msg="必须输入正整数" value="${entity.sort}" /></td>';
		html +='<td><input type="text" class="text" name="name" data-type="必填" placeholder="请输入分类名称" value="${entity.name}" /></td>';
		html +='</tr>';
		$("#table tbody").append(html);
		javaex.render();/*重新渲染功能 */
	});
	
	
</script>
<script>
	//保存按钮点击事件，保存数据
		$("#save").click(function() {
		if (javaexVerify()) {
			idArr = [];
			sortArr = [];
			nameArr = [];
			
			// id
			$(':checkbox[name="id"]').each(function() {
				idArr.push($(this).val());
			});
			
			// sort
			$('input[name="sort"]').each(function() {
				sortArr.push($(this).val());
			});
			
			// name
			$('input[name="name"]').each(function() {
				nameArr.push($(this).val());
			});
			
			console.log("idArr:"+idArr);
			console.log("sortArr:"+sortArr);
			console.log("nameArr:"+nameArr);
			$.ajax({
				url : "save.json",
				type : "POST",
				dataType : "json",
				traditional : "true",//开启传输数组的支持
				data : {
					"idArr" : idArr,
					"sortArr" : sortArr,
					"nameArr" : nameArr
				},
			success : function(rtn) {
				if(rtn.code=="000000"){
					//成功后弹窗提示
					javaex.optTip({
						content : rtn.message,
						type : "success"
					});
					setTimeout(function(){
						//延迟2s刷新页面
						window.location.reload();
					}, 2000);
					
				}
				
			},
			error : function(rtn) {
				javaex.optTip({
					content :rtn.message,
					type : "error"
				});
			}
		});
		}
	});
	// 点击删除按钮事件
	$("#delete").click(function() {
		idArr = [];
		// 1.0 遍历所有被勾选的复选框
		$(':checkbox[name="id"]:checked').each(function() {
			// 2.0 添加主键存在的记录
			var id = $(this).val();
			if (id!="") {
				idArr.push(id);
			}
		});
	// 判断所勾选的是不是新增的空白记录
	if (idArr.length==0) {
		$(':checkbox[name="id"]:checked').each(function() {
			// 前台无刷新去除新增的tr
			$(this).parent().parent().parent().remove();
		});
	} else {
		$.ajax({
			url : "delete.json",
			type : "POST",
			dataType : "json",
			traditional : "true",
			data : {
				"idArr" : idArr
			},
			success : function(rtn) {
					if (rtn.code=="000000") {
						javaex.optTip({
							content : rtn.message
						});
						// 建议延迟加载
						setTimeout(function() {
							// 刷新页面
							window.location.reload();
						}, 1000);
				} else {
						javaex.optTip({
							content : rtn.message,
							type : "error"
						});
					}
				}
			});
		}
	});			
		
		

</script>

<!--该函数保证左边菜单能点开 -->
<script>
	javaex.menu({
		id : "menu",	// 菜单id
		isAutoSelected : true
	});
</script>
