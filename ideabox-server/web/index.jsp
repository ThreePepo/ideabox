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
      �ļ��ϴ�
  </h1>
  <!--  //action���html�Ǻ�׺��������HTML�ļ�������spring��������������ж�-->
  <form method="post" action="/idea/upload.html" enctype="multipart/form-data">
       <input type="text" name="id" value=""/>
      <input type="file" name="file" />
      <input type="submit" />
  </form>
  </body>
</html>