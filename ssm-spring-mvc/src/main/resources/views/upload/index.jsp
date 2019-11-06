<!--
@author chenhx
@version index.jsp, v 0.1 2018-08-19 下午 6:10
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h1>文件上传演示</h1>
<%--表单的 method 必须为 post，enctype 为 multipart/form-data--%>
<form action="<c:url value='/file/upload'/>" enctype="multipart/form-data" method="post">
    <table>
        <tr>
            <td><input type="text" placeholder="用户名" name="name"></td>
        </tr>
        <tr>
            <td><input type="number" placeholder="年龄" name="age"></td>
        </tr>
        <tr>
            <td>请上传头像:</td>
            <td><input type="file" name="image"></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>