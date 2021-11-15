/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.assessmentgroupa7.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Data access object for the application properties file. This class is
 * responsible for loading the properties and writing changes to the file.
 * @author cgallen
 */
public class PropertiesDao {

    final static Logger LOG = LogManager.getLogger(PropertiesDao.class);

    private File propertiesFile;

    private Properties properties = new Properties();

    /**
     * Creates a new data access object for a properties file.
     * @param propertiesFileLocation File path of the target properties file
     */
    public PropertiesDao(String propertiesFileLocation) {
        try {
            propertiesFile = new File(propertiesFileLocation);
            if (!propertiesFile.exists()) {
              
                InputStream input = PropertiesDao.class.getClassLoader().getResourceAsStream("default.properties");
                properties.load(input);
                
                LOG.info("properties file does not exist: creating new file: " + propertiesFile.getAbsolutePath());
                propertiesFile.getParentFile().mkdirs();
                propertiesFile.createNewFile();
                saveProperties();
            }
            loadProperties();
        } catch (IOException ex) {
            LOG.error("cannot load properties", ex);
        }
    }

    /**
     * Returns the value of a property from its name in the file, or null if the
     * property does not exist.
     * @param propertyName Name of the property to get
     * @return Value of the property, or null if the property does not exist
     */
    // synchronized ensures changes are not made by another thread while reading
    public synchronized String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    /**
     * Sets a new value for the given property and saves the change to the
     * properties file.
     * @param propertyKey Name of the property to update
     * @param propertyValue New value for the property
     */
    public synchronized void setProperty(String propertyKey, String propertyValue) {
        properties.setProperty(propertyKey, propertyValue);
        saveProperties();
    }

    /**
     * Saves all property values into the properties file.
     */
    private void saveProperties() {
        OutputStream output = null;
        try {
            LOG.debug("saving properties to: " + propertiesFile.getAbsolutePath());

            output = new FileOutputStream(propertiesFile);
            String comments = "# PoS Configuration Properties";
            properties.store(output, comments);

        } catch (IOException ex) {
            LOG.error("cannot save properties", ex);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Reads the properties file and loads the values into memory.
     */
    private void loadProperties() {
        InputStream input = null;
        try {
            LOG.debug("loading properties from: " + propertiesFile.getAbsolutePath());
            input = new FileInputStream(propertiesFile);
            properties.load(input);
        } catch (IOException ex) {
            LOG.error("cannot load properties", ex);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
            }
        }
    }

}
