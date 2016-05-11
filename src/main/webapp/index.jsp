<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/9
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>dictionary</title>
    <script src="js/jquery-1.12.3.min.js"></script>
    <script>
        $(function () {
            $('tr th').css('background', '#ddd');
            $('tr:even').css('background', '#ffc');
            $('tr:odd').css('background', '#ccf');

            $('#key').keyup(function () {
                var key = $(this).val();
                $.ajax({
                    url: '/word',
                    type: 'POST',
                    data: {'action':'autoComplete', 'key':key},
                    dataType: 'json',
                    success: function (words) {
                        $.each(words, function (index, word) {
                            alert(word.english + ':' + word.partOfSpeech);
                        });
                    }
                });
            });
        });
    </script>
</head>
<body>
<h1>dictionary</h1>
<form action="/word" method="post">
    <input type="hidden" name="action" value="query">
    <input id="key" name="key">
    <input type="submit" value="QUERY">
</form>
<hr>
<table>
    <tr>
        <th>ID</th>
        <th>ENGLISH</th>
        <th>PHONETIC</th>
        <th>PART OF SPEECH</th>
        <th>CHINESE</th>
    </tr>
    <c:forEach var="word" items="${sessionScope.words}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${word.english}</td>
            <td>${word.phonetic}</td>
            <td>${word.partOfSpeech}</td>
            <td>${word.chinese}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
