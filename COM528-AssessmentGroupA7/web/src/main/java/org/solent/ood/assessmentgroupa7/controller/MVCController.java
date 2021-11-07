/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.assessmentgroupa7.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.ArrayList;
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

/**
 *
 * @author jrandall, tstrong
 */

@Controller
@RequestMapping("/")
public class MVCController {
    @RequestMapping(value="/", method={RequestMethod.GET})
    public String index(Model model) {
        return "index";
    }
    
    
    @RequestMapping(value="/test", method={RequestMethod.GET})
    public String test(Model model) {
        return "testjsp";
    }
    
    
    
    @RequestMapping(value="/pos", method={RequestMethod.GET})
    public String pos(Model model) {
        return "pos";
    }
    
    @RequestMapping(value = "/admin", method = {RequestMethod.GET})
    public String admin(
            Model model,
            HttpSession session) {
        
        PropertiesDao propertiesDao = WebObjectFactory.getPropertiesDao();
        
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
        
 /*
        Check all required parameters are present for updating application.properties. If not, display error message and do not update.
        If all are present, display confirmation and update accordingly.
        */
        if (url.isEmpty() || username.isEmpty() || password.isEmpty() || name.isEmpty() || endDate.isEmpty() || cardNumber.isEmpty() || cvv.isEmpty()) {
            message = "Please complete all fields before updating properties";
        } else {
            PropertiesDao propertiesDao = WebObjectFactory.getPropertiesDao();
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
}
