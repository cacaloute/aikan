
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新书征集</title>
	<link type="text/css" rel="styleSheet" href="${contextPath}/styles/titleimg.css">
	<style type="text/css">
		body{
			font-size:14px;
			text-align:center;
			font-family:STXingkai;
		}
		a{
   			text-decoration: none;
   			color: #232323;
		}
		a:hover{
			color:#f26552;
		}
		h1{
			margin: 10px auto;
			text-align: center;
			color:#C2C287;
		}
		.btn{
			width: 150px;
			height: 50px;
			margin: 5px 10px;
		}
	</style>

	<!--   引入jQuery，注意版本 -->
	<script type="text/javascript" language="javascript" src="${contextPath}/scripts/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="${contextPath}/scripts/titleimg.js"></script>
	<script type="text/javascript">
	//js变量保存上下文路径
	var contextPath="${contextPath}";
	//用户是否登录
	var isEmpty="${empty sessionScope.userT }";
	
	$(document).ready(function(e){
		
	});
	</script>
</head>
<body>
	<!-- 导入头文件 -->
	<%@ include file="../header.jsp" %>
	<div id="divbook" style="margin:10px auto;">
		<h1>欢迎投稿</h1>
		<form method="post" id="bookForm" style="margin:40px auto;border:3px solid orange;width:380px;height:300px;">
			<input name="authorId" type="text" value="${sessionScope.userT.userId }" style="width:300px;height:50px;display:none;"/>
			书籍名称：<input name="bookName" type="text" style="width:300px;height:50px;"/>
		<!--  	书籍地址：<input name="bookAddress" type="text" style="width:300px;height:50px;"/>  -->
			选择文件：<input id="bookFile" type="file" name="bookFile" style="width:300px;height:50px;margin:10px auto;border:1px solid orange;"/>
			书籍封面：<input id="bookImg" type="file" name="imgFile" style="width:300px;height:50px;margin:10px auto;border:1px solid orange;"/>
			
			<!-- 提交按钮 -->
			<input id="btnpost" type="button" value="投稿" class="btn"/>
			<!-- 重置按钮，注意不是清空按钮，恢复到当前页面打开时最原始的表单状态 -->
			<input type="reset" value="重置" class="btn"/>
		</form>
	</div>
	
	
</body>
</html>