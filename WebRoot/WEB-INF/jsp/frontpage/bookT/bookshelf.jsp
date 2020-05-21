
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>书架</title>
	<link type="text/css" rel="styleSheet" href="${contextPath}/styles/titleimg.css">
	<style type="text/css">
		body{
				font: 14px Arial,"微软雅黑";
				text-align:center;
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
		.first{
			display:none;
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
		initUpdateAndDelete();
		//管理操作
		$("#divmanager").click(function(e) {
			$(".first").toggle(1000);
		 });	
		//删除操作	
		$("#btnDelete").click(function(e) {	
			if(!window.confirm("是否确定删除所选的书籍?")){
				return;
			}
			$.post(contextPath+"/manager/bookT/dodeletebookajax.action",
				   $("#deleteForm").serialize(),
				   function(data){
						if(data.result==1){
							alert("删除成功！");
							//删除完毕后，重新执行一遍查询操作
							window.location.reload();
						}else if(data.result==0){
							alert("删除失败！");
						}else if(data.result==-1){
							alert("准备被删除的书籍数和实际存在的书籍数不一致，不能做删除操作！");
						}
					},"json");
        });
		
		//全选
		$("#chkAll").click(function(e) {
			//$(this)指代的是事件相关的元素对象
			var isChecked=$(this).prop("checked");
            $("input[name=ids]").prop("checked",isChecked);
            
           	//删除按钮的禁用取消
           	$("#btnDelete").prop("disabled",!isChecked);
            
        });
		
    });
	
function initUpdateAndDelete(){
	//每行复选框选择操作，决定全选状态
	$("input[name=ids]").click(function(e) {
		
		var isOk=true;
		//先禁用
		$("#btnDelete").prop("disabled",true);
		
		//jquery中对一组元素做迭代each方法
		$("input[name=ids]").each(function(index, element) {
            if($(this).prop("checked")==false){
				isOk=false;
			}else{
				//只要有一项被选中
				//删除按钮的禁用取消
	           	$("#btnDelete").prop("disabled",false);
			}
        });
		
		$("#chkAll").prop("checked",isOk);
    });
}	

	</script>
</head>
<body>
	<!-- 导入头文件 -->
	<%@ include file="../../../../header.jsp" %>
	
	<div>
		<table width="100%">
			<tr>
				<td width="20%">		
					<div><img width="150px" height="180px" src="${contextPath}/${requestScope.userT.userImg }"/></div>
					<div style="margin:20px auto;font-size:14px;font-weight:bold;font-family:STSong;color:#6A6AFF">${requestScope.userT.userEmail }</div>
					<div style="margin:20px auto;font-size:14px;font-weight:bold;font-family:STSong;color:#6A6AFF">${requestScope.userT.userSex }</div>
					<input id="divmanager" type="button" value="书籍管理" style="width:200px;line-height:50px;background-color:#E0E0E0;font-size:16px;font-weight:bold;font-family:STSong;"></input>
					
				</td>
				<td>
				
				 <form id="deleteForm" method="post" onSubmit="return false;">
						<div class="first" style="font-size:20px;font-weight:bold;font-family:STSong;color:#6A6AFF">
							<span class="first" style="margin:50px 60px">
	                    		<input type="checkbox" id="chkAll"/>全选
	                		</span> 
	                		<span class="first" style="margin:50px 60px;font-size:20px"><input type="button" id="btnDelete" value="删除" disabled="disabled" style="font-size:20px"/></span>
	                		
						</div>
						
						<div style="height:550px; overflow:auto">
							<c:forEach items="${requestScope.booklists }" var="bookT">
								<table style="float:left;margin:20px 40px;">
									<tr>
										<td><img width="150px" height="200px" src="${contextPath}/images/bookface${bookT.bookImg }"/></td>
									</tr>
									<tr>
										<td style="font-size:16px;font-weight:bold;font-family:STSong;color:#6A6AFF"><a href="${contextPath}/manager/bookT/tobookinfor.action?bookId=${bookT.bookId }">${bookT.bookName }</a></td>
									</tr>
									<tr><td class="first" style="color:red">移除<input type="checkbox" value="${bookT.bookId }" name="ids"/></td></tr>
								</table>
							</c:forEach>
						</div>
					</form>	
				</td>
			</tr>
		</table>
	</div>
	
	<!-- 导入尾文件 -->
	<%@ include file="../../../../footer.jsp" %>
</body>
</html>