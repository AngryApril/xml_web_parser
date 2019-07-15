<%--
  Created by IntelliJ IDEA.
  User: alexey.valiev
  Date: 6/13/19
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>MainPage</title>
  </head>
  <body>
  <div align="center">
  <h3>File Upload:</h3>
  Select a file to upload: <br />
      <form action = "FileUploadHandler" method = "post" enctype = "multipart/form-data">
          <input type = "file" name = "file" size = "50" accept=".xml" />
          <br/>
          <input type = "submit" value = "Upload File"/>
      </form>
  </div>
  </body>
</html>
