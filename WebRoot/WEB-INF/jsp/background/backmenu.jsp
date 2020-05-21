
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
	a{
		text-decoration:none;
	}
	ul,li{
		list-style-type:none;
		margin:0;
		padding:0;
	}
	.first{
		font-size:20px;
		font-weight:bold;
		background-color:#FFF0F5;
		width:300px;
		line-height:45px;
		text-align:center;
		margin-top:0px;
	}
	.second{
		font-size:16px;
		background-color:#FAF4FF;
		width:300px;
		line-height:35px;
		text-align:center;
		margin:0px auto;
	}

</style>
</head>

<body>
	<!-- 导入头文件 -->
	<%@ include file="header2.jsp" %>
	<c:choose>
		<c:when test="${empty sessionScope.managerT }">	
			<div style="margin:200px 450px 100px 580px;font-size:29px;">
				<a href="${contextPath}/background.jsp"><span>管理员未登录,点此登录</span></a>
			</div>	
			
		</c:when>
		<c:otherwise>
			<table border="1" style="width:100%;height:580px;">
				<tr>
					<!-- menu开始 -->
					<td style="width:20%;">
						<ul>
							<c:forEach var="firstRoleAuthor" items="${sessionScope.managerT.menuTree['0']}">
								<li>
									<c:choose>
										<c:when test="${firstRoleAuthor.auType==0 }">
										<div class="first"> ${firstRoleAuthor.auName }${firstRoleAuthor.auUrl}</div>	
										</c:when>
										<c:otherwise>
											<a href="${contextPath}${firstRoleAuthor.auUrl}" target="right">${firstRoleAuthor.auName }</a>
										</c:otherwise>
									</c:choose>
									
									<ul>
										<c:forEach var="secondRoleAuthor" items="${sessionScope.managerT.menuTree[firstRoleAuthor.auIdString]}">
											<li>
												<c:choose>
													<c:when test="${secondRoleAuthor.auType==0 }">
														${secondRoleAuthor.auName }${secondRoleAuthor.auUrl}
													</c:when>
													<c:otherwise>
													<div class="second"><a href="${contextPath}${secondRoleAuthor.auUrl}" target="right">${secondRoleAuthor.auName }</a> </div>
													</c:otherwise>
												</c:choose>
											</li>
										</c:forEach>
									</ul>
								</li>
							</c:forEach>
						</ul>
					</td>
					<!-- menu结束 -->
					
					<!-- content开始 -->
					<td>
						<iframe name="right" src="${contextPath}/manager/tosearchbookt.action" frameborder="0" width="100%" height="100%"></iframe>
					</td>
					<!-- content结束 -->
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	
</body>
</html>