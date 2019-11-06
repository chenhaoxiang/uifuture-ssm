<!--
演示静态资源的缓存
@author chenhx
@version index.jsp, v 0.1 2018-08-18 下午 4:58
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html> utils
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="<c:url value='/static/css/test.css?${version}'/>"/>
</head>
<body>
<h1 class="title">演示静态资源的缓存</h1>
<img src="<c:url value='/static/images/img1.jpg?${version}'/>">
<script src="<c:url value='/static/js/test.js?${version}'/>"></script>
</body>
</html>