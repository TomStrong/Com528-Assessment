package org.solent.com504.oodd.bank.client.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;

/**
 * Handles logging of transactions as they happen to a file on the system.
 * @author Jake Roblett
 */
public class TransactionLogger {
    
    public static final TransactionLogger INSTANCE = new TransactionLogger();
    
    private final File outputFile;
    
    /**
     * 
     */
    public TransactionLogger() {
        String TEMP_DIR = System.getProperty("java.io.tmpdir");
        this.outputFile = new File(TEMP_DIR + "/transactions.txt");
    }
    
    /**
     * Logs a bank transaction to the log file.
     * @param transaction Transaction to log
     * @throws IOException 
     */
    public void log(TransactionReplyMessage transaction) throws IOException {
        if(!outputFile.exists()) {
            if(!outputFile.getParentFile().mkdirs()) {
                throw new RuntimeException("Failed to create parent directories for log file");
            }
            
            if(!outputFile.createNewFile()) {
                throw new RuntimeException("Failed to create transaction log file");
            }
        }
        
        StringBuilder output = new StringBuilder();
        DateFormat dateFormat = DateFormat.getDateInstance();
        
        output.append("=== TRANSACTION ");
        output.append(transaction.getTransactionId());
        output.append(" ===\n");
        
        output.append("DATE: ");
        output.append(dateFormat.format(transaction.getTransactionDate()));
        output.append("\n");
        
        output.append("STATUS: ");
        output.append(transaction.getStatus().toString());
        output.append("\n");
        
        output.append("FROM: ");
        output.append(transaction.getFromCardNo());
        output.append("\n");
        
        output.append("TO: ");
        output.append(transaction.getToCardNo());
        output.append("\n");
        
        output.append("AMOUNT: ");
        output.append(transaction.getAmount());
        output.append("\n");
        
        output.append("MESSAGE: ");
        output.append(transaction.getMessage());
        output.append("\n");
        
        output.append("=== END OF TRANSACTION ===");
        output.append("\n\n");
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
        writer.write(output.toString());
        writer.close();
    }
    
    public File getOutputFile() {
        return outputFile;
    }
    
}
