<%--
Created by IntelliJ IDEA.
User: 陈浩翔
Date: 2018/08/07
Time: 下午 10:51
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误页面</title>
</head>
<body>
<pre>定制的HttpMessageNotWritableException异常页面</pre>
<br/>
${data.code},${data.message}
${exception}
</body>
</html>