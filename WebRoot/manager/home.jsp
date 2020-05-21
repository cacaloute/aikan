
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>首页</title>
	<link type="text/css" rel="styleSheet" href="${contextPath}/styles/titleimg.css">
	<style type="text/css">
		body{
				font: 14px Arial,"微软雅黑";
				text-align:center;
			}
		body #header{
			font-size:15px;
			font-weight:bold;
			width:100%; 
			height:80px; 
			line-height:20px;
			/*border:1px solid pink; */
			margin-top:10px;
			
		}
		body #header #left{
			width:60%;
			text-align:center;
			float:left;
		}

		body #header #sec{
			width:20%;
			text-align:center;
			float:left;
			margin:10px;
		}
		
		body #bodyer{
			width:100%;
			height:1000px;
			text-align:center;
		}
		body #bodyer #titleimg{
			background-color:#F0F0F0;
			width:100%;
			margin:10px auto;
		}	
		body #bodyer #titleimg .img_box{
			width:100%;
			margin:10px auto;
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
		li{

			width:58px;
			text-align:center;
		}

		body #header #sec input {
			width: 200px;
			height: 36px;
			line-height: 26px;
			border: 1px solid #d2d2d2;
			border-radius: 50px;
			margin: 4 20px -6 10px;
			color: #9e9e9e;
			padding: 0 36px 0 12px;
			font-size: 12px;

		}
		body #header #sec s {
			width: 15px;
			height: 15px;
			cursor: pointer;
			position: absolute;
			right: 280px;
			top: 100px;
			background: url(${contextPath}/images/icon.png) no-repeat;
		}
		div#allbooks{
			margin:0px auto;
			width:1071px;
		}
		div#divTable{
			margin:0px auto;
			width:1000px;
			overflow:hidden;
		}

		div#divTable table.bookT{
			text-align:center;
			width:300px;
			height:200px;
			float:left;
			margin:15px 15px;
		}
		td.tdimg img{
			width:150px;
			height:190px;
		}
		td.tdname{
			font: 14px Arial,"微软雅黑";
			text-align:center;
			color:red;
		}
		 #logo{
			width:15%; 
			line-height:55px; 
			text-align:center;
		/* border:2px solid red; */
			float:right;
		}
		#page1 span{
			display:inline-block;
			margin:30px auto;
			width:200px;
			font-size:16px;
			font-weight:bold;
		}
		#page2{
			font-size:14px;
		}
		#page3 span{
			display:inline-block;
			margin:30px auto;
			width:30px;
		}
	</style>

	<!--   引入jQuery，注意版本 -->
	<script type="text/javascript" language="javascript" src="${contextPath}/scripts/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="${contextPath}/scripts/titleimg.js"></script>
	<script type="text/javascript">
	//js变量保存上下文路径
	var contextPath="${contextPath}";
	//声明一个全局的当前页面变量
	var pageNo=1;
	$(document).ready(function(e) {
		//默认页面加载时，自动执行一遍查询操作
		doSearchBookTs();
		
		//绑定查询按钮
		$("#btnSearch").click(function(e) {
			$("#pageNo").val("1");
            doSearchBookTs();
        });
      
    });
	
	//查询操作
	function doSearchBookTs(){
		
		$("#divFormErrors").html("");
		$.post(contextPath+"/manager/bookT/dosearchajax.action",
			   $("#searchForm").serialize(),
			   function(data){
					
					//返回了验证错误消息
				   	if(data.errors){
				   		var errorsHtml="";
				   		for(var i=0;i<data.errors.length;i++){
				   			var error=data.errors[i];
				   			errorsHtml+="<div>"+error+"</div>";
				   		}
				   		$("#divFormErrors").html(errorsHtml);
				   		return;
				   	}
			
					var bookTs=data.bookTs;
					var pageUtil=data.pageUtil;
					//记住当前页码,为了删除时服务	
					pageNo=pageUtil.pageNo;
					fillBookTs(bookTs);
					fillPageUtil(pageUtil);
				},"json");
	}
	//填充查询结果数据
	function fillBookTs(bookTs){
		
		//删除原有的书籍信息
		$("#divTable table.bookT").remove();
		for(var i=0;i<bookTs.length;i++){
			var bookT=bookTs[i];			
		
			var $divBookT=$("<table class='bookT'></table>");
			$divBookT.append("<tr><td class='tdimg' rowspan='4'><img src='"+contextPath+"/images/bookface"+bookT.bookImg+"'/></td> <td class='tdname' style='font-size:16px;font-weight:bold;font-family:STSong;color:#6A6AFF'><a href='"+contextPath+"/manager/bookT/tobookinfor.action?bookId="+bookT.bookId+"'>"+bookT.bookName+"</a></td></tr>");
		//	$divBookT.append("<span><a href='"+contextPath+"/manager/bookT/toinfo.action?bookId="+bookT.bookId+"'>"+bookT.bookId+"</a></span>");
			$divBookT.append("<tr><td class='tdstate' style='font-size:13px;color:#6A6AFF'>"+"状态:"+bookT.bookState+"</td></tr>");

			$divBookT.append("<tr><td class='tdclick' style='font-size:15px;color:#6A6AFF'>"+"浏览数:"+bookT.bookClickNums+"</td></tr>");
			$divBookT.append("<tr><td class='tdchapter' style='font-size:14px;color:#6A6AFF'>"+"章节数:"+bookT.bookChapterNums+"</td></tr>");
			
			$("#divTable").append($divBookT);
		}
	}
	//填充分页信息
	function fillPageUtil(pageUtil){
		$("#spanPageNo").text(pageUtil.pageNo);
		$("#spanTotalPages").text(pageUtil.totalPages);
		$("#spanPageSize").text(pageUtil.pageSize);
		$("#spanTotalCount").text(pageUtil.totalCount);
		
		//三种分页操作
		var page1html="";
		if(pageUtil.pageNo<=1){
			page1html+="<span>首页</span><span>上一页 </span>";
		}else{
			page1html+=" <a id='afirst' href='javascript:void(0);'><span>首页</span></a> ";
			page1html+=" <a id='aprevious' href='javascript:void(0);'><span>上一页</span></a> ";
		}
		
		if(pageUtil.pageNo>=pageUtil.totalPages){
			page1html+="<span>下一页 </span><span>尾页</span>";
		}else{
			page1html+=" <a id='anext' href='javascript:void(0);'><span>下一页</span></a> ";
			page1html+=" <a id='alast' href='javascript:void(0);'><span>尾页</span></a> ";
		}
		$("#page1").html(page1html);
		
		var page2OptionHtml="";
		for(var i=1;i<=pageUtil.totalPages;i++){
			if(i==pageUtil.pageNo){
				page2OptionHtml+="<option value='"+i+"' selected>"+i+"</option>";
			}else{
				page2OptionHtml+="<option value='"+i+"'>"+i+"</option>";
			}
		}
		$("#selectPage").html(page2OptionHtml);
		
		var page3Html="";
		for(var i=1;i<=pageUtil.totalPages;i++){
			if(i==pageUtil.pageNo){
				page3Html+="<span style='color:red'> ["+i+"] </span>";
			}else{
				page3Html+=" <a href='javascript:void(0);' class='apage'><span>"+i+"</span></a> ";
			}
		}
		$("#page3").html(page3Html);
		
		//初始化分页相关事件操作
		initPageInfoEvent(pageUtil);
	}
	//初始化分页相关事件操作
	function initPageInfoEvent(pageUtil){
		//首页超链接
		$("#afirst").click(function(e) {
            $("#pageNo").val("1");
			//其他非提交按钮，可以通过事件使用其他控件来执行查询操作
            doSearchBookTs();
        });
		
		//上一页超链接
		$("#aprevious").click(function(e) {
            $("#pageNo").val(pageUtil.pageNo-1);
			//其他非提交按钮，可以通过事件使用其他控件来执行查询操作
			doSearchBookTs();
        });
		
		//尾页超链接
		$("#alast").click(function(e) {
            $("#pageNo").val(pageUtil.totalPages);
			//其他非提交按钮，可以通过事件使用其他控件来执行查询操作
            doSearchBookTs();
        });
		
		//下一页超链接
		$("#anext").click(function(e) {
            $("#pageNo").val(pageUtil.pageNo+1);
			//其他非提交按钮，可以通过事件使用其他控件来执行查询操作
            doSearchBookTs();
        });
		
		//下拉选择分页
		$("#selectPage").change(function(e) {
            $("#pageNo").val($(this).val());
			//其他非提交按钮，可以通过事件使用其他控件来执行查询操作
            doSearchBookTs();
        });
		
		//超链接选择页面
		$("a.apage").click(function(e) {
            $("#pageNo").val($(this).text());
			//其他非提交按钮，可以通过事件使用其他控件来执行查询操作
            doSearchBookTs();
        });
	}
	</script>
</head>
<body>
	<!-- 导入头文件 -->
	<%@ include file="../header.jsp" %>
	
	<!--header开始-->
	<div id="header">
		<div id="left">
		    <ul>
				<li><a href="${contextPath}/manager/friend.jsp">书友圈</a></li>
				<li><a href="${contextPath}/manager/menu.jsp">分类</a></li>
				<li><a href="${contextPath}/manager/bang.jsp">排行榜</a></li>
				<li><a href="${contextPath}/manager/newbook.jsp">新书</a></li> 
			<!--  	<li><a href="${contextPath}/manager/newbook.jsp">征文</a></li>  -->
		   </ul>
		</div>
		<div id="divhide">
			
		</div>
		<!--sec开始-->
		<div id="sec">
			<form id="searchForm" method="post" onsubmit="return false;">
				<input id="pageNo" type="hidden" name="pageNo" value="1"/>
				
                <input name="bookName" type="text" placeholder="书名 / 作者名" autocomplete="off" >
                <!-- class="big_search"-->
                <s id="btnSearch"></s>
            </form>
		</div>
		<!--sec结束-->
		
		<div id="logo"><img src="${contextPath}/images/icare.PNG" alter="logo" width=200px,height=100px/></div>
		
		
	</div>
	<!--header结束-->
	
	<!-- bodyer开始 -->
	<div id="bodyer">
		<!-- 轮播图 -->
		<div id="titleimg">
			<div class="img_box">
		 		 <a href="${contextPath}/manager/bookT/tobookinfor.action?bookId=6"><img src="../images/liulan/1.jpg"></a>
				 <a href="${contextPath}/manager/bookT/tobookinfor.action?bookId=81"><img src="../images/liulan/2.jpg"></a>
				 <a href="${contextPath}/manager/bookT/tobookinfor.action?bookId=2"><img src="../images/liulan/3.jpg"></a>
				 <a href="${contextPath}/manager/bookT/tobookinfor.action?bookId=5"><img src="../images/liulan/4.jpg"></a>
				 <a href="${contextPath}/manager/bookT/tobookinfor.action?bookId=82"><img src="../images/liulan/5.jpg"></a>
			</div> 
		 	<ul class="ul5">
				 <li></li>
				 <li></li>
				 <li></li>
				 <li></li>
			</ul>
		 	<div class="d1"><</div>
		 	<div class="d2">></div>
		</div>
		<!-- titleimg结束 -->
		
		<!-- book开始 -->
		<div id="allbooks">
			<form id="deleteForm" method="post" onSubmit="return false;">
			
		        <div id="divTable">
		        	<table border="1" class="bookT">
		        		<!-- 循环填充书籍区 -->
		        	</table>
		        </div>
			</form>	
		<!--  
			<div id="pageInfo">
				当前第<span id="spanPageNo"></span>页/共<span id="spanTotalPages"></span>页
				每页显示<span id="spanPageSize"></span>条数据/共<span id="spanTotalCount"></span>条数据
			</div>
		-->	
			<div id="page1">
			</div>
			
			<div id="page2">
				跳转到第
				<select id="selectPage">		
				</select>
				页
			</div>
			
			<div id="page3">
			</div>
		</div>
		<!-- book结束 -->
	</div>
	<!-- bodyer结束 -->
	
	<!-- 导入尾文件 -->
	<%@ include file="../footer.jsp" %>
</body>
</html>