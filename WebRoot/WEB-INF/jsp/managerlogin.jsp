
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="${contextPath}/styles/company.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	body{
	
		font-family:STXingkai;
	}
	div#divForm{
		width: 400px;
		height: 500px;
		margin: 10px auto;
		font-size:20px;
	}
	div#divForm label,input{
		width: 170px;
		height: 50px;
		margin: 5px auto;
		font-size:20px;
	}
	
	h3{
		margin: 10px auto;
		text-align: center;
		color:#616130;
	}
	h1{
		margin: 10px auto;
		text-align: center;
		color:#C2C287;
		
	}
	span.error{
		color: red;
	}
</style>
<!--   引入jQuery，注意版本 -->
<script type="text/javascript" language="javascript" src="${contextPath}/scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(e) {
		
        $("#btnLogin").click(function(e) {
			
			$.post("${contextPath}/dologinajax.action",
			   	   $("#loginForm").serialize(),
			       function(data){
					   	//alert(data.result);
					   	if(data.result==true){
					   		//window.history.back();
					   		//alert("登录成功，正在跳转！")
							window.location.href="${contextPath}/manager/index.jsp";
						}else{
							$("span.error").html("账号或密码有误，登录失败！");
						}
					},"json");
        });
    });
</script>
</head>
<body>
	<c:choose>
		<c:when test="${empty sessionScope.managerT }">
	<div id="divForm">
		<h3><img src="${contextPath}/images/icare.PNG" alter="logo" width=210px,height=110px/></h3>
		<h3>->您的贴心管家！</h4>
		<h1>欢迎来到爱看书屋</h3>
		<span class="error"></span>
		<form id="loginForm" method="post"> 	
			<fieldset>
				<legend>管理员登录</legend>

				<label for="managerName">姓名：</label>
				<input id="managerName" type="text" name="managerName" style="width:270px"/>
				<br />

				<!-- type="password"，呈现的就是密码框 -->
				<label for="managerPassword">密码：</label>
				<input id="managerPassword" type="password" name="managerPassword" style="width:270px"/>
		
				<hr />

				<!-- 要想提交form表单中的数据，现阶段，只能通过点击form表单中的提交按钮 -->
				<input id="btnLogin" type="button" value="登录" />

				<!-- 重置按钮，注意不是清空按钮，恢复到当前页面打开时最原始的表单状态 -->
				<input type="reset" value="重置" />

			</fieldset>
		</form>
	</div>
		</c:when>
		<c:otherwise>
			<h3>您已登录，请进入<a href="${contextPath}/manager/index.jsp">后台系统</a>！</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>