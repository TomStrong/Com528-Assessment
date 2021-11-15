/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.assessmentgroupa7.spring.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.solent.ood.assessmentgroupa7.dao.PropertiesDao;
import org.solent.ood.assessmentgroupa7.dao.WebObjectFactory;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.client.BankRestClient;
import org.solent.com504.oodd.bank.client.impl.BankRestClientImpl;
import org.solent.ood.assessmentgroupa7.service.TransactionLogger;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;
import org.solent.ood.assessmentgroupa7.service.Authenticator;
import solent.ac.uk.ood.examples.cardcheck.CardValidationResult;
import solent.ac.uk.ood.examples.cardcheck.RegexCardValidator;

/**
 * Handles HTTP requests for the front-end of the application, including views
 * and create, update, and delete operations. 
 * 
 * This class is managed by the Spring Framework and as such should not 
 * be accessed directly.
 * 
 * @author Jo Randall
 * @author Tom Strong
 */

@Controller
@RequestMapping("/")
public class MVCController {
    
    final static Logger LOG = LogManager.getLogger(MVCController.class);
    final static TransactionLogger TRANSACTIONS_LOG = TransactionLogger.INSTANCE;
    
    private final PropertiesDao propertiesDao = WebObjectFactory.getPropertiesDao();
    
    /**
     * Handles the view for the homepage.
     * @param model Spring framework model
     * @return View to be rendered
     */
    @RequestMapping(value="/", method={RequestMethod.GET})
    public String index(Model model) {
        return "index";
    }
    
    /**
     * Handles the view for the PoS terminal.
     * @param model Spring framework model
     * @return View to be rendered
     */
    @RequestMapping(value="/pos", method={RequestMethod.GET})
    public String pos(Model model) {
        return "pos";
    }
    
    /**
     * Handles the view for the admin configuration page. Access to this page
     * requires valid admin credentials, which should be supplied as parameters
     * with the request.
     * @param authUsername Username for authentication checking
     * @param authPassword Plain text password for authentication checking
     * @param model Spring framework model
     * @param session Spring framework HTTP session
     * @return View to be rendered
     */
    @RequestMapping(value = "/admin", method = {RequestMethod.POST})
    public String admin(
            @RequestParam(name = "auth_username", required = true) String authUsername,
            @RequestParam(name = "auth_password", required = true) String authPassword,
            Model model,
            HttpSession session) {
        
        Authenticator auth = WebObjectFactory.getAuthenticator();
        
        if(!auth.verifyAdmin(authUsername, authPassword)) {
            model.addAttribute("message", "Invalid username or password");
            return "index";
        }
        
        String url = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.url");
        String username = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.username");
        String name = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.name");
        String endDate = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.enddate");
        String cardNumber = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.cardno");
        String cvv = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.cvv");
        String issueNumber = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.issueno");

        model.addAttribute("url", url);
        model.addAttribute("username", username);
        model.addAttribute("password", "");
        model.addAttribute("name", name);
        model.addAttribute("endDate", endDate);
        model.addAttribute("cardNumber", cardNumber);
        model.addAttribute("cvv", cvv);
        model.addAttribute("issueNumber", issueNumber);
        
        return "admin";
    }
    
    /**
     * Updates the properties from the admin configuration page with new values
     * as supplied in the body of the POST request. 
     * 
     * This endpoint requires valid
     * admin credentials, which should be included within the request.
     * 
     * Unchanged values must still be provided for the request to be valid, with
     * the exception of the `password` field, which should be null if it is not
     * being changed.
     * 
     * @param authUsername Username for authentication checking
     * @param authPassword Plain text password for authentication checking
     * @param url URL for the banking application's API
     * @param username Username for the admin account
     * @param password (Optional) New password for the admin account. Set to
     *                 null if this should not be changed.
     * @param name Name on the PoS system's credit card
     * @param endDate End date on the PoS system's credit card
     * @param cardNumber Card number for the PoS system to use
     * @param cvv CVV number on the PoS system's credit card
     * @param issueNumber (Optional) Issue number on the PoS system's credit card
     * @param model Spring framework model
     * @param session Spring framework HTTP session
     * @return View to be rendered
     */
    @RequestMapping(value = "/configurepos", method = {RequestMethod.POST})
    public String admin(
            @RequestParam(name = "auth_username", required = true) String authUsername,
            @RequestParam(name = "auth_password", required = true) String authPassword,
            @RequestParam(name = "url", required = true) String url,
            @RequestParam(name = "username", required = true) String username,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "endDate", required = true) String endDate,
            @RequestParam(name = "cardNumber", required = true) String cardNumber,
            @RequestParam(name = "cvv", required = true) String cvv,
            @RequestParam(name = "issueNumber", required = false) String issueNumber,
            Model model,
            HttpSession session) {
        
        Authenticator auth = WebObjectFactory.getAuthenticator();
        
        String message;
        
        CardValidationResult luhnCheck = RegexCardValidator.isValid(cardNumber);
        
        if(auth.verifyAdmin(authUsername, authPassword)) {
            if (url.isEmpty() || username.isEmpty() || name.isEmpty() || endDate.isEmpty() || cardNumber.isEmpty() || cvv.isEmpty()) {
                message = "Please complete all fields before updating properties";
            } else {
                if(luhnCheck.isValid()) {
                    propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.url", url);
                    propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.username", username);
                    propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.name", name);
                    propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.enddate", endDate);
                    propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.cardno", cardNumber);

                    propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.cvv", cvv);
                    propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.issueno", issueNumber);

                    if(password != null && !password.isEmpty() && !password.isBlank()) {
                        // Update admin password
                        auth.setAdminCredentials(username, password);

                        LOG.info("Updated admin credentials from web PoS Configuration");
                    }
                    
                    message = "PoS now configured";
                } else {
                    message = "Invalid credit card number";
                }
            } 
        } else {
            message = "Invalid username or password";
        }
        
        model.addAttribute("authUsername", authUsername);
        model.addAttribute("url", url);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("name", name);
        model.addAttribute("endDate", endDate);
        model.addAttribute("cardNumber", cardNumber);
        model.addAttribute("cvv", cvv);
        model.addAttribute("issueNumber", issueNumber);
        model.addAttribute("message", message);
        
        return "admin";
    }
    
    /**
     * Performs a transaction between the card held by the PoS system and the
     * card provided as parameters in the request. 
     * 
     * The transactionType parameter must be one of the following values:
     *    "1": Charge
     *    "2": Refund
     * 
     * @param transactionType Type of transaction to perform
     * @param amount Amount to charge or refund
     * @param inputName Name on the credit card
     * @param inputEndDate Expiry date of the credit card
     * @param inputCardNumber Card number to perform the transaction on
     * @param inputCvv CVV number of the credit card
     * @param inputIssueNumber (Optional) Issue number of the credit card
     * @param model Spring framework model
     * @param session Spring framework HTTP session
     * @return View to be rendered
     */
    @RequestMapping(value = "/transaction", method = {RequestMethod.POST})
    public String transaction(
            @RequestParam(name = "transactionType", required = true) String transactionType,
            @RequestParam(name = "amount", required = true) String amount,
            @RequestParam(name = "name", required = true) String inputName,
            @RequestParam(name = "endDate", required = true) String inputEndDate,
            @RequestParam(name = "cardNumber", required = true) String inputCardNumber,
            @RequestParam(name = "cvv", required = true) String inputCvv,
            @RequestParam(name = "issueNumber", required = false) String inputIssueNumber,
            Model model,
            HttpSession session) {
        
        String result = null;
        String transactionReply = null;
            
        CardValidationResult luhnCheck = RegexCardValidator.isValid(inputCardNumber);
        
        if (luhnCheck.isValid()) {
            
            String posName = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.name");
            String posEndDate = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.enddate");
            String posCardNumber = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.cardno");
            String posCvv = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.cvv");
            String posIssueNumber = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.issueno");

            CreditCard toCard = new CreditCard();
            CreditCard fromCard = new CreditCard();

            if (transactionType.equals("1"))
            {
                toCard.setName(posName);
                toCard.setEndDate(posEndDate);
                toCard.setCardnumber(posCardNumber);
                toCard.setCvv(posCvv);
                toCard.setIssueNumber(posIssueNumber);

                fromCard.setName(inputName);
                fromCard.setEndDate(inputEndDate);
                fromCard.setCardnumber(inputCardNumber);
                fromCard.setCvv(inputCvv);
                fromCard.setIssueNumber(inputIssueNumber);
            } 
            else if (transactionType.equals("2"))
            {
                toCard.setName(inputName);
                toCard.setEndDate(inputEndDate);
                toCard.setCardnumber(inputCardNumber);
                toCard.setCvv(inputCvv);
                toCard.setIssueNumber(inputIssueNumber);

                fromCard.setName(posName);
                fromCard.setEndDate(posEndDate);
                fromCard.setCardnumber(posCardNumber);
                fromCard.setCvv(posCvv);
                fromCard.setIssueNumber(posIssueNumber);
            }

            try {

                String bankUrl = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.url");
                Double dAmount = Double.parseDouble(amount);
                BankRestClient client = new BankRestClientImpl(bankUrl);
                TransactionReplyMessage reply = client.transferMoney(fromCard, toCard, dAmount);  

                if(reply.getCode() == 200){
                    result = "Approved";
                    transactionReply = "Transaction complete";
                } else if (reply.getCode() == 400){
                    result = "Declined<br/><br/>" + reply.getMessage();
                    transactionReply =  "Transaction aborted";
                }         
                TRANSACTIONS_LOG.log(reply.toString());
            } catch (Exception ex) {
                LOG.error("cannot complete transaction:", ex);
                result = "Error. Please check your connection.";
            } 
        } else {
            result = inputCardNumber + " is an invalid card number";
            transactionReply = "Transaction aborted";
        }
        
        model.addAttribute("result", result);
        model.addAttribute("transactionReply", transactionReply);
        
        return "pos";
    }
    
    /**
     * Default handler for uncaught exceptions, which renders the error page
     * in the event that an unexpected error occurs.
     * @param e Exception thrown
     * @param model Spring framework model
     * @param request HTTP request
     * @return View to be rendered
     */
    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(final Exception e, Model model, HttpServletRequest request) {
        model.addAttribute("exception", e);
        return "error";
    }
}