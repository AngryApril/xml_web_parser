<%--
  Created by IntelliJ IDEA.
  User: alexey.valiev
  Date: 6/25/19
  Time: 3:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CHOOSE A PARSER</title>
</head>
<body>
<h4 align="center">Select Parser</h4>
<div align = "center">
    <form action="ParsersHandler" method = "post">
        <input type = radio id = "sax" name="parser" value="SAX">
        <label for = "sax"> SAX </label>
        <br>
        <input type = radio id = "dom" name="parser" value="DOM">
        <label for = "dom"> DOM </label>
        <br>
        <input type = radio id = "stax" name="parser" value="StAX">
        <label for = "stax">StAX</label>
        <br>
        <input type = "submit" value = "Parse">
    </form>
</div>
</body>
</html>
