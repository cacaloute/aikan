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
	
    	  //点击删除
        $("table tbody").on("click","tr td #delete_button",function(){
			var commentId=$(this).attr("name");
		//	alert("commentId:"+commentId);
			if(!window.confirm("删除所选的评论或帖子?")){
				return;
			}
			deletecommentT(commentId);
    	});
    	  
		function deletecommentT(commentId){
			$.post(contextPath+"/manager/dodeletecomment.action",
					{"commentId":commentId},
				       function(data){
						   	//alert(data.result);
						   	if(data.result==1){
						   		alert("删除成功！");
						   	window.location.reload();
							}else if(data.result==-1){
								alert("要删除的评论不存在！");
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
            <caption>所有评论/帖子信息</caption>
            <thead>
            <tr class="trtile">
            	<th style="font-size:13px;background-color:#FFE4C4;">排序</th>
            	<th style="font-size:13px;background-color:#FFE4C4;">评论编号</th>
            	<th style="font-size:13px;background-color:#FFE4C4;">评论用户</th>
            	<th style="font-size:13px;background-color:#FFE4C4;">评论内容</th>
                <th style="font-size:13px;background-color:#FFE4C4;">评论标题</th>
                <th style="font-size:13px;background-color:#FFE4C4;">评论日期</th>
                <th style="font-size:13px;background-color:#FFE4C4;">评论区</th>
                <th style="font-size:13px;background-color:#FFE4C4;">操作</th>
            </tr>
            </thead>
            <tbody><%int i = 1;%>
            <c:forEach items="${lists}" var="commentT">
                <tr>
                    <td class="table_content"><%=i ++%></td>
                    <td class="table_content">${commentT.commentId}</td>
                    <td class="table_content">${commentT.userT.userName}</td>
                    <td class="table_content">${commentT.commentConnent}</td>
                    <td class="table_content">${commentT.commentTitle}</td>
                    <td class="table_content">${commentT.commentDate}</td>
                    <td class="table_content">${commentT.commentdiv}</td>
                    <td class="table_content">
                        <div class="cell">
                            <a href="#" id="delete_button" class="update_button" name="${commentT.commentId}" style="color:red">删除</a>
                        </div>
                    </td>	
                </tr>      
            </c:forEach>
            </tbody>
        </table>
    </div>
	 
</body>
</html>