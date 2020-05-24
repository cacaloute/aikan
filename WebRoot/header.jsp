
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>头部</title>
<link href="${contextPath}/styles/company.css" rel="stylesheet" type="text/css" />
	<style>
		body #tou{
			width:100%; 
			height:60px; 
			line-height:60px; 
			font: 14px Arial,"微软雅黑";
			color: #111;
			box-shadow: 3px 3px 3px 3px #888888;
			
		}
		body #tou #left{
			width:25%; 
			height:50px; 
			text-align:center;
			float:left;
			margin-top:15px;
		}
	  	body #tou .time1{
			width:30%; 
			line-height:55px; 
			text-align:left;
			float:left;
		}
		#third{
			width:500px;
			height:40px;
			text-align:center;
			line-height:0px;
			float:right;
		}
		#third span{
			display-line:block;
			margin:35px 40px;
			
		}
		a:hover{
			color:#f26552;
		}
  	</style>

	<script language="javascript">

  		var theader = null;
  		theader = setTimeout(time,1000);//开始执行
 		function time()
 		{
			clearTimeout(theader);//清除定时器
     		dt = new Date();
			var y=dt.getYear()+1900;
			var mm=dt.getMonth()+1;
			var d=dt.getDate();
			var weekday=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
			var day=dt.getDay();
        	var h=dt.getHours();
        	var m=dt.getMinutes();
        	var s=dt.getSeconds();

			if(h<10){h="0"+h;}
			if(m<10){m="0"+m;}
			if(s<10){s="0"+s;}
        	document.getElementById("timeShow").innerHTML =  "现在的时间为："+y+"年"+mm+"月"+d+"日"+weekday[day]+" "+h+":"+m+":"+s+"";
        	theader = setTimeout(time,1000); //设定定时器，循环执行           
   			 }
  		</script>
	</head>
	<body>
		<div id="tou">
			<div id="left">
				<iframe id="fancybox-frame" name="fancybox-frame1521513593400" frameborder="0" scrolling="no" hspace="0"  					
					src="http://i.tianqi.com/index.php?c=code&a=getcode&id=34&h=25&w=280" style="height:50px">
				</iframe>
			</div>
			<div id="third">	
				<c:choose>
					<c:when test="${empty sessionScope.userT }">	
						<h4><a href="${contextPath}/toindex.action"><span>首页</span></a>
							<a href="${contextPath}/manager/tologin.jsp"><span>我的书架</span></a>
							<a href="${contextPath}/manager/tologin.jsp"><span>登录</span></a>
							<a href="${contextPath}/manager/toregister.jsp"><span>注册</span></a>
						</h4>
					</c:when>
					<c:otherwise>
						<h4><a href="${contextPath}/toindex.action"><span>首页</span></a>
							<a href="${contextPath}/manager/userT/tobookshelf.action"><span>我的书架</span></a>
							<a href="${contextPath}/manager/userT/dologout.action"><span><img width="25px" style="border-radius:50%" height="25px" src="${contextPath}/${sessionScope.userT.userImg}"/></span><span>${sessionScope.userT.userName}</span></a>
						</h4>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="timeShow" class="time1"></div>
		</div>
	</body>
</html>