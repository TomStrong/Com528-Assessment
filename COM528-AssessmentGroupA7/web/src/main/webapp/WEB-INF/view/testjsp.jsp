<%-- 
    Document   : testjsp
    Created on : 5 Nov 2021, 16:16:57
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="./rest/api-v1/test" method="GET">
            <input type="text" name="testInput">
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
