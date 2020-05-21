
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>尾部</title>

	<style>
		#wei{
				width:100%; 
				background-color:#FBFBFB; 
				line-height:26px; 
				font: 16px Arial,"微软雅黑";
				box-shadow: -1px -1px 1px 1px #888888;
			}
			#firwei{
				text-align:center;
			}
			#secwei{
				text-align:center;
				line-height:38px;
			}#thrwei{
				text-align:center;
				line-height:38px;
			}
			
		ul,ol{ 
				list-sytle:none;	
			}
			a {
    			text-decoration: none;
    			color:#232323;
			}
			li{
				display:inline-block;
				width:80px;
				line-height:38px;
				text-align:center;
			}
			#secwei img{
				width:20px;
				height:20px;
			}
  		</style>
		 <script language="javascript">

  		</script>
	</head>
	<body>
		<div id="wei">
			<div id="firwei">
			    <ul>
				<li><a href="${contextPath}/toindex.action">爱看首页</a></li>
				<li>|</li>
				<li><a href="${contextPath}/manager/tologin.jsp">我的书架</a></li>
				<li>|</li>
				<li><a href="${contextPath}/manager/friend.jsp">书友圈</a></li>
				<li>|</li>
				<li><a href="${contextPath}/manager/menu.jsp">悬疑</a></li>
				<li>|</li>
				<li><a href="${contextPath}/manager/menu.jsp">言情</a></li>
				<li>|</li>
				<li><a href="${contextPath}/manager/menu.jsp">玄幻</a></li>
				<li>|</li>
				<li><a href="${contextPath}/manager/menu.jsp">修真</a></li>
				<li>|</li>
				<li><a href="${contextPath}/manager/menu.jsp">文学</a></li>
				<li>|</li>
				<li><a href="${contextPath}/manager/menu.jsp">历史</a></li>
				<li>|</li>
				<li><a href="${contextPath}/manager/menu.jsp">科幻</a></li>
				<li>|</li>
				<li><a href="${contextPath}/manager/menu.jsp">仙侠</a></li>
			   </ul>
			</div>
			<div id="secwei">
				出版物经营许可证&emsp;  京ICP备11008516号&emsp;  网络出版服务许可证（总）网出证（京）字第141号 &emsp;京ICP证090653号  <img src="${contextPath}/images/an.png"/>京公网安备11010502030452
			</div>
			<div id="thrwei">
				2018 All Rights Reserved 爱看小说阅读网站版权所有<br/>
				<a href="${contextPath}/background.jsp">后台管理</a>
			</div>
		</div>
	</body>
</html>