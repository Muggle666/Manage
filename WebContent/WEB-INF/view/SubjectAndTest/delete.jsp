<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除学员</title>
</head>
<body>
<form:form action="${subject.subId}" method="post" modelAttribute="user">
    <table align="center">
        <tr>
            <td>Student_ID</td>
            <td><form:input path="student_ID" /><form:errors path="student_ID" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><form:input path="name" /><form:errors path="name" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
             <tr>
            <td>性别</td>
            <td><form:input path="gender" /><form:errors path="gender" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
             <tr>
            <td>出生年月</td>
            <td><form:input path="date_of_birth" /><form:errors path="date_of_birth" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
             <tr>
            <td>电话</td>
            <td><form:input path="tel" /><form:errors path="tel" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
             <tr>
            <td>住址</td>
            <td><form:input path="address" /><form:errors path="address" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
        <tr><td colspan="2"><button type="submit">修改</button></td></tr>
    </table>

</form:form>
</body>
</html>