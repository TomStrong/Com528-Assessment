package org.solent.ood.assessmentgroupa7.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;
import org.solent.ood.assessmentgroupa7.spring.web.MVCController;

/**
 * Handles logging of transactions as they happen to a file on the system.
 * @author Jake Roblett
 */
public class TransactionLogger {
    
    public static final TransactionLogger INSTANCE = new TransactionLogger();
    private final static Logger LOG = LogManager.getLogger(TransactionLogger.class);
    
    private final File outputFile;
    
    /**
     * 
     */
    private TransactionLogger() {
        String TEMP_DIR = System.getProperty("java.io.tmpdir");
        this.outputFile = new File(TEMP_DIR + "/transactions.txt");
        
        // TODO: Log4j logger is returned as null and throws NullPointerException
        //       during tests. Sysout used for now as it works  -Jake
        System.out.println("Transaction log path: " + outputFile.getAbsolutePath());
//        LOG.info("Transaction log path: " + outputFile.getAbsolutePath());
    }
    
    /**
     * Logs a bank transaction to the log file.
     * @param message Transaction string to log
     * @throws IOException 
     */
    public void log(String message) throws IOException {
        if(!outputFile.exists()) {
            if(!outputFile.getParentFile().exists() && !outputFile.getParentFile().mkdirs()) {
                throw new RuntimeException("Failed to create parent directories for log file");
            }
            
            if(!outputFile.createNewFile()) {
                throw new RuntimeException("Failed to create transaction log file");
            }
        }
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
        writer.write(message.strip());
        writer.write("\n");
        writer.close();
    }
    
    public File getOutputFile() {
        return outputFile;
    }
    
}
