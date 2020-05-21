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
			var authorId=$(this).attr("name");
		//	alert("authorId : "+authorId);
    		$(".max").show();
    		$.post(contextPath+"/manager/doSearchOneAuthor.action",
    				{"authorId":authorId},
    				   function(data){
    					//alert("qiantaijieshou");
    					var authorT=data.authorT;
    						//alert(bookT.bookName);
    						fillOneAuthor(authorT);
    						
    					},"json");		

    	});
    	//点击取消
		$("#quxiao").click(function(e){
			$(".max").hide();
		});
		//修改作者信息
		$("#xiugai").click(function(e){
			if(!window.confirm("是否确定修改所选的作者信息?")){
				return;
			}
			$.post(contextPath+"/manager/doupdateauthorajax.action",
			 $("#authorForm").serialize(),
		       function(data){
				   	//alert(data.result);
				   	if(data.result==1){
				   		//window.history.back();
				   		alert("修改成功！");
				   		window.location.reload();
					}else if(data.result==-1){
						alert("要修改的作者不存在！");
					}else{
						alert("修改失败！");
					}
				},"json");
		});
		
		//填充查询结果数据
		function fillOneAuthor(authorT){
			//alert("fillOneBook");
			$("#inputauthorId").val(authorT.authorId);
			$("#inputauthorName").val(authorT.authorName);
			$("#inputauthorWorksNums").val(authorT.authorWorksNums);
			$("#inputauthorWordNums").val(authorT.authorWordNums);
			$("#inputfansNums").val(authorT.fansNums);
			$("#inputauthorImg").val(authorT.authorImg);
			$("#inputauthorInfor").val(authorT.authorInfor);

		}
    	  //点击删除
        $("table tbody").on("click","tr td #delete_button",function(){
			var authorId=$(this).attr("name");
			alert("authorId:"+authorId);
			if(!window.confirm("是否确定删除所选的用户?")){
				return;
			}
			deleteAuthorT(authorId);
    	});
    	//删除方法 
		function deleteAuthorT(authorId){
			$.post(contextPath+"/manager/dodeleteauthort.action",
					{"authorId":authorId},
				       function(data){
						   	//alert(data.result);
						   	if(data.result==1){
						   		alert("作者删除成功！");
						   		window.location.reload();
							}else if(data.result==-1){
								alert("要删除的作者不存在！");
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
            <caption>所有作者信息</caption>
            <thead>
            <tr class="trtile">
            	<th style="font-size:13px;background-color:#FFE4C4;width:20px;">排序</th>
            	<th style="font-size:13px;background-color:#FFE4C4;width:20px;">作者编号</th>
            	<th style="font-size:13px;background-color:#FFE4C4;">作者姓名</th>
            	<th style="font-size:13px;background-color:#FFE4C4;">作者作品数</th>
                <th style="font-size:13px;background-color:#FFE4C4;">累计字数</th>
                <th style="font-size:13px;background-color:#FFE4C4;">粉丝数</th>
                <th style="font-size:13px;background-color:#FFE4C4;">作者信息</th>
                <th style="font-size:13px;background-color:#FFE4C4;">操作</th>
            </tr>
            </thead>
            <tbody><%int i = 1;%>
            <c:forEach items="${lists}" var="authorT">
                <tr>
                    <td class="table_content"><%=i ++%></td>
                    <td class="table_content">${authorT.authorId}</td>
                    <td class="table_content">${authorT.authorName}</td>
                    <td class="table_content">${authorT.authorWorksNums}</td>
                    <td class="table_content">${authorT.authorWordNums}</td>
                    <td class="table_content">${authorT.fansNums}</td>
                    <td class="table_content">${authorT.authorInfor}</td>
                    <td class="table_content">
                        <div class="cell">
                         	<a href="#" id="update_button" class="update_button" name="${authorT.authorId}" style="color:blue">编辑</a>
                            <a href="#" id="delete_button" class="update_button" name="${authorT.authorId}" style="color:red">删除</a>
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
			<form method="post" id="authorForm">
				<div style="margin:10px auto;text-align:center;font-family:STXingkai;font-size:20px">作者信息编辑</div>
				<span>作者编号：</span><input id="inputauthorId" type="text" value="${authorT.authorId}" name="authorId" readonly="readonly"></input>
				<span>作者姓名：</span><input id="inputauthorName" type="text" value="${authorT.authorName}" name="authorName"></input>
				<span>作者作品数：</span><input id="inputauthorWorksNums" type="text" value="${authorT.authorWorksNums}" name="authorWorksNums"></input>
				<span>累计字数：</span><input id="inputauthorWordNums" type="text" value="${authorT.authorWordNums}" name="authorWordNums"></input>
				<span>粉丝数：</span><input id="inputfansNums" type="text" value="${authorT.fansNums}" name="fansNums"></input>
				<span>作者头像：</span><input id="inputauthorImg" type="text" value="${authorT.authorImg}" name="authorImg"></input>
				<span>作者信息：</span><textarea id="inputauthorInfor" name="authorInfor" rows="3"  warp="virtual" maxlength="100" placeholder="简介字数不得超过100字" style="width:350px;height:90px;"></textarea>
				<input type="button" value="修改" id="xiugai" style="width:190px;height:50px"></input>
				<input type="button" value="取消" id="quxiao" style="width:190px;height:50px"></input>
			</form>
		</div>
	</div>	
	<!-- 隐藏快结束 -->
</body>
</html>