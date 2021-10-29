<%-- 
    Document   : index
    Created on : 27 Oct 2021, 13:50:52
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="./resources/css/index.css">
        <script src="./resources/js/index.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="interface">
                <div id="screen">

                </div>

                <div class="keypad">
                    <div class="keypadrow">
                        <button type="button" class="btn btn-secondary char">1</button>
                        <button type="button" class="btn btn-secondary char">2</button>
                        <button type="button" class="btn btn-secondary char">3</button>
                    </div>

                    <div class="keypadrow">
                        <button type="button" class="btn btn-secondary char">4</button>
                        <button type="button" class="btn btn-secondary char">5</button>
                        <button type="button" class="btn btn-secondary char">6</button>
                    </div>

                    <div class="keypadrow">
                        <button type="button" class="btn btn-secondary char">7</button>
                        <button type="button" class="btn btn-secondary char">8</button>
                        <button type="button" class="btn btn-secondary char">9</button>
                    </div>

                    <div class="keypadrow">
                        <button type="button" class="btn btn-secondary char">*</button>
                        <button type="button" class="btn btn-secondary char">0</button>
                        <button type="button" class="btn btn-secondary char">#</button>
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
