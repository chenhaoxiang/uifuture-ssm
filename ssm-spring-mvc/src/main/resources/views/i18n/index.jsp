<!--
国际化界面演示
@author chenhx
@version index.jsp, v 0.1 2018-09-01 下午 5:28
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="title" scope="session"></spring:message></title>
</head>
<body>
<h1><spring:message code="welcome" scope="session"></spring:message></h1>
<spring:message code="name" scope="session"></spring:message><br/>
<a href="<c:url value='/i18n/index?localeName=zh_CN' />">中文</a>
<a href="<c:url value='/i18n/index?localeName=en_US' />">English</a>
</body>
</html>