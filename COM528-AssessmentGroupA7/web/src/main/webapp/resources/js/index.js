/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var entry = "";

function ready() {
    // sets click function for all character buttons
    let charBtns = document.querySelectorAll(".char");

    charBtns.forEach(function(btn) {
        btn.addEventListener("click", function() {
            entry += btn.innerHTML;
            document.getElementById("screen").innerHTML = entry;
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

document.addEventListener("DOMContentLoaded", ready);