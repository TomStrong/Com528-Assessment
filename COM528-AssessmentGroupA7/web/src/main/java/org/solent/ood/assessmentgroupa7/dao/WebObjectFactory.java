/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.assessmentgroupa7.dao;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.ood.assessmentgroupa7.service.Authenticator;

/**
 * Handles the creation and access of singleton instances used throughout the
 * application.
 * @author cgallen
 */
public class WebObjectFactory {

    final static Logger LOG = LogManager.getLogger(WebObjectFactory.class);

    private static PropertiesDao propertiesDao = null;
    private static Authenticator authenticator = null;

    /**
     * Returns the instance of the properties data access object, or creates it
     * if it has not yet been initialised.
     * @return The properties data access object
     * @see PropertiesDao
     */
    public static PropertiesDao getPropertiesDao() {
        if (propertiesDao == null) {
            synchronized (WebObjectFactory.class) {
                if (propertiesDao == null) {
                    // creates a single instance of the dao
                    String TEMP_DIR = System.getProperty("java.io.tmpdir");
                    File propertiesFile = new File(TEMP_DIR + "/application.properties");
                    LOG.debug("using system temp directory: " + TEMP_DIR);
                    LOG.debug("using application properties file : " + propertiesFile.getAbsolutePath());
                    propertiesDao = new PropertiesDao(propertiesFile.getAbsolutePath());
                }
            }
        }
        return propertiesDao;
    }

    /**
     * Returns the instance of the authenticator object, or creates it if it 
     * has not yet been initialised.
     * @return The authenticator instance
     * @see Authenticator
     */
    public static Authenticator getAuthenticator() {
        if(authenticator == null) {
            authenticator = new Authenticator(getPropertiesDao());
        }
        
        return authenticator;
    }

}
