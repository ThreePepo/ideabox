<%--
  Created by IntelliJ IDEA.
  User: Warren
  Date: 12-11-25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <h1>
      文件上传
  </h1>
  <!--  //action里的html是后缀名，不是HTML文件，用于spring对请求进行拦截判断-->
  <form method="post" action="/idea/upload.html" enctype="multipart/form-data">
       <input type="text" name="id" value=""/>
      <input type="file" name="file" />
      <input type="submit" />
  </form>
  </body>
</html>