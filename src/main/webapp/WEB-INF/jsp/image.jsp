<%--
  Created by IntelliJ IDEA.
  User: Tomo
  Date: 2020/12/20
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--，imageという名前のオブジェクトが指定したスコープに存在すれば，それを取得，存在しなければclassかBeanNameが指定されている場合に限り生成-->
<jsp:useBean id="image" scope="session" beanName="bean.Image" type="bean.Image" />
<html>
<header>
    <meta charset="UTF-8" />
    <title>Java Sample</title>
</header>
<body>
<h1>Hello</h1>
<img src=<%= image.source %> />
</body>
</html>
