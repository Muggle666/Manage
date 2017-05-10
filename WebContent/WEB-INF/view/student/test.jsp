<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery.validate.min.js"></script>
<title>考试试题</title>
<script type="text/javascript">
$(function(){
	$('#submitBtn').each(function(){
		 $(this).click(function(){
			  if(confirm("确定提交吗？")){
	 			  
			  }else{
				  $(this).attr("href","");
			  }
 		  })
});
</script>


</head>
<body>
<div>
<table>
<c:forEach items="${subjectList}" var="sub">
<th><b>考试时间：${sub.totalTime}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </b></th>
<th><b>考试总分：${sub.totalScore}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></th>
</c:forEach>
<th>考试截止时间：2018年01月01日</th>
</table>
</div>
				<div>
					<table>
					<c:forEach items="${testList}" var="test">
						<tr>
							<th>题目：${test.content}</th>
						</tr>
						
						<tr>
							<th>选项：</th>
						</tr>
						 <c:choose>
			           			 <c:when test="${test.examTestType.id == 1}">
				            			<tr>
											<th><input name="radioBtn" type="radio">选项A：</>${test.chooseA}</th>
										</tr>
							
										<tr>
											<th><input name="radioBtn" type="radio">选项B：${test.chooseB}</th>
										</tr>
										
										<tr>
											<th><input name="radioBtn" type="radio">选项C：${test.chooseC}</th>
										</tr>
										
										<tr>
											<th><input name="radioBtn" type="radio">选项D：${test.chooseD}</th>
										</tr>
			           			 </c:when>
					            <c:otherwise>
					            	<tr>
							<th><input name="checkBoxBtn" type="checkbox">选项A：</>${test.chooseA}</th>
						</tr>
						
						<tr>
							<th><input name="checkBoxBtn" type="checkbox">选项B：${test.chooseB}</th>
						</tr>
						
						<tr>
							<th><input name="checkBoxBtn" type="checkbox">选项C：${test.chooseC}</th>
						</tr>
						
						<tr>
							<th><input name="checkBoxBtn" type="checkbox">选项D：${test.chooseD}</th>
						</tr>
					            </c:otherwise>
		        		 </c:choose>
						
						
						</c:forEach>
					</table>
				</div>	
				<div>
				<input type="button" value="提交" id="submitBtn">
				</div>
				
</body>
</html>