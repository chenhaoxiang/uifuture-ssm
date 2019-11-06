<%--
Created by IntelliJ IDEA.
User: 陈浩翔
Date: 2018/08/04
Time: 下午 07:00
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MVC Title</title>
</head>
<body>
<pre>文件下载演示</pre>
<br/>
<h1>下载小文件</h1>
<form action="<c:url value='/file/download'/>" method="get">
    <input type="text" name="filename" placeholder="文件名"/>
    <button type="submit">下载小文件</button>
</form>
<h1>下载大文件</h1>
<form action="<c:url value='/file/downloadLargeFile'/>" method="get">
    <input type="text" name="filename" placeholder="文件名"/>
    <button type="submit">下载大文件</button>
</form>
</body>
</html>