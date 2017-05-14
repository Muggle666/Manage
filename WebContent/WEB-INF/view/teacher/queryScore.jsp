<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/jquery-ui.min.css" /> --%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery.validate.min.js"></script>
				<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery-ui.min.js"></script>
		<title>用户列表</title>
		
		<script>
			$(function(){
				$("#examTimeString").datepicker({dateFormat: 'yy-mm-dd'});
				
				 $(".query-score").click(function(){
			 		$.ajax({
						type: 'POST',
						url: '${contextPath}/Manage/teacher/queryScore',
						data: {
							userId: $("#userId").val(),
							userName: $("#userName").val(),
							subjectName: $("#name").val(),
							examTime: $("#examTime").val(),
						},
						dataType: 'JSON',
						success: function(data){
							$("#scoreTable tbody").empty();
							for(var i = 0; i <data.dates.length; i++){
								var score = data.dates[i];
								$("#scoreTable tbody").append('<tr>');
								$("#scoreTable tbody").append('		<td>' + score.userId +'</td>');
								$("#scoreTable tbody").append('		<td>' + score.userName +'</td>');
								$("#scoreTable tbody").append('		<td>' + score.name +'</td>');
								$("#scoreTable tbody").append('		<td>' + score.examTime +'</td>');
								$("#scoreTable tbody").append('		<td>' + score.score +'</td>');
								$("#scoreTable tbody").append('</tr>');
							}
							$("#scoreTable").addClass("table table-striped table-bordered table-hover");
						}
					}); 
					$("#query-form").submit();
				}); 
			});
		</script>
	</head>

	<body class="no-skin">
		<!-- /section:basics/sidebar -->
								<div class="row">
<div id="messageDiv">
<c:if test="${not empty message}">
		<script>
			alert('${message}');
		</script>
</c:if>
</div>
									<div class="col-xs-12">
										<form id="query-form" action="${contextPath}/Manage/teacher/toManageScore" method="post" accept-charset="UTF-8">
										<span>学号：</span><input type="text" name="userId" placeholder="学号"/>
										<span>姓名：</span><input type="text" name="userName" placeholder="姓名"/>
										<span>科目：</span><input type="text" name="name" placeholder="科目"/>
										<span>考试日期：</span><input type="text" name="examTime" placeholder="考试日期"/><br>
										</form>
										<a class="btn btn-xs btn-primary query-score">
										<i class="ace-icon fa fa-search"></i><span style="font-size:16px">查询</span>
										</a>
										<table id="scoreTable" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>学号</th>
													<th>姓名</th>
													<th>科目</th>
													<th>考试日期</th>
													<th>分数</th>
												</tr>
											</thead>

											<tbody>
											<c:forEach items="${pageList.dates}" var="score">
												<tr>
													<td>${score.userId}</td>
													<td>${score.userName}</td>
													<td>${score.name}</td>
													<td>${score.examTime}</td>
													<td>${score.score}</td>
												</tr>
											</c:forEach>
									<tr><td colspan="5">
												<jsp:include page="/pager.jsp">
												<jsp:param name="url" value="${pageContext.request.contextPath}/teacher/toManageScore"/>  
												<jsp:param name="items" value="${pageList.totalSize}"/> 
												<jsp:param name="pageSize" value="${pageList.pageSize}"/>  
												</jsp:include> 
											</td></tr>		
											</tbody>
										</table>
									</div><!-- /.span -->
								</div><!-- /.row -->
	</body>
</html>
