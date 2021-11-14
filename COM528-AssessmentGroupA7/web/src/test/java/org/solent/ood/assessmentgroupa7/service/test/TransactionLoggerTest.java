/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.assessmentgroupa7.service.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.oodd.bank.client.impl.BankRestClientImpl;
import org.solent.ood.assessmentgroupa7.service.TransactionLogger;

/**
 *
 * @author jofai
 */
public class TransactionLoggerTest {
    
    final static TransactionLogger TRANSACTIONS_LOG = TransactionLogger.INSTANCE;
    
    @Test
    public void LogTransactionTest() throws IOException {
        TRANSACTIONS_LOG.log("Test");
        
        try {
            String TEMP_DIR = System.getProperty("java.io.tmpdir");
            File log = new File(TEMP_DIR + "/transactions.txt");
            
            Scanner reader = new Scanner(log);
            String line = "";
            // goes through each line, line is finally assigned to the last line
            while (reader.hasNextLine()) {
                line = reader.nextLine();
            }
            assertEquals("Test", line);
        } catch(FileNotFoundException e) {
            assertEquals("Test", "File not found");
        }
    }

}
