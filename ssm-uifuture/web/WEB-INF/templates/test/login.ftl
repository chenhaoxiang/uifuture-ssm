<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="spring"/>

    <title>登录测试</title>
</head>

<body>
<#if SESSION_USERS_LOGIN_INFO??>
    ${SESSION_USERS_LOGIN_INFO.username!''}
</#if>

<form action="/usersRest/login" method="POST">
    用户名: <input type="text" name="username"/>
    密码: <input type="password" name="password"/>
    <input type="submit" value="Submit"/>
</form>

</body>

</html>
