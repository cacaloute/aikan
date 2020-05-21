
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加书籍</title>
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
		h2{
			margin: 10px auto;
			text-align: center;
			color:#C2C287;
		}
		.btn{
			width: 150px;
			height: 50px;
			margin: 10px 100px;
		}
		#bookForm input{
			margin-right:20px;
			margin-bottom:20px;
			width:100px;
			height:30px;
		}
	</style>

	<!--   引入jQuery，注意版本 -->
	<script type="text/javascript" language="javascript" src="${contextPath}/scripts/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="${contextPath}/scripts/titleimg.js"></script>
	<script type="text/javascript">
	//js变量保存上下文路径
	var contextPath="${contextPath}";

	$(document).ready(function(e) {
		$("#btnpost").click(function(e) {
			doAddBook();
        });
    });
	function doAddBook(){
		alert("doAddBook内部");
		
		$("span.result").html("");
			 //会把表单以enctype="multipart/form-data"的方式提交给服务器
             var formData = new FormData($("#bookForm")[0]);
			 $.ajax({  
				  url: '${contextPath}/manager/doaddbookt.action' ,  
				  type: 'POST',  
				  data: formData,
				  dataType: "json",  
				  async: false,  
				  cache: false,  
				  contentType: false,  
				  processData: false,  
				  success: function (data) { 
						
					  	if(data.result==1){
							//alert("书籍信息添加成功！");
							$("span.result").html("书籍信息添加成功");
						}else if(data.result==-1){
							$("span.result").html("书籍编号已经存在，不能添加！");
						}else if(data.result==0){
							$("span.result").html("书籍添加失败，请重新尝试！");
						}else if(data.result==-2){
							$("span.result").html("书籍封面上传失败，请重新添加！");
						}
				  }
			 });  
	}
	</script>
</head>
<body>
	<div id="divbook" style="margin:10px auto;">
		<h2>添加书籍</h2>
		<span class="result" style="color:red;"></span>
		<form method="post" id="bookForm" style="text-align:left;margin:10px auto;border:3px solid orange;width:595px;height:460px;color:#C2C287;font-weight:bold;padding:10px 10px;">
			书籍作者：<input name="authorName" type="text" />
			书籍名称：<input name="bookName" type="text" />
			书籍字数：<input name="bookWordNums" type="text" />
			章节数目：<input name="bookChapterNums" type="text" />
			书籍种类：<select name="bookTypeId"  style="width:100px;height:40px">
			            <optgroup label="男生爱看">
			                <option value="1">玄幻</option>
			                <option value="2">修真</option>
			                <option value="3">仙侠</option>
			                <option value="4">言情</option>
			                <option value="5">历史</option>
			                <option value="6">文学</option>
			                <option value="7">悬疑</option>
			                <option value="8">科普</option>
			            </optgroup>   
			            <optgroup label="女生爱看">
			                <option value="10">玄幻</option>
			                <option value="20">修真</option>
			                <option value="30">仙侠</option>
			                <option value="40">言情</option>
			                <option value="50">历史</option>
			                <option value="60">文学</option>
			                <option value="70">悬疑</option>
			                <option value="80">科普</option>
			            </optgroup>
			        </select><br/>
			是否免费：<input type="radio" name="isFree" value="是" checked>免费
				   <input type="radio" name="isFree" value="否">不免<br/>
			书籍状态：<input type="radio" name="bookState" value="完结" checked>完结 
				   <input type="radio" name="bookState" value="连载">连载    <br/>
 <!-- 点击次数 -->	   <input name="bookClickNums" type="text" value="0" style="display:none;" />
	  		
			书籍封面：<input id="bookImg" type="file" name="imgFile" style="width:500px;height:50px;margin:10px auto;border:1px solid orange;"/><br/>
	  		书籍简介： <textarea id="textarea" name="bookInfor" rows="5"  warp="virtual" maxlength="400" placeholder="简介字数不得超过400字" style="width:550px;height:90px;"></textarea>
		
			
			<!-- 提交按钮 -->
			<input id="btnpost" type="button" value="添加" class="btn"/>
			<!-- 重置按钮，注意不是清空按钮，恢复到当前页面打开时最原始的表单状态 -->
			<input type="reset" value="重置" class="btn"/>
		</form>
	</div>

</body>
</html>