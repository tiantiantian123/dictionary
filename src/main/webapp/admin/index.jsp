<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/9
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="../js/jquery-1.12.3.min.js"></script>
    <script>
        $(function () {
            $('tr th').css('background', '#ddd');
            $('tr:even').css('background', '#ffc');
            $('tr:odd').css('background', '#ccf');
            var partOfSpeech = $('select').attr('title');
            $('option[title="' + partOfSpeech + '"]').attr('selected', 'selected');
        });
    </script>
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
    <c:if test="${requestScope.word.id eq null}">
        <input type="hidden" name="action" value="add">
    </c:if>
    <c:if test="${requestScope.word.id ne null}">
        <input type="hidden" name="action" value="update">
    </c:if>
    <input type="hidden" name="id" value="${requestScope.word.id}">
    <input type="text" name="english" placeholder="english" value="${requestScope.word.english}"><br>
    <input type="text" name="chinese" placeholder="chinese" value="${requestScope.word.chinese}"><br>
    <input type="text" name="phonetic" placeholder="phonetic" value="${requestScope.word.phonetic}"><br>
    <select name="part_of_speech" title="${requestScope.word.partOfSpeech}">
        <option value="名词" title="名词">名词</option>
        <option value="动词" title="动词">动词</option>
        <option value="形容词" title="形容词">形容词</option>
        <option value="副词" title="副词">副词</option>
    </select><br>
    <c:if test="${requestScope.word.id eq null}">
        <input type="submit" value="ADD">
    </c:if>
    <c:if test="${requestScope.word.id ne null}">
        <input type="submit" value="UPDATE">
    </c:if>
</form>
<hr>
<table>
    <tr>
        <th>ID</th>
        <th>ENGLISH</th>
        <th>CHINESE</th>
        <th>PHONETIC</th>
        <th>PART OF SPEECH</th>
        <th colspan="2">OPERATION</th>
    </tr>
    <c:forEach var="word" items="${sessionScope.words}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${word.english}</td>
            <td>${word.chinese}</td>
            <td>${word.phonetic}</td>
            <td>${word.partOfSpeech}</td>
            <td><a href="/word?action=search&id=${word.id}">EDIT</a></td>
            <td><a href="/word?action=remove&id=${word.id}">REMOVE</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
