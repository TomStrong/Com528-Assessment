/* Copyright 2021 Jake Roblett
  *********************************************
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at

  * http://www.apache.org/licenses/LICENSE-2.0

  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
 */

package org.solent.ood.assessmentgroupa7.service;

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
