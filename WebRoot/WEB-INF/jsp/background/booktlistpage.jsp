<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" language="javascript" src="${contextPath}/scripts/jquery-3.2.1.min.js"></script>
<link href="http://cdn.datatables.net/1.10.5/css/jquery.dataTables.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js" type="text/javascript"></script>

<script src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js"></script>
<style type="text/css">
	body{
		font: 16px Arial,"微软雅黑";
	}
	a{
 		text-decoration: none;
	}
	.example{
		width:300px;
	}
	.example td{
		width:50px;
		text-align:center;
		background-color:#FAF4FF;
	}
	.max{
		width:100%;
		height:100%;
		position:fixed;
		top:0px;
		left:0px;
		
		background:rgba(255,228,196,0.8);
		z-index:50;
		display:none;
	/*	 background-color:#DCB5FF; */
		border:1px solid black;
	}
	.min{
		top:10px;
		left:300px;
		width:400px;
		height:500px;
		position:absolute;
		opacity:1;
		z-index:60;
		background-color:#FFF0F5;
		border:1px solid black;
	}
	.min span{
		width:100px;
		line-height:30px;
		font-weight:bold;
		font-size:16px;
	/*	border:1px solid black; */
		margin:10px 0px 10px 20px;
		display:inline-block;
		text-align:center;
	}
	.min input{
		width:200px;
		height:30px;
		
		margin:5px auto;
	}
</style>
<script>
//js变量保存上下文路径
var contextPath="${contextPath}";

$(document).ready(function(){
        $('#example').dataTable();
        //点击编辑
        $("table tbody").on("click","tr td #update_button",function(){
        //$(".update_button").on("click",function(){
		//$(".update_button").click(function(){
			//alert(".update_button");
			var bookId=$(this).attr("name");
			//alert("bookId : "+bookId);
    		$(".max").show();
    		$.post(contextPath+"/manager/doSearchOneBook.action",
    				{"bookId":bookId},
    				   function(data){
    					//alert("qiantaijieshou");
    					var bookT=data.bookT;
    						//alert(bookT.bookName);
    						fillOneBook(bookT);
    						
    					},"json");		

    	});
		
    	  //点击删除
        $("table tbody").on("click","tr td #delete_button",function(){
			var bookId=$(this).attr("name");
		//	alert("bookId:"+bookId);
			if(!window.confirm("是否确定删除所选的书籍?")){
				return;
			}
			deleteBookT(bookId);
    	});
		
		//点击取消
		$("#quxiao").click(function(e){
			$(".max").hide();
		});
		//修改书籍信息
		$("#xiugai").click(function(e){
			if(!window.confirm("是否确定修改所选的书籍?")){
				return;
			}
			$.post(contextPath+"/manager/doupdateajax.action",
			 $("#bookForm").serialize(),
		       function(data){
				   	//alert(data.result);
				   	if(data.result==1){
				   		//window.history.back();
				   		alert("修改成功！");
				   		window.location.reload();
					}else if(data.result==-1){
						alert("要修改的书籍不存在！");
					}else{
						alert("修改失败！");
					}
				},"json");
		});
		
		//填充查询结果数据
		function fillOneBook(bookt){
			//alert("fillOneBook");
			$("#inputbookId").val(bookt.bookId);
			$("#inputbookId").attr("readonly","readonly");
			$("#inputbookName").val(bookt.bookName);
			$("#inputbookAuthor").val(bookt.bookAuthor);
			$("#inputbookState").val(bookt.bookState);
			$("#inputbookWordNums").val(bookt.bookWordNums);
			$("#inputbookChapterNums").val(bookt.bookChapterNums);
			$("#inputisFree").val(bookt.isFree);
			$("#inputbookClickNums").val(bookt.bookClickNums);
		}
		//删除
		function deleteBookT(bookId){
			$.post(contextPath+"/manager/dodeleteajax.action",
					{"bookId":bookId},
				       function(data){
						   	//alert(data.result);
						   	if(data.result==1){
						   		alert("删除成功！");
						   	window.location.reload();
							}else if(data.result==-1){
								alert("要删除的书籍不存在！");
							}else{
								alert("删除失败！");
							}
						},"json");
		}
});



</script>

</head>

<body>				
	
	<!-- border:1px solid red -->
	<div class="member_table" style="width:1000px;margin:0px auto;background-color:#FAF4FF;">
        <table class="example" id="example">
            <caption>所有书籍信息</caption>
            <thead>
            <tr class="trtile">
            	<th style="font-size:13px;background-color:#FFE4C4;">排序</th>
            	<th style="font-size:13px;background-color:#FFE4C4;">书籍编号</th>
                <th style="font-size:13px;background-color:#FFE4C4;">书籍名称</th>
                <th style="font-size:13px;background-color:#FFE4C4;">书籍作者</th>
                <th style="font-size:13px;background-color:#FFE4C4;">书籍状态</th>
                <th style="font-size:13px;background-color:#FFE4C4;">书籍字数</th>
                <th style="font-size:13px;background-color:#FFE4C4;">书籍章节数</th>
                <th style="font-size:13px;background-color:#FFE4C4;">是否免费</th>
                <th style="font-size:13px;background-color:#FFE4C4;">浏览次数</th>
                <th style="font-size:13px;background-color:#FFE4C4;">操作</th>
            </tr>
            </thead>
            <tbody><%int i = 1;%>
            <c:forEach items="${lists}" var="bookt">
                <tr>
                    <td class="table_content"><%=i ++%></td>
                    <td class="table_content" id="member_id">${bookt.bookId}</td>
                    <td class="table_content" style="font-weight:bold;">${bookt.bookName}</td>
                    <td class="table_content">${bookt.bookAuthor}</td>
                    <td class="table_content">${bookt.bookState}</td>
                    <td class="table_content">${bookt.bookWordNums}</td>
                    <td class="table_content">${bookt.bookChapterNums}</td>
                    <td class="table_content">${bookt.isFree}</td>
                     <td class="table_content">${bookt.bookClickNums}</td>
                    <td class="table_content">
                        <div class="cell">
                            <a href="#" id="update_button" class="update_button" name="${bookt.bookId}" style="color:blue">编辑</a>
                            <a href="#" id="delete_button" class="update_button" name="${bookt.bookId}" style="color:red">删除</a>
                        </div>
                    </td>	
                </tr>      
            </c:forEach>
            </tbody>
        </table>
    </div>
    
   	  <!-- 隐藏快开始   id="max<%=i-1 %>" -->
     <div class="max">
		<div class="min">
			<form method="post" id="bookForm">
				<div style="margin:10px auto;text-align:center;font-family:STXingkai;font-size:20px">书籍编辑</div>
				<span>书籍编号：</span><input id="inputbookId" type="text" value="${bookt.bookId}" name="bookId" readonly="readonly"></input>
				<span>书籍名称：</span><input id="inputbookName" type="text" value="${bookt.bookName}" name="bookName"></input>
				<span>书籍作者：</span><input id="inputbookAuthor" type="text" value="${bookt.bookAuthor}" name="bookAuthor"></input>
				<span>书籍状态：</span><input id="inputbookState" type="text" value="${bookt.bookState}" name="bookState"></input>
				<span>书籍字数：</span><input id="inputbookWordNums" type="text" value="${bookt.bookWordNums}" name="bookWordNums"></input>
				<span>章节数目：</span><input id="inputbookChapterNums" type="text" value="${bookt.bookChapterNums}" name="bookChapterNums"></input>
				<span>是否免费：</span><input id="inputisFree" type="text" value="${bookt.isFree}" name="isFree"></input>
				<span>浏览次数：</span><input id="inputbookClickNums" type="text" value="${bookt.bookClickNums}" name="bookClickNums"></input>
				<input type="button" value="修改" id="xiugai" style="width:197px;height:50px"></input>
				<input type="button" value="取消" id="quxiao" style="width:198px;height:50px"></input>
			</form>
		</div>
	</div>	
	<!-- 隐藏快结束 -->
</body>
</html>