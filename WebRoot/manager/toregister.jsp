
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
<link href="${contextPath}/styles/company.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	body{
	
		font-family:STXingkai;
	}
	div#divForm{
		width: 400px;
		/* height: 1500px;*/
		margin: 10px auto;
		font-size:20px;
		overflow:hidden;
	}
	div#divForm label,input{
		width: 360px;
		height: 50px;
		margin: 5px auto;
		font-size:20px;
	}
	.btn{
		width: 150px;
		height: 50px;
		margin: 5px 10px;
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
	
       $("#btnRegister").click(function(e) {
		doRegisterUser();			
       });

});


function doRegisterUser(){
	//alert("doRegisterUser()内部");
	$("span.result").html("");
		//$("#divFormErrors").html("");
		 //FormData是js对象
		 //会把表单以enctype="multipart/form-data"的方式提交给服务器
         var formData = new FormData($("#registerForm")[0]);
		// var userId=$("#userId").val();
		 $.ajax({  
			  url: '${contextPath}/manager/userT/doregisterajax.action' ,  
			  type: 'POST',  
			  data: formData,
			  dataType: "json",  
			  async: false,  
			  cache: false,  
			  contentType: false,  
			  processData: false,  
			  success: function (data) { 
				  
				  	if(data.result==1){
						//$("span.result").html("用戶信息添加成功！");
						alert("用戶注册成功！");
						window.location.href="${contextPath}/manager/home.jsp";
					}else if(data.result==-1){
						$("span.result").html("用戶名已经存在，请换个名称注册！");
					}else if(data.result==0){
						$("span.result").html("用戶注册失败，请重新尝试！");
					}else if(data.result==-2){
						$("span.result").html("用戶头像上传失败，请重新添加！");
					}
			  }
		 });  
}
</script>
</head>
<body>
	<div id="divForm">
		<h3><img src="${contextPath}/images/icare.PNG" alter="logo" width=210px,height=110px/></h3>
		<h3>->您的贴心管家！</h3>
		<h1>欢迎来到爱看书屋</h1>
		<span class="error"></span>
		<span class="result" style="color:red;font-size:16px;font-weight:bold"></span>
		<form id="registerForm" method="post" onSubmit="return false;"> 	
			<fieldset>
				<legend>新用户注册</legend>

				<label for="userName">姓名：</label>
				<input id="userName" type="text" name="userName"/>
				<br />

				<!-- type="password"，呈现的就是密码框 -->
				<label for="userPassword">密码：</label>
				<input id="userPassword" type="password" name="userPassword" />
				
				<label for="userSex">性别：</label>
				<input id="userSex" type="text" name="userSex" />
				
				<label for="userEmail">Email：</label>
				<input id="userEmail" type="text" name="userEmail"/>
		
				<label for="imgFile">头像：</label>
				<input id="userImg" type="file" name="imgFile" />
		
				<hr />

				<!-- 要想提交form表单中的数据，现阶段，只能通过点击form表单中的提交按钮 -->
				<input id="btnRegister" type="button" value="提交" class="btn"/>

				<!-- 重置按钮，注意不是清空按钮，恢复到当前页面打开时最原始的表单状态 -->
				<input type="reset" value="重置" class="btn"/>

			</fieldset>
		</form>
	</div>
</body>
</html>