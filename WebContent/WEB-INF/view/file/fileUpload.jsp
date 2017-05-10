<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>file load</title>
<link rel="stylesheet" 
href="${pageContext.request.contextPath}/static/assets/css/bootstrap.min.css">
<link rel="stylesheet" 
href="${pageContext.request.contextPath}/static/assets/css/ace.min.css">
<%-- <link rel="stylesheet" 
href="${pageContext.request.contextPath}/static/assets/css/ace-rtl.min.css">
<link rel="stylesheet" 
href="${pageContext.request.contextPath}/static/assets/css/ace-skins.min.css">
<script src="${pageContext.request.contextPath}/static/lib/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/jquery.metadata.js"></script> --%>
</head>
<body class="theme-blue">
	<div class="main-content clearfix">
		<div class="row learfix">
			<c:if test="${not empty message}">
				<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">x</button>${message }</div>
			</c:if>
			<form:form method="POST" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
				<div class="form-group">
					<label for="exampleInputFile" class="control-label">选择上传文件</label>
					<input type="file" id="exampleInputFile" name="file" class="form-control">
				</div>
			
				<div>
					<button class="btn btn-primary" type="submit">Upload</button>
					<button class="btn btn-default" onclick="history.go(-1)">Cancel</button>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>