<%-- 
    Document   : index
    Created on : 27 Oct 2021, 13:50:52
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="./resources/css/pos.css">
        <script src="./resources/js/pos.js"></script>
        <title>PoS Terminal</title>
    </head>
    <body>
        <div class="container">
            <div id="hiddenAttributes">
                <div id="retrieved">
                    <input type="hidden" id="result" name="result" value="${result}">
                    <input type="hidden" id="transactionReply" name="transactionReply" value="${transactionReply}">
                </div>
                <div id="sending">
                    <form id="transactionForm" action="./transaction" method="POST">
                        <input type="hidden" name="transactionType">
                        <input type="hidden" name="amount">
                        <input type="hidden" name="name">
                        <input type="hidden" name="endDate">
                        <input type="hidden" name="cardNumber">
                        <input type="hidden" name="cvv">
                        <input type="hidden" name="issueNumber">
                    </form>
                </div>
            </div>
            <div class="interface">
                <div id="screen">
                    <p id="status"></p>
                    <p id="prompt"></p>
                    <br>
                    <p id="entry"></p>
                </div>

                <div class="keypad">
                    <div class="keypadrow">
                        <button type="button" class="btn btn-secondary char" id="btn1"><strong>1</strong><br><p>abc</p></button>
                        <button type="button" class="btn btn-secondary char" id="btn2"><strong>2</strong><br><p>def</p></button>
                        <button type="button" class="btn btn-secondary char" id="btn3"><strong>3</strong><br><p>ghi</p></button>
                    </div>

                    <div class="keypadrow">
                        <button type="button" class="btn btn-secondary char" id="btn4"><strong>4</strong><br><p>jkl</p></button>
                        <button type="button" class="btn btn-secondary char" id="btn5"><strong>5</strong><br><p>mno</p></button>
                        <button type="button" class="btn btn-secondary char" id="btn6"><strong>6</strong><br><p>pqr</p></button>
                    </div>

                    <div class="keypadrow">
                        <button type="button" class="btn btn-secondary char" id="btn7"><strong>7</strong><br><p>stu</p></button>
                        <button type="button" class="btn btn-secondary char" id="btn8"><strong>8</strong><br><p>vwx</p></button>
                        <button type="button" class="btn btn-secondary char" id="btn9"><strong>9</strong><br><p>yz</p></button>
                    </div>

                    <div class="keypadrow">
                        <button type="button" class="btn btn-secondary char"><strong>.</strong></button>
                        <button type="button" class="btn btn-secondary char" id="btn0"><strong>0</strong><br><p>&#9251;</p></button>
                        <button type="button" class="btn btn-secondary char"><strong>/</strong></button>
                    </div>

                    <div class="keypadrow">
                        <button type="button" class="btn btn-danger action" id="cancel">X</button>
                        <button type="button" class="btn btn-warning action" id="back">&#8592;</button>
                        <button type="button" class="btn btn-success action" id="confirm">&#10003;</button>
                    </div>            
                </div>
            </div>
        </div>
    </body>
</html>
