<%-- 
    Document   : admin
<<<<<<< HEAD
    Created on : 30 Oct 2021, 15:22:20
    Author     : Tom
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="org.solent.ood.assessmentgroupa7.dao.WebObjectFactory"%>
<%@page import="org.solent.ood.assessmentgroupa7.dao.PropertiesDao"%>
<%@page import="org.solent.ood.assessmentgroupa7.service.AdminService"%>

<%
    PropertiesDao propertiesDao = WebObjectFactory.getPropertiesDao();
    
    // To be deleted once ReST implemented - see service/AdminService.java
    String name = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.name");
    String endDate = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.enddate");
    String cardNumber = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.cardno");
    String cvv = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.cvv");
    String issueNumber = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.issueno");
    String message = "";
    
    String action = (String) request.getParameter("action");
    if ("updateProperties".equals(action)) {
        // To be changed once ReST implemented - see service/AdminService.java
        message = "updating properties";
        name = (String) request.getParameter("name");
        endDate = (String) request.getParameter("enddate");
        cardNumber = (String) request.getParameter("cardno");
        cvv = (String) request.getParameter("cvv");
        issueNumber = (String) request.getParameter("issueno");
        
        // To be deleted once ReST implemented - see service/AdminService.java
        propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.name", name);
        propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.enddate", endDate);
        propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.cardno", cardNumber);
        propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.cvv", cvv);
        propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.issueno", issueNumber);
    }

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="./resources/css/main.css">
        <title>Administrator</title>
    </head>
    <body>
        <h1>PoS Configuration</h1>
    </head>
    <body>
        <br/>
        <p><%=message %></p>
        
            <form action="./admin.jsp" method="POST">
                <div id="admin">
                    <table style="width:100%;" >
                        <tr>
                            <td>
                                Name on Credit Card
                            </td>
                            <td>
                                <input type="text" name="name" value="<%=name%>">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Credit Card End Date 
                            </td>
                            <td>
                                <input type="text" name="enddate" value="<%=endDate%>">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Credit Card Number
                            </td>
                            <td>
                                <input type="text" name="cardno" value="<%=cardNumber%>">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                CVV Number
                            </td>
                            <td>
                                <input type="text" name="cvv" value="<%=cvv%>">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Issue Number
                            </td>
                            <td>
                                <input type="text" name="issueno" value="<%=issueNumber%>">
                            </td>
                        </tr>
                    </table>
                </div>
                
                <br/>
                <input type="hidden" name="action" value="updateProperties">
                <button class="btn" type="submit">Update Properties</button>
            </form> 
        
    </body>
</html>
