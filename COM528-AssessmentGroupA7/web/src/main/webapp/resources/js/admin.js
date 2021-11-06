/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ready() {
    document.getElementById("submit").addEventListener("click", async function() {
        console.log("Send new details to server");
        var form = document.querySelector("#admin");
        var creditcard = {
            name: form.querySelector('input[name="name"]').value,
            endDate: form.querySelector('input[name="endDate"]').value,
            cardNumber: form.querySelector('input[name="cardNumber"]').value,
            cvv: form.querySelector('input[name="cvv"]').value,
            issueNumber: form.querySelector('input[name="issueNumber"]').value
        };
        let response = await fetch("/rest/api-v1/configurePoS", {method: "POST", headers: {"Content-Type": "application/json;charset=utf-8"}, body: JSON.stringify(creditcard)});
        console.log(response);
    });
}

    
document.addEventListener("DOMContentLoaded", ready);