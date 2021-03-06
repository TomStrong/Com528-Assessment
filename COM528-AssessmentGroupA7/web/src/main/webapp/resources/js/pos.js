/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var chars = false; // boolean to represent whether the numpad is it number or character mode
var charTimer; // timer for lock in of characters from the numpad
var curButton = null; // current button used for character cycle input
var letterIndex = 0; // letter currently selected on numpad

var entry = ""; // user input
var transactionType = null; // refund or payment
var inputStage = 1; // used for keeping track of which part of the data entry process the user is at
// data entry vars
var amount;
var name;
var cardNo;
var expiryDate;
var cvv;
var issueNo;

// function run once all page elements have loaded
function ready() {
    // sets click function for all character buttons
    let charBtns = document.querySelectorAll(".char");

    charBtns.forEach(function(btn) {
        btn.addEventListener("click", function() {
            if (!chars) {
                // gets number from button text and adds to entry
                entry += btn.getElementsByTagName("strong")[0].innerHTML;
                document.getElementById("entry").innerHTML = entry;
            } else {
                if (btn.id != "") {
                    if (btn.id == "btn0") {
                        entry += " ";
                    } else {
                        clearTimeout(charTimer);
                    charTimer = setTimeout(() => {resetLetterInputVars();}, 1000);
                    // if button is already selected AND letterIndex isn't the last then next letter
                    if (curButton == btn.id) {
                        if (letterIndex != btn.getElementsByTagName("p")[0].innerHTML.length - 1) {
                            letterIndex += 1;
                        } else {
                            letterIndex = 0;
                        }
                        entry = entry.replace(/.$/,btn.getElementsByTagName("p")[0].innerHTML.charAt(letterIndex));
                    // if button isn't selected
                    } else {
                        curButton = btn.id;
                        letterIndex = 0;
                        entry += btn.getElementsByTagName("p")[0].innerHTML.charAt(letterIndex);
                    }
                    document.getElementById("entry").innerHTML = entry;
                    }
                }
            }
        });
    });
    
    // sets click function for back button
    document.getElementById("back").addEventListener("click", function() {
        entry = entry.slice(0, -1);
        document.getElementById("entry").innerHTML = entry;
    });
    
    
    // sets click function for cancel button
    document.getElementById("cancel").addEventListener("click", async function() {
        transactionType = null;
        inputStage = 1;
        emptyEntry();
        getTransactionType();
    });
    
    

    // sets click function for confirm button, this advances the data entry process if data entered is valid
    document.getElementById("confirm").addEventListener("click", async function() {
        switch (inputStage) {
            case 1:
                if (entry == 1 || entry == 2) {
                    transactionType = entry;
                    emptyEntry();
                    getAmount();
                } else {
                    showStatus("Not a valid entry");
                }
                return;
            case 2:
                if (entry.length == 0){
                    showStatus("Required");
                } else if (isNaN(entry)) {
                    showStatus("Please enter a number");
                } else {
                    amount = entry;
                    emptyEntry();
                    getName();
                }
                return;
            case 3:
                if (entry.length == 0){
                    showStatus("Required");
                } else {
                    name = entry;
                    emptyEntry();
                    getCardNo();
                }
                return;
            case 4:
                if (entry.length == 0){
                    showStatus("Required");
                } else if (isNaN(entry) || entry.includes(".")) {
                    showStatus("Please enter a number");
                } else if (entry.length !== 16) {
                    showStatus("Card numbers must be 16 digits");
                } else {
                cardNo = entry;
                emptyEntry();
                getExpiryDate();
                }
                return;
            case 5:
                if (entry.length == 0){
                    showStatus("Required");
                } else if (!/^(0[1-9]|1[0-2])\/?([0-9]{2})$/.test(entry)) { // regex validation for a MM/YY format
                    showStatus("Date must be formatted as MM/YY");
                } else {
                    expiryDate = entry;
                    emptyEntry();
                    getCVV();
                }
                return;
            case 6:
                if (entry.length == 0){
                    showStatus("Required");
                } else if (isNaN(entry) || entry.includes(".")) {
                    showStatus("Please enter a number");
                } else if (entry.length != 3 && entry.length != 4){
                    showStatus("CVV should be 3 or 4 digits");
                } else {
                    cvv = entry;
                    emptyEntry();
                    getIssueNo();
                }
                return;
            case 7:
                if (entry != "" && (isNaN(entry) || entry.includes("."))) {
                    showStatus("Please enter a number or nothing");
                } else if (entry != "" && (entry.length != 1 && entry.length != 2)){
                    showStatus("Issue Number should be 0-2 digits");
                } else {
                    issueNo = entry;
                    emptyEntry();
                    displayEnteredDetails();
                }
                return;
            case 8:
                // puts entered data into hidden form and submits it
                var form = document.querySelector("#transactionForm");
                form.querySelector('input[name="transactionType"]').value = transactionType;
                form.querySelector('input[name="amount"]').value = amount;
                form.querySelector('input[name="name"]').value = name;
                form.querySelector('input[name="endDate"]').value = expiryDate;
                form.querySelector('input[name="cardNumber"]').value = cardNo;
                form.querySelector('input[name="cvv"]').value = cvv;
                form.querySelector('input[name="issueNumber"]').value = issueNo;
                form.submit();
                return;
        }
    });
    
    // styles result message if present on load or starts the data entry process
    if (document.getElementById("result").value != "") {
        if (document.getElementById("result").value.includes("Approved")) {
            showStatus(document.getElementById("result").value, "success");
        } else {
            showStatus(document.getElementById("result").value);
        }
        
        if (document.getElementById("transactionReply").value != "") {
            document.getElementById("prompt").innerHTML = document.getElementById("transactionReply").value;
        }
    } else {
        getTransactionType();
    }
}

// changes the buttons form inserting numbers to letters and vice versa
function changeInput() {
    if (chars) {
        chars = false;
    } else {
        chars = true;
    }
}

// resets vars for number inputs
function resetLetterInputVars() {
    charTimer = null;
    curButton = null;
    letterIndex = 0;
}

// empties the entry var and resets the display for next stage
function emptyEntry() {
    entry = "";
    document.getElementById("entry").innerHTML = "";
    document.getElementById("status").innerHTML = "";
    document.getElementById("status").style.display = "none";
}

// displays status message
function showStatus(message, type="error") {
    document.getElementById("status").innerHTML = message;
    if (type == "error") {
        document.getElementById("status").style.color = "red";
    } else if (type == "success") {
        document.getElementById("status").style.color = "green";
    }
    document.getElementById("status").style.display = "block";
}

// functions for the data entry prompts
function getTransactionType() {
    chars = false;
    document.getElementById("prompt").innerHTML = "Please select an option:<br>1) Payment<br>2) Refund";
}

function getAmount() {
    document.getElementById("prompt").innerHTML = "Please enter amount:";
    inputStage += 1;
}

function getName() {
    chars = true;
    document.getElementById("prompt").innerHTML = "Please enter name on card:";
    inputStage += 1;
}

function getCardNo() {
    chars = false;
    document.getElementById("prompt").innerHTML = "Please enter card number:";
    inputStage += 1;
}

function getExpiryDate() {
    document.getElementById("prompt").innerHTML = "Please enter expiry date:";
    inputStage += 1;
}

function getCVV() {
    document.getElementById("prompt").innerHTML = "Please enter CVV:";
    inputStage += 1;
}

function getIssueNo() {
    document.getElementById("prompt").innerHTML = "Please enter issue number (not required):";
    inputStage += 1;
}

// final display message before confiming transaction
function displayEnteredDetails() {
    message = "Amount: " + amount + "<br>Name: " + name + "<br>Card Number: " + cardNo + "<br>Expiry Date: " + expiryDate + "<br>CVV: " + cvv + "<br>Issue Number: " + issueNo + "<br>Confirm details...";
    document.getElementById("prompt").innerHTML = message;
    inputStage += 1;
}

// when the page is loaded runs the ready() function
document.addEventListener("DOMContentLoaded", ready);