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
import org.solent.com504.oodd.bank.client.impl.BankRestClientImpl;
import org.solent.ood.assessmentgroupa7.service.TransactionLogger;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;

/**
 *
 * @author jrandall, tstrong
 */

@Controller
@RequestMapping("/")
public class MVCController {
    
    final static Logger LOG = LogManager.getLogger(MVCController.class);
    final static TransactionLogger TRANSACTIONS_LOG = TransactionLogger.INSTANCE;
    
    private final PropertiesDao propertiesDao = WebObjectFactory.getPropertiesDao();
    
    @RequestMapping(value="/", method={RequestMethod.GET})
    public String index(Model model) {
        return "index";
    }
    
    @RequestMapping(value="/pos", method={RequestMethod.GET})
    public String pos(Model model) {
        return "pos";
    }
    

    @RequestMapping(value = "/admin", method = {RequestMethod.GET})
    public String admin(
            Model model,
            HttpSession session) {
        
        String url = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.url");
        String username = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.username");
        String password = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.password");
        String name = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.name");
        String endDate = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.enddate");
        String cardNumber = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.cardno");
        String cvv = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.cvv");
        String issueNumber = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.issueno");

        model.addAttribute("url", url);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("name", name);
        model.addAttribute("endDate", endDate);
        model.addAttribute("cardNumber", cardNumber);
        model.addAttribute("cvv", cvv);
        model.addAttribute("issueNumber", issueNumber);
        
        return "admin";
    }
    

    @RequestMapping(value = "/admin", method = {RequestMethod.POST})
    public String admin(
            @RequestParam(name = "url", required = true) String url,
            @RequestParam(name = "username", required = true) String username,
            @RequestParam(name = "password", required = true) String password,
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "endDate", required = true) String endDate,
            @RequestParam(name = "cardNumber", required = true) String cardNumber,
            @RequestParam(name = "cvv", required = true) String cvv,
            @RequestParam(name = "issueNumber", required = false) String issueNumber,
            Model model,
            HttpSession session) {
        
        String message;
        

        if (url.isEmpty() || username.isEmpty() || password.isEmpty() || name.isEmpty() || endDate.isEmpty() || cardNumber.isEmpty() || cvv.isEmpty()) {
            message = "Please complete all fields before updating properties";
        } else {
            propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.url", url);
            propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.username", username);
            propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.password", password);
            propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.name", name);
            propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.enddate", endDate);
            propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.cardno", cardNumber);
            
            propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.cvv", cvv);
            propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.issueno", issueNumber);

            message = "PoS now configured";
        } 
        
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
        
        String result = null;
        String transactionReply = null;
        
        try {
            
            String bankUrl = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.url");
            Double dAmount = Double.parseDouble(amount);
            BankRestClientImpl client = new BankRestClientImpl(bankUrl);
            TransactionReplyMessage reply = client.transferMoney(fromCard, toCard, dAmount);  
            
            if(reply.getCode() == 200){
                result = "Approved";
                transactionReply = "Transaction complete";
            } else if (reply.getCode() == 400){
                result = "Declined<br/><br/>" + reply.getMessage();
                transactionReply =  "Transaction aborted";
            }         
            TRANSACTIONS_LOG.log(reply);    
        } catch (Exception ex) {
            LOG.error("cannot complete transaction:", ex);
            result = "Error. Please try again.";
        } 
        
        model.addAttribute("result", result);
        model.addAttribute("transactionReply", transactionReply);
        
        return "pos";
    }
    
   
    
}