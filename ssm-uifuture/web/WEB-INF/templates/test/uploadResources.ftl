<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="spring"/>

    <title>上传资源文件测试</title>
</head>

<body>
资源文件上传测试：（最大50MB）
<form action="/resource/uploadResources" method="POST" enctype="multipart/form-data">
    File: <input type="file" name="uploadFile"/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
