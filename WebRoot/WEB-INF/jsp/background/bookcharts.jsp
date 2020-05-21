<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 导入工具类一样，导入标签库文件 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!--   引入jQuery，注意版本 -->
<script type="text/javascript" language="javascript" src="${contextPath}/scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/scripts/echarts.common.min.js"></script>


<div id="main" style="width:500px;height:400px;border:1px solid red;margin:100px auto;"></div>

<script type="text/javascript">
	var myChart=echarts.init(document.getElementById('main'));
/*	
	 var teamNamesArry=new Array();
	 <c:forEach items="${requestScope.teamNamesLists }" var="name">
	 	teamNamesArry.push("${name}");	//存放组名的数组
	 </c:forEach>
*/	
	 var bookTypeNumsArry=new Array();
	 <c:forEach items="${requestScope.bookTypeNums }" var="num">
	 	bookTypeNumsArry.push("${num}");  //存放每组人数的数组
	 </c:forEach>

	//指定图标的配置和数据
	var option={
			title:{
				text:'不同种类书籍统计'
			},
			tooltip:{},
			legend:{
				data:['数目']
			},
			xAxis:{
				data:['玄幻','修真','仙侠','言情','历史','文学','悬疑','科普']
			},
			yAxis:{
				
			},
			series:[{
				name:'数目',
				type:'bar',
				data:[bookTypeNumsArry[0],bookTypeNumsArry[1],bookTypeNumsArry[2],bookTypeNumsArry[3],
				      bookTypeNumsArry[4],bookTypeNumsArry[5],bookTypeNumsArry[6],
				      bookTypeNumsArry[7]]
			}]
	};
	myChart.setOption(option);
</script>

		
</html>

			
			
