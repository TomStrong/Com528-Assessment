<%-- 
    Document   : admin
<<<<<<< HEAD
    Created on : 30 Oct 2021, 15:22:20
    Author     : Tom
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="./resources/css/main.css">
        <script src="./resources/js/admin.js"></script>
        <title>Administrator</title>
    </head>
    <body>
        <h1>PoS Configuration</h1>
    </head>
    <body>
        <br/>
        <p class="required">* = required fields </p>
        <br/>
        <p>${message}</p>
        <br/>
            <form action="./admin" method="POST">
                <div id="admin">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                                Bank URL<scope class="required">*</scope>
                            </td>
                            <td>
                                <input type="text" name="url" value="${url}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Username<scope class="required">*</scope>
                            </td>
                            <td>
                                <input type="text" name="username" value="${username}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Password<scope class="required">*</scope>
                            </td>
                            <td>
                                <input type="text" name="password" value="${password}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Name on Credit Card<scope class="required">*</scope>
                            </td>
                            <td>
                                <input type="text" name="name" value="${name}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Credit Card End Date<scope class="required">*</scope>
                            </td>
                            <td>
                                <input type="text" name="endDate" value="${endDate}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Credit Card Number<scope class="required">*</scope>
                            </td>
                            <td>
                                <input type="text" name="cardNumber" value="${cardNumber}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                CVV Number<scope class="required">*</scope>
                            </td>
                            <td>
                                <input type="text" name="cvv" value="${cvv}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Issue Number
                            </td>
                            <td>
                                <input type="text" name="issueNumber" value="${issueNumber}">
                            </td>
                        </tr>
                    </table>
                </div>
                
                <br/>
                <button class="btn" type="submit">Submit Configuration</button>
            </form> 
        
    </body>
</html>
