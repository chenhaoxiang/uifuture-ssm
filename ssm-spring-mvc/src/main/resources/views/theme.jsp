<!--
@author chenhx
@version theme.jsp, v 0.1 2018-08-21 下午 9:37
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--引入Sprign的taglib--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>主题演示页面</title>
    <link rel="stylesheet" href="<spring:theme code='font.color'/>">
</head>
<body>
<h1>主题演示页面</h1>
<h2 class="font-color">字体颜色的变化演示</h2>
使用session进行设置主题:<br/>
<div>
    <a href="<c:url value='/themeCut/red'/>">使用Session进行切换为红色字体主题</a>
</div>
<div>
    <a href="<c:url value='/themeCut/default'/>">使用Session进行切换为蓝色字体主题</a>
</div>
下面使用Spring MVC中内置的拦截器进行切换主题:<br/>
<div>
    <a href="<c:url value='/theme?themeName=default'/>">使用拦截器进行切换为蓝色字体主题</a>
</div>
<div>
    <a href="<c:url value='/theme?themeName=red'/>">使用拦截器进行切换为红色字体主题</a>
</div>
</body>
</html>