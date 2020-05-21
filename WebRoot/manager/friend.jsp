
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>书友圈</title>
	<link type="text/css" rel="styleSheet" href="${contextPath}/styles/titleimg.css">
	<style type="text/css">
		body{
			font: 14px Arial,"微软雅黑";
			text-align:center;
		}
		a{
   			text-decoration: none;
   			color: #232323;
		}
		a:hover{
			color:#f26552;
		}
		#commentTheader{
			width:100%;
			height:100px;
		}
		#commentTheader ul li{
			display:inline-block;
			width:22%;
			border:1px solid blue;
		}
		#commentTheader ul li span{
			font-size:24px;
			font-weight:bold;
			font-family:STXingkai;
			line-height:80px;
		}
		#divcommentT{
			display:none;
			position:fixed;
			top:200px;
			left:30%;
			text-align:center;
			font-size:20px;
			z-index:10;
		}
		#backgrounddiv{
			display:none;
			position:fixed;
			width:100%;
			height:100%;
			top:0px;
			left:0px;
			text-align:center;
			z-index:5;
			opacity:0.8;
			background-color:#999;
		}
		.divcomment tr td{
			margin:0px;
			padding:0px;
			text-align:left;
		}
	</style>

	<!--   引入jQuery，注意版本 -->
	<script type="text/javascript" language="javascript" src="${contextPath}/scripts/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="${contextPath}/scripts/titleimg.js"></script>
	<script type="text/javascript">
	//js变量保存上下文路径
	var contextPath="${contextPath}";
	//用户是否登录
	var isEmpty="${empty sessionScope.userT }";
	
	$(document).ready(function(e) {
		//默认页面加载时，自动执行一遍查询操作
		doSearchCommentTs("求书专区");

		//绑定查询事件
		$("#commentTheader ul li a").click(function(e) {
			var commentdiv=$(this).attr("name");
		//	alert("commentdiv :"+commentdiv);
			
			//修改专区名
			$("#spandiv").text(commentdiv);
			doSearchCommentTs(commentdiv);
        });
		
		//点击发帖超链接
		$("#postcomment").click(function(){
			//判断用户是否登录
    		if(isEmpty=="true"){
    			alert("亲，请先登录！"); 
    			window.location.href=contextPath+"/manager/tologin.jsp";
    		}
			var ispost=$("#postcommentspan").text();
			var newpost=(ispost=="发帖")?"取消发帖":"发帖";
			$("#postcommentspan").text(newpost);
			$("#divcommentT").toggle();
			$("#backgrounddiv").toggle();
		});
		//发帖上的取消按钮
		$("#buttonq").click(function(){
			var ispost=$("#postcommentspan").text();
			var newpost=(ispost=="发帖")?"取消发帖":"发帖";
			$("#postcommentspan").text(newpost);
			$("#divcommentT").toggle();
			$("#backgrounddiv").toggle();
		});
      	//编帖，发帖方法开始
		$("#divcommentT #button").click(function(){
		//	alert("编帖，发帖");
			var commentdiv=$("#spandiv").text();	//取值--评论区名
			$("#commentdivinput").val(commentdiv);	//给表格评论区名赋值
		//	var input=$("#commentdivinput").val();	//取值
		//	alert("commentdivinput--val():"+input);	//显示
			
    	//	var text=$("#textarea").text();			//取值--帖子内容  ie浏览器
 			var text=$("#textarea").val();			//火狐浏览器

    		if(text.length == 0||text.trim().length ==0||text=="帖子字数不得超过500字"){
    			alert("帖子文本不能为空，请输入内容。");
    			return;
    		}
    		//判断用户是否登录
    		if(isEmpty=="true"){
    			alert("亲，请先登录！"); 
    			window.location.href=contextPath+"/manager/tologin.jsp";
    		}
    		else{
    			if(!window.confirm("是否确定发表帖子？")){
    	 			return;
    	 		}
   	 			$.post(contextPath+"/manager/bookT/addtieziajax.action",
   	 			   	   $("#tieziForm").serialize(),
   	 			       function(data){
   	 					   	if(data.result==1){
   	 					   		//alert("发表帖子成功。");
   	 					   		$("#divcommentT").toggle();
								$("#backgrounddiv").toggle();
   	 					   		doSearchCommentTs(commentdiv);
   	 						}else if(data.result==-1){
   	 							alert("发表帖子失败，已存在。");
   	 						}else{
   	 							alert("发表帖子失败。");
   	 						}
   	 					},"json");
    		 }	
 			
         });//编帖，发帖方法结束
		
         
       //回复方法开始
 		$(document).on("click",".huifubtn",function(){
 		//	alert("回复");
 		//	var formid="#"+$(this).attr("name");
 		//	alert("formid:"+formid);
 			
     		//var huifuconnent=$(formid+".huifuconnent").text();			//取值--回复内容
     	//	var huifuFormId=$(this).parent(".huifuForm").attr("id");		//回复表单的id
     	//	alert("huifuFormId:"+huifuFormId);
     		
     		var huifuconnent=$(this).siblings(".huifuconnent").val();
     	//	alert("huifuconnent:"+huifuconnent);
     		
     	//	var huifuconnentname=$("input[id='"+huifuconnentid+"']").attr("name");
     	//	alert("huifuconnentname:"+huifuconnentname);	
     	//	var huifuconnentname=$("huifuconnentid").attr("name");
     	//	alert("huifuconnentname:"+huifuconnentname);	
     	//	var huifuconnent=$("huifuconnentid").text();
     	//	var huifuconnent=$(this).siblings(".huifuconnent").val();
     	//	var huifuconnent=$(this).parent(".huifuForm").children(".huifuconnent").text();	
     	//	alert("huifuconnent: "+huifuconnent);
  

     		if(huifuconnent.length == 0||huifuconnent.trim().length ==0||huifuconnent=="我也说一句"){
     			alert("回复内容不能为空，请输入内容。");
     			return;
     		}
     		//判断用户是否登录
     		if(isEmpty=="true"){
     			alert("亲，请先登录！"); 
     			window.location.href=contextPath+"/manager/tologin.jsp";
     		}
     		else{
     		/*	if(!window.confirm("确认回复？")){
     	 			return;
     	 		}
     		*/
   	 			$.post(contextPath+"/manager/bookT/addtieziajax.action",
   	 					$(this).parent(".huifuForm").serialize(),
   	 			       function(data){
   	 					//   	alert("result: "+data.result);
   	 					   	if(data.result==1){
   	 					   	//	alert("回复成功。");
   	 					   	//	alert("data.commentdiv:"+data.commentdiv);
   	 					   		doSearchCommentTs(data.commentdiv);
   	 						}else if(data.result==-1){
   	 							alert("回复失败，已存在。");
   	 						}else{
   	 							alert("发表帖子失败。");
   	 						}
   	 					},"json");
    		 }	
  			
          });//回复方法结束
    });

	//查询操作开始
	function doSearchCommentTs(commentdiv){
	//	alert("doSearchCommentTs内部。");
		
		$.post(contextPath+"/manager/bookT/dosearchtieziajax.action",
				{"commentdiv":commentdiv},
			   function(data){
					
					var commentTs=data.commentTs;
					fillCommentTs(commentTs);
				},"json");
	}//查询操作结束
	
	//填充查询结果数据
	function fillCommentTs(commentTs){
		
		//删除原有的评论
		$(".divcomment").remove();
		$(".divcommentchild").remove();
		$(".divhuifu").remove();
		//alert("fillBookComment");
		for (var i = 0; i < commentTs.length;i++) {
			
			var comment = commentTs[i];

			var $divcomment = $("<div class='divcomment' style='font-size:12px;margin-left:20px;'></div>");
			$divcomment.append("<table width='800px' height='50px' style='margin-left:0px;'>"
									+"<tr><td rowspan='3' style='width:10%'><img src='"+contextPath+comment.userT.userImg+"' style='width:50px;height:50px;border-radius:50%' /></td><td><span style='color:#999;'>用户 : "+comment.userT.userName+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='color:#999;'>发表时间 ："+comment.commentDate+"</span></td></tr>"
									+"<tr><td><span style='font-size:20px;font-weight:bold;'>"+comment.commentTitle+"</span></td></tr>"
									+"<tr><td><span style='font-size:15px'>"+comment.commentConnent+"</span></td></tr>"
								+"</table>");
			$("#divcomments").append($divcomment);
			
		//	alert("1");
			if(comment.childComments==null){
		//		alert("2");
				return;
			}else{
				for(var j = 0; j < comment.childComments.length;j++) {
					var commentchild = comment.childComments[j];
			//		alert("commentchild:"+commentchild.commentConnent);
					
					var $divcommentchild = $("<div class='divcommentchild' style='font-size:12px;margin-left:100px;'></div>");
					$divcommentchild.append("<table width='500px' height='30px' style='margin-left:0px;text-align:left;'>"
											+"<tr><td rowspan='2' style='width:10%'><img src='"+contextPath+commentchild.userT.userImg+"' style='width:30px;height:30px;border-radius:50%' /></td><td><span style='color:#999;'>用户 : "+commentchild.userT.userName+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='color:#999;'>发表时间 ："+comment.commentDate+"</span></td></tr>"
											+"<tr><td><span style='color:#999;font-size:15px'>"+commentchild.commentConnent+"</span></td></tr>"
										+"</table>");
					$divcommentchild.append("<p style='text-align:left'>-----------------------------------------------------------------------------------"
							+"----------------------------------------------------------------------------------------------</p>");
					$("#divcomments").append($divcommentchild);
			}
			
			
		//	alert("parentId:"+comment.commentId);
		//	alert("commentedId:"+comment.commentId);
		//	alert("userT:"+userT.userId);
			var $divhuifu = $("<div class='divhuifu' style='font-size:12px;margin-right:20px;'></div>");
			$divhuifu.append("<form class='huifuForm' id='huifuForm"+i+"' method='post'>"
							+"<input type='text' class='huifuconnent' id='huifuconnent"+i+"' name='commentConnent' placeholder='我也说一句' style='width:600px;height:30px;margin-left:22%;'></input>"
							+"<input type='button' name='huifuForm"+i+"'  class='huifubtn' value='回复' style='margin:10px 0px 10px 10px;width:100px;height:30px;background-color:#FFF0F5'></input><br/>"
					//		+"<input id='userId' type='text' name='userId' value ='"+(isEmpty==true)?0:userT.userId+"' style='display:none;' />"
							+"<input class='parentId' type='text' name='parentId' value ='"+comment.commentId+"' style='display:none;' />"
							+"<input class='commentedId' type='text' name='commentedId' value ='"+comment.commentId+"' style='display:none;' />"
							+"</form>");
			$divhuifu.append("<p>-----------------------------------------------------------------------------------------------------------"
					+"-----------------------------------------------------------------------------------------------------------------------------------</p>");
			$("#divcomments").append($divhuifu);
		}			
	}
}
	</script>
</head>
<body>
	<!-- 导入头文件 -->
	<%@ include file="../header.jsp" %>
	
	<!-- commentTheader开始  -->
	<div id="commentTheader">
		<ul style="list-style-type:none">
			<li style="background-color:#FF79BC"><a href="javascript:void(0);" name="求书专区"><span class="commentTdiv" id="commentTdiv1">求书专区</span></a></li>
			<li style="background-color:#FF77FF"><a href="javascript:void(0);" name="交友专区"><span class="commentTdiv" id="commentTdiv2">交友专区</span></a></li>
			<li style="background-color:#BE77FF"><a href="javascript:void(0);" name="原创专区"><span class="commentTdiv" id="commentTdiv3">原创专区</span></a></li>
			<li style="background-color:#79FF79"><a href="javascript:void(0);" name="剧情讨论专区"><span class="commentTdiv" id="commentTdiv4">剧情讨论专区</span></a></li>
		</ul>
	</div>
	<!-- commentTheader结束  -->
	
	<!-- commentTbody结束  -->
	<div id="commentTbody">
		<div style="font-size:20px;">
			<span id="spandiv" style="font-weight:bold;color:#BE77FF">求书专区</span>
			<a id="postcomment" href="javascript:void(0);"><span id="postcommentspan" style="margin:10px 100px 10px 1000px;font-weight:bold;color:#BE77FF">发帖</span></a>
			
			<div id="backgrounddiv"></div>
			
			<!-- 发帖表单开始 -->
			<div id="divcommentT">
				<form method="post" id="tieziForm">
					<input id="userId" type="text" name="userId" value ="${sessionScope.userT.userId }" style="width:300px;display:none;" />
					<input id="bookId" type="text" name="bookId" value ="0" style="width:300px;display:none;" />
					<input id="commentedId" type="text" name="commentedId" value ="0" style="width:300px;display:none;" />
					<input id="parentId" type="text" name="parentId" value ="0" style="width:300px;display:none;" />
					<input id="commentdivinput" type="text" name="commentdiv" value ="蜀葵区" style="width:300px;display:none;" />
					<p><input type="text" name="commentTitle" placeholder="标题" style="width:600px;height:30px;"></input><br/></p>
					<textarea id="textarea" name="commentConnent" rows="10"  warp="virtual" maxlength="500" placeholder="帖子字数不得超过500字" style="width:600px;height:200px;"></textarea><br/>
					<input type="button" id="button" value="发帖" style="margin:0px 400px 10px 0px;width:100px;height:30px;background-color:#ff8f59"></input>
					<input type="button" id="buttonq" value="取消" style="width:100px;height:30px;background-color:#ff8f59"></input>
				</form>
			</div>
			<!-- 发帖表单结束 -->
			
			<!-- 各种帖子填充区开始 -->
			<div  id="divcomments" style="width:80%;overflow:auto;border:1px solid #DDA0DD;margin:10px auto;">
			
			
			</div>
			<!-- 各种帖子填充区结束 -->
			
		</div>
	</div>
</body>
</html>