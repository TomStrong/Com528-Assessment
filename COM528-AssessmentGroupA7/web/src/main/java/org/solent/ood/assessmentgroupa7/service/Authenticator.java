/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.ood.assessmentgroupa7.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.solent.com504.oodd.password.PasswordUtils;
import org.solent.ood.assessmentgroupa7.dao.PropertiesDao;

/**
 * Handles authentication of users and the generation and validation of 
 * auth tokens.
 * @author Jake Roblett
 */
public class Authenticator {
    
    private final PropertiesDao propertiesDao;
    
    /**
     * Create a new Authenticator
     * @param propertiesDao Properties access object to fetch admin
     *                      credentials from.
     */
    public Authenticator(PropertiesDao propertiesDao) {
        this.propertiesDao = propertiesDao;
    }
    
    /**
     * Verifies whether the given credentials match that of the admin user.
     * @param username Admin username
     * @param password Admin password
     * @return Whether the credentials match that of the admin user
     */
    public boolean verifyAdmin(String username, String password) {
        String adminUsername = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.username");
        String adminPasswordHash = propertiesDao.getProperty("org.solent.ood.assessmentgroupa7.password");
        
        // Check username matches
        if(!adminUsername.equals(username)) {
            return false;
        }
        
        // Check password matches
        if(!PasswordUtils.checkPassword(password, adminPasswordHash)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Replaces the existing admin credentials with new ones as specified in
     * the method parameters.
     * @param username New admin username
     * @param password New admin password
     */
    public void setAdminCredentials(String username, String password) {
        String passwordHash = PasswordUtils.hashPassword(password);
        
        propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.username", username);
        propertiesDao.setProperty("org.solent.ood.assessmentgroupa7.password", passwordHash);
    }
    
}
