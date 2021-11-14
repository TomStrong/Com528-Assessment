/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ready() {
    var form = document.querySelector("#adminForm");
    form.onsubmit = validateForm;
    
    function validateForm(event) {
        var formOkay = true;
        
        if (form.querySelector('input[name="url"]').value.length == 0 || form.querySelector('input[name="username"]').value.length == 0 || form.querySelector('input[name="password"]').value.length == 0 || form.querySelector('input[name="name"]').value.length == 0 || form.querySelector('input[name="endDate"]').value.length == 0 || form.querySelector('input[name="cardNumber"]').value.length == 0 || form.querySelector('input[name="cvv"]').value.length == 0) {
            formOkay = false;
            document.getElementById("message").innerHTML = "Please fill all required fields";
        } else if (!/^(0[1-9]|1[0-2])\/?([0-9]{2})$/.test(form.querySelector('input[name="endDate"]').value)) {
            formOkay = false;
            document.getElementById("message").innerHTML = "Expiry date format should be MM/YY";
        } else if (form.querySelector('input[name="cardNumber"]').value.length != 16) {
            formOkay = false;
            document.getElementById("message").innerHTML = "Card number should be 16 digits long";
        } else if (isNaN(form.querySelector('input[name="cardNumber"]').value) || form.querySelector('input[name="cardNumber"]').value.includes(".")) {
            formOkay = false;
            document.getElementById("message").innerHTML = "Card number must only contain numbers";
        } else if (!(form.querySelector('input[name="cvv"]').value.length == 3 || form.querySelector('input[name="cvv"]').value.length == 4)) {
            formOkay = false;
            document.getElementById("message").innerHTML = "CVV must be 3 or 4 digits";
        } else if (isNaN(form.querySelector('input[name="cvv"]').value) || form.querySelector('input[name="cvv"]').value.includes(".")) {
            formOkay = false;
            document.getElementById("message").innerHTML = "CVV must only contain numbers";
        } 
        
        if (!formOkay) {
            event.preventDefault();
            document.getElementById("message").style.color = "red";
        }        
    }
}

    
document.addEventListener("DOMContentLoaded", ready);