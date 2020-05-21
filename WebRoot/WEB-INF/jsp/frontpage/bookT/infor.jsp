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
<title>小说详情页</title><base>
<link href="${contextPath}/styles/footer.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/styles/header.css" rel="stylesheet" type="text/css" />
<style>
	body{
		font: 16px Arial,"微软雅黑";
	}
	div,ul,li,p,span{
		margin:0px;
		padding:0px;
	}
	a {
  		text-decoration: none;
  		color:#232323;
	}
	.tdch{
		font-size:20px;
		background-color:#E0E0E0;
		width:200px;
		line-height:50px;
	}
	#divtable table{
		margin:10px auto;
		text-align:center;
		line-height:40px;
		font-family:STXihei;
	}
	#divchapters{
		margin:10px auto;
		font-size:13px;
		color:#ECECFF;
		overflow:auto;
		width:1000px;
		height:300px;
	}
	#divchapters .divchap{
		float:left;
		line-height:20px;
		margin:5px 10px;
		width:300px;
	}
	.divcomment tr td{
		margin:0px;
		padding:0px;
		text-align:left;
	}
	
</style>
<script type="text/javascript" language="javascript" src="${contextPath}/scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript" language="javascript">
	var contextPath="${contextPath}";
	
	var bookId="${requestScope.bookT.bookId}";
	var isEmpty="${empty sessionScope.userT }";
	
	$(document).ready(function(e) {
		//初始化书籍章节
        initBookChapter();
		//初始化书籍评价
        initBookComment();
        
    	//在线试读
    	$("#divread").click(function(e){
    		var chapterid=-1;
    		 window.location.href=contextPath+"/manager/bookT/tochaptercontent.action?bookId="+bookId+"&chapterid="+chapterid;
    	});
    	//加入书架
    	$("#divbookshelf").click(function(e){
    		 doAddBookToBookShelf();
    	});
    	//发表评论
    	 $("#divcommentBtn").click(function(e) {
    		 //先判断是否登录
    		 if(isEmpty=="true"){
    			 alert("亲，请先登录！"); 
    			 window.location.href=contextPath+"/manager/tologin.jsp";
    			 return;
    		 }
    	//	 alert("isEmpty :"+isEmpty);
    		var text=$("#textarea").val();
    	//	alert("text "+text);
    		if(text.length == 0||text.trim().length ==0||text=="评论字数不得超过50个"){
    			alert("评论文本不能为空，请输入评论。");
    			return;
    		}
    		else{
    			 if(!window.confirm("是否确定发表评论？")){
    	 				return;
    	 		 }
   	 			$.post(contextPath+"/manager/bookT/addcommentajax.action",
   	 			   	   $("#commentForm").serialize(),
   	 			       function(data){
   	 					//  alert("result: "+data.result);
   	 					   	if(data.result==1){
   	 							initBookComment();
   	 						}else if(data.result==-1){
   	 							alert("发表评论失败，已存在。");
   	 						}else{
   	 							alert("发表评论失败。");
   	 						}
   	 					},"json");
    		 }	
 			
         });
		
    });
	
	//初始化小说章节
	function  initBookChapter(){
		$.post(contextPath+"/manager/bookT/dofillbookchapter.action",
				   {"bookId":bookId},
				   function(data){
					 	var chapters=data.chapters;
					 	
					 	//alert("initBookChapter().获取得到值");
					 	
						fillBookChapter(chapters);
						
					},"json");			
	}
	//填充章节
	function  fillBookChapter(chapters){	
		//alert("fillBookChapter");
		for (var i = 0; i < chapters.length;i++) {
			
			var chapter = chapters[i];
			var $divchap = $("<div class='divchap'></div>");
			$divchap.append("<a href='"+contextPath+"/manager/bookT/tochaptercontent.action?bookId="+bookId+"&chapterid="+i+"'><span>"+chapter+"</span></a>");
			
			$("#divchapters").append($divchap);
		}			 
	}
	//初始化小说评价
	function  initBookComment(){
		$.post(contextPath+"/manager/bookT/dofillbookcomment.action",
				   {"bookId":bookId},
				   function(data){
					 	var comments=data.comments;
					 	
					// 	alert("initBookComment().获取得到值");
					 	
						fillBookComment(comments);
						
					},"json");			
	}
	//填充书籍评价
	function  fillBookComment(comments){	
		//删除原有的评论
		$(".divcomment").remove();
		//alert("fillBookComment");
		for (var i = 0; i < comments.length;i++) {
			
			var comment = comments[i];
			var $divcomment = $("<div class='divcomment' style='font-size:12px;margin-left:20px;'></div>");
			$divcomment.append("<table width='800px' height='50px' style='margin-left:0px;'>"
									+"<tr><td rowspan='2' style='width:10%'><img src='"+contextPath+comment.userT.userImg+"' style='width:50px;height:50px;border-radius:50%' /></td><td>"+comment.commentConnent+"</td></tr>"
									+"<tr><td><span style='color:#999;'>用户 : "+comment.userT.userName+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='color:#999;'>发表时间 ："+comment.commentDate+"</span></td></tr>"
								+"</table>");
			$divcomment.append("<p>-----------------------------------------------------------------------------------------------------------"
					+"-----------------------------------------------------------------------------------------------------------------</p>");
			$("#divcomments").append($divcomment);
		}			 
	}
	//书籍加入书架操作
	function doAddBookToBookShelf(){
		$.post(contextPath+"/manager/bookT/doaddbooktobookshelf.action",
				{"bookId":bookId},
				 function(data){
					if(data.result==1){
						alert("书籍加入书架成功！");
					}else if(data.result==2){
						alert("书籍已在书架中，勿重复添加！")
					
					}else{
						alert("书籍加入书架失败！")
					}
				},"json");	
	}
</script>
</head>
<body>
	<%@ include file="../../../../header.jsp" %>
	<div id="divtable">
		<table width=800px>
			<tr>
				<td rowspan="6"><img src="${contextPath}/images/bookface${requestScope.bookT.bookImg}" width=250px height=370px/></td>
				<td style="font-size:20px"> ${requestScope.bookT.bookName}</td>
			</tr>
			<tr>
				<td>
					
					<a href="${contextPath}/manager/authorT/toauthorinfor.action?authorId=${requestScope.bookT.authorT.authorId}"><span>作者：${requestScope.bookT.authorT.authorName}</span>&nbsp;&nbsp;&nbsp;&nbsp;</a>
					<span>字数：${requestScope.bookT.bookWordNums}字</span>
				</td>
			</tr>
			<tr>
				<td>书籍状态：${requestScope.bookT.bookState}</td>
			</tr>
			<tr>
				<td>浏览数：${requestScope.bookT.bookClickNums}</td>
			</tr>
			<tr>
				<td>类型：${requestScope.bookT.bookType.bookTypeName}</td>
			</tr>
			<tr>
				<td>章节数：${requestScope.bookT.bookChapterNums}</td>
			</tr>
			
			
			<tr>
				<td ><input type="button" value="在线阅读" class="tdch" id="divread" style="border-radius:20px;background-color:#FFF0F5"></td>
				<c:choose>
					<c:when test="${empty sessionScope.userT }">
						
						<td ><a href="${contextPath}/manager/tologin.jsp"><input type="button" value="加入书架" class="tdch" style="border-radius:20px;background-color:#FFF0F5"></a></td>
					</c:when>
					<c:otherwise>
						<td ><input type="button" value="加入书架" class="tdch" id="divbookshelf" style="border-radius:20px;background-color:#FFF0F5"></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td colspan="2">小说简介：<p style="margin:10px auto;letter-spacing:4px">&nbsp;&nbsp;&nbsp;&nbsp;${requestScope.bookT.bookInfor}</p></td>
			</tr>
		</table>
		<div>
			<div style="font-family:STHupo;font-size:20px">章节目录</div>
			<div id="divchapters">
			
			
			</div>
		</div>
		<div style="font-family:STHupo;font-size:20px">书籍评论</div>
		<!-- 发表书籍评价 -->
		<div id="comment" style="margin-left:60%;margin-bottom:20px;">
			<form id="commentForm" method="post">
				<input id="userId" type="text" name="userId" value ="${sessionScope.userT.userId }" style="width:300px;display:none;" />
				<input id="bookId" type="text" name="bookId" value ="${requestScope.bookT.bookId}" style="width:300px;display:none;" />
				<textarea id="textarea" name="commentConnent" clos="10" rows="3" warp="virtual" maxlength="50" placeholder="评论字数不得超过50个" style="width:400px;border:2px solid #DDA0DD"></textarea>
				<input id="divcommentBtn" type="button" value="发表评论" style="border-radius:20px;background-color:#FFF0F5"/>
			</form>
		</div>
		<!-- 填充书籍评价 -->
		<div  id="divcomments" style="width:100%;overflow:auto;border:1px solid black;">
			
			
		</div>
	</div>
	
<!--  尾部导航
    <%@ include file="../../../../footer.jsp" %>
-->
</body>
</html>