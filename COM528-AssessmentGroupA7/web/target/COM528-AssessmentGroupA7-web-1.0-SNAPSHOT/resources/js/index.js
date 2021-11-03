/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var entry = "";
var chars = false;
var charTimer;
var charSet = "";
var curButton = null;
var letterIndex = 0;

function ready() {
    // sets click function for all character buttons
    let charBtns = document.querySelectorAll(".char");

    charBtns.forEach(function(btn) {
        btn.addEventListener("click", function() {
            if (!chars) {
                // gets number from button text and adds to entry
                entry += btn.getElementsByTagName("strong")[0].innerHTML;
                document.getElementById("screen").innerHTML = entry;
            } else {
                if (btn.id != "") {
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
                    document.getElementById("screen").innerHTML = entry;
                }
            }
        });
    });

    // sets click function for confirm button
    document.getElementById("confirm").addEventListener("click", async function() {
        console.log("Send entry to server");
        let response = await fetch("/confirm", {method: "POST", headers: {"Content-Type": "application/json;charset=utf-8"}, body: JSON.stringify(entry)});
    });

    // sets click function for back button
    document.getElementById("back").addEventListener("click", function() {
        entry = entry.slice(0, -1);
        document.getElementById("screen").innerHTML = entry;
    });

    // sets click function for cancel button
    document.getElementById("cancel").addEventListener("click", async function() {
        console.log("Cancel transaction");
        let response = await fetch("/cancel", {method: "POST"});
    });
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

document.addEventListener("DOMContentLoaded", ready);