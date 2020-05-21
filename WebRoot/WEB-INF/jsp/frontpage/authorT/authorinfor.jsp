
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>作者空间</title>
	<link type="text/css" rel="styleSheet" href="${contextPath}/styles/titleimg.css">
	<style type="text/css">
		body{
				font: 14px Arial,"微软雅黑";
				text-align:center;
			}
		
		ul,ol{
			 
			list-sytle:none;
		}

		a{
   			text-decoration: none;
   			color: #232323;
		}
		a:hover{
			color:#f26552;
		}
		#divtable{
			text-align:left;
			padding:10px auto;
		}
		#divtable tr td span{
			font-size:20px;
			font-weight:bold;
		}
		#booktable tr td span{
			display:inline-block;
			color:#999;
			font-size:16px;
			width:150px;
		}
	</style>

	<!--   引入jQuery，注意版本 -->
	<script type="text/javascript" language="javascript" src="${contextPath}/scripts/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="${contextPath}/scripts/titleimg.js"></script>
	<script type="text/javascript">
	//js变量保存上下文路径
	var contextPath="${contextPath}";
	
	$(document).ready(function(e) {
	
		
    });


	</script>
</head>
<body>
	<!-- 导入头文件 -->
	<%@ include file="../../../../header.jsp" %>
	
	<div id="divtable">
		<table width="100%">
			<tr>
				<td width="20%" rowspan="4">		
					<img width="200px" height="300px" src="${contextPath}/images/author/${requestScope.authorT.authorImg }"/>
				</td>
				<td>
					<span>姓名 ：</span>${requestScope.authorT.authorName }<br/>
				</td>
			</tr>
			<tr>
				<td>
					<span>作品数 ：</span>${requestScope.authorT.authorWorksNums }
				</td>
			</tr>
			<tr>
				<td>
					<span>粉丝数 ：</span>${requestScope.authorT.fansNums }<br/>
				</td>
			</tr>
			<tr>
				<td>
					<span>作品字数 ：</span>${requestScope.authorT.authorWordNums }
				</td>
			</tr>
			<tr>
				<td>
					<span>作者简介 ：</span><span style="font-size:16px;font-weight:normal;">${requestScope.authorT.authorInfor }</span>
				</td>
			</tr>
		</table>
	</div>
	<h3>所有作品：</h3>
	<div>
		<c:forEach items="${requestScope.booklists }" var="bookT">
			<table id="booktable" style="text-align:left;">
				<tr>
					<td>
						<span><img width="150px" height="200px" src="${contextPath}/images/bookface${bookT.bookImg }"/></span><br/>
						<span>书名：${bookT.bookName }</span><br/>
						<span>状态：${bookT.bookState }</span><br/>
						<span>种类：${bookT.bookType.bookTypeName }</span><br/>
					</td>
					<td>
						<td><span style="width:300px;font-size:14px;font-weight:normal;">简介：${bookT.bookInfor }</span>
					</td>
				</tr>
				
				
			</table>
				
		</c:forEach>
	</div>
	
	<!-- 导入尾文件 -->
	<%@ include file="../../../../footer.jsp" %>
</body>
</html>