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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles logging of transactions as they happen to a file on the system.
 * @author Jake Roblett
 */
public class TransactionLogger {
    
    public static final TransactionLogger INSTANCE = new TransactionLogger();
    private final static Logger LOG = LogManager.getLogger(TransactionLogger.class);
    
    private final File outputFile;
    
    /**
     * Creates a new transaction logger
     */
    private TransactionLogger() {
        String TEMP_DIR = System.getProperty("java.io.tmpdir");
        this.outputFile = new File(TEMP_DIR + "/transactions.txt");
        
        System.out.println("Transaction log path: " + outputFile.getAbsolutePath());
    }
    
    /**
     * Logs a bank transaction to the log file.
     * @param message Transaction string to log
     * @throws IOException Thrown when writing to file failed 
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
        writer.write(message);
        writer.write("\n");
        writer.close();
    }
    
    public File getOutputFile() {
        return outputFile;
    }
    
}
