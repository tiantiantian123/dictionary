<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/9
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin page</title>
    <style>
        span.error {
            color: #900;
        }
        span.message {
            color: #0f0;
        }
    </style>
</head>
<body>
<h1>admin</h1>
<form action="/admin" method="post">
    <input type="hidden" name="action" value="login">
    <input type="text" name="username" placeholder="USERNAME" value="admin"><br>
    <input type="password" name="password" placeholder="PASSWORD" value="123"><br>
    <input type="submit" value="LOGIN">
</form>
<span class="error">${requestScope.message}</span>
<span class="message">${sessionScope.username}</span>
<hr>
<form action="/word" method="post">
    <input type="hidden" name="action" value="add">
    <input type="text" name="english" placeholder="english"><br>
    <input type="text" name="chinese" placeholder="chinese"><br>
    <input type="text" name="phonetic" placeholder="phonetic"><br>
    <select name="part_of_speech">
        <option value="名词">名词</option>
        <option value="动词">动词</option>
        <option value="形容词">形容词</option>
        <option value="副词">副词</option>
    </select><br>
    <input type="submit" value="ADD">
</form>
</body>
</html>
