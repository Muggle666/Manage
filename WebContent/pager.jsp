<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
<% 
String url = request.getParameter("url");
int total = Integer.parseInt(request.getParameter("items"));
int pageSize = Integer.parseInt(request.getParameter("pageSize"));
%>
<pg:pager url="<%=url %>" items="<%=total %>" maxPageItems="<%=pageSize %>" maxIndexPages="10" export="currentPageNumber=pageNumber">  
    <pg:first>  
        <a href="${pageUrl }">Home</a>  
    </pg:first>  
    <pg:prev>  
        <a href="${pageUrl }">Previous</a>  
    </pg:prev>  
    <pg:pages>  
        <c:choose>  
            <c:when test="${currentPageNumber eq pageNumber}">  
                <font color="red">${pageNumber }</font>  
            </c:when>  
            <c:otherwise>  
                <a href="${pageUrl }">${pageNumber }</a>  
            </c:otherwise>  
        </c:choose>  
    </pg:pages>  
    <pg:next>  
        <a href="${pageUrl }">Next</a>  
    </pg:next>  
    <pg:last>  
        <a href="${pageUrl }">End</a>  
    </pg:last>  
</pg:pager> 
