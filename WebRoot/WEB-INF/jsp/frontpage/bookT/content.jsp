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
<title>章节内容页</title><base>
<link href="${contextPath}/styles/footer.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/styles/header.css" rel="stylesheet" type="text/css" />
<style>
	body{
		font: 16px Arial,"微软雅黑";
	}
	#divtable{
		width:60%;
		margin:10px auto;
		overflow:auto;
	}
	#menu span{
		text-align:center;
		width:48%;
		font-size:24px;
		font-height:bold;
		display:inline-block;
		text-line:40px;
	}
	#menu2{
		position:fixed;
		top:80px;
		left:95%;
		text-align:center;
		font-size:24px;
		font-height:bold;
		display:inline-block;
		text-line:40px;
		z-index:10;
	}
	#divbookname{
		font-size:24px;
		font-height:bold;
		text-align:center;
		margin:20px auto;
	}
	#divbookcha{
		font-size:20px;
		font-height:bold;
	}
	#chaptermenu{
		position:fixed;
		top:75px;
		left:0px;
		width:100%;
		height:500px;
		background-color:#FFE4C4;
	}
	.togspan{
		display:inline-block;
		width:260px;
		line-height:20px;
		margin:10px 35px;
	}
</style>
<script type="text/javascript" language="javascript" src="${contextPath}/scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript" language="javascript">
	var contextPath="${contextPath}";
	
	var bookId="${requestScope.bookId}";
	
	var bookName="${requestScope.bookName}";
	//本书一共多少章
	var chapterlength="${requestScope.chapterlength}";
	//此章的编号
	var chapterid="${requestScope.chapterid}";
	
	var chapterName="${requestScope.chapterName}";
	
	$(document).ready(function(e) {
		//先初始化menu
		updateMenu(chapterid,chapterlength);
		
		//初始化书籍章节
	//	initBookChapter();
		//目录操作
		$("#chapp").click(function() {
			$("#chaptermenu").toggle(1000);
		 });	
		//章节选择
		$(".chaptera").click(function(){
			$("#chaptermenu").toggle(1000);
			//根据超链接a的name属性值得到章节编号
			var chapnum=$(this).attr("name");			
			doSearchchapter(chapnum);
		});
    });
	
	//查找某一章内容
	function doSearchchapter(chapternum){
		$.post(contextPath+"/manager/bookT/doSearchchapterajax.action",
				{"chapterid":chapternum,"bookId":bookId},
				   function(data){
						 var chaptername=data.newchaptername;
						 var chaptercontent=data.newchaptercontent;
					 	
					 	 var chaid=data.newchapterId;
					 	 var chapterlength=data.chapterlength;
	
					 	updateMenu(chaid,chapterlength);
					 	//alert("chapterlength:  "+chapterlength);
						fillBookContent(chaptername,chaptercontent,chaid);
						
					},"json");		
	}
	//更新菜单
	function updateMenu(chaid,chapterlength){
		var menuHtml="";
		if(chaid<=0){
			menuHtml+="<span>上一章</span>";
			//menuHtml+="<a id='chap' href='javascript:void(0);'><span>目录</span></a>";
		}else{
			menuHtml+="<a id='aprevious' href='javascript:void(0);'><span>上一章</span></a>";
			//menuHtml+="<a id='chap' href='javascript:void(0);'><span>目录</span></a>";
		}
		if(chaid>=chapterlength-1){
			menuHtml+="<span>下一章</span>";
		}else{
			menuHtml+="<a id='anext' href='javascript:void(0);'><span>下一章</span></a>";
		}
		$("#menu").html(menuHtml);
		 chapterid=parseInt($("#divchapterid").text());
		
		chooseChapter(chaid);
	}
	//填充书籍内容
	function fillBookContent(chaptername,chaptercontent,chaid){
		$("#divtable #update").remove();
		
		//更新填充
		var $divupdate=$("<div id='update'></div>");
		$divupdate.append("<div id='divchapterid' style='display:none'>"+chaid+"</div>");
		$divupdate.append("<div id='divbookcha'>"+chaptername+"</div>");
		$divupdate.append("<div>"+chaptercontent+"</div>");
		
		$("#divtable").append($divupdate);
	}
	
	//选择章节，上一章，下一章
	function chooseChapter(chapterid){
		//点击上一章超链接
		$("#aprevious").click(function(e) {
			//其他非提交按钮，可以通过事件使用其他控件来执行查询操作
			 chapterid=parseInt($("#divchapterid").text());
			 doSearchchapter(chapterid-1);
        });
		
		//点击下一章超链接
		$("#anext").click(function(e) {
          
           chapterid=parseInt($("#divchapterid").text());
         
			doSearchchapter(chapterid+1);
        });
		
		
	}
	
	

</script>
</head>
<body>
	<%@ include file="../../../../header.jsp" %>
	<div id="divtable">
		<div id="menu">
			<a id=aprevious href="javascript:void(0);"><span>上一章</span></a>
			<a id="anext" href="javascript:void(0);"><span>下一章</span></a>
		</div>
		<div id="menu2">
			<a id="chapp" href="javascript:void(0);"><span>目录</span></a>
		</div>
		
		
		<!-- 目录 -->
		<div id="chaptermenu" style="overflow:auto;display:none;"><%int i = 0;%>
			<c:forEach items="${chapters }" var="chaptername">
				<a class="chaptera" href="javascript:void(0);" name="<%=i ++%>"><span class="togspan">${chaptername }</span></a>
			</c:forEach>
		</div>
		
		<div id="divbookname">${requestScope.bookName}</div>
			
		<div id="update">
			<div id="divchapterid" style="display:none">${requestScope.chapterid}</div>
			<div id="divbookcha">${requestScope.chaptername}</div>
			<div><p style="line-height:30px;letter-spacing:3px">${requestScope.chaptercontent}</p></div>
		</div>
	</div>
	<div>
		<%@ include file="../../../../footer.jsp" %>
	</div>
    
</body>
</html>