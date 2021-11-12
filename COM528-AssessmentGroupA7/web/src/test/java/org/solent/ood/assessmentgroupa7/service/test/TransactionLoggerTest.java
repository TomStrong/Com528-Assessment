/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.assessmentgroupa7.service.test;

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
    public void LogTransactionTest() {
        

    }

}
