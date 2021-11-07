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
var inputStage = 1;
var amount;
var name;
var cardNo;
var expiryDate;
var cvv;
var issueNo;

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
    
    

    // sets click function for confirm button
    document.getElementById("confirm").addEventListener("click", async function() {
        switch (inputStage) {
            case 1:
                if (entry == 1 || entry == 2) {
                    transactionType = entry;
                    emptyEntry();
                    getAmount();
                } else {
                    document.getElementById("status").innerHTML = "Not a valid entry";
                }
                return;
            case 2:
                amount = entry;
                emptyEntry();
                getName();
                return;
            case 3:
                name = entry;
                emptyEntry();
                getCardNo();
                return;
            case 4:
                cardNo = entry;
                emptyEntry();
                getExpiryDate();
                return;
            case 5:
                expiryDate = entry;
                emptyEntry();
                getCVV();
                return;
            case 6:
                cvv = entry;
                emptyEntry();
                getIssueNo();
                return;
            case 7:
                issueNo = entry;
                emptyEntry();
                displayEnteredDetails();
                return;
            case 8:
                var data = {
                    transactionType: transactionType,
                    amount: amount,
                    name: name,
                    endDate: expiryDate,
                    cardNumber: cardNo,
                    cvv: cvv,
                    issueNumber: issueNo
                };
                var response = await fetch("/Com528-Assessment/transaction", {method: "POST", body: JSON.stringify(data)});
                return;
        }
    });
    
    // ----------
    // Starts transaction input cycle
    // ----------
    getTransactionType();
}

function changeInput() {
    if (chars) {
        chars = false;
    } else {
        chars = true;
    }
}

function resetLetterInputVars() {
    charTimer = null;
    curButton = null;
    letterIndex = 0;
}

function emptyEntry() {
    entry = "";
    document.getElementById("entry").innerHTML = "";
    document.getElementById("status").innerHTML = "";
}

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

function displayEnteredDetails() {
    message = "Amount: " + amount + "<br>Name: " + name + "<br>Card Number: " + cardNo + "<br>Expiry Date: " + expiryDate + "<br>CVV: " + cvv + "<br>Issue Number: " + issueNo + "<br>Confirm details...";
    document.getElementById("prompt").innerHTML = message;
    inputStage += 1;
}

document.addEventListener("DOMContentLoaded", ready);