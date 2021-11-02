/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.assessmentgroupa7.model.dto.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.ood.assessmentgroupa7.model.dto.CreditCard;

/**
 *
 * @author jrandall
 */
public class CreditCardTest {
    
    @Test
    public void testNewCard() {
        CreditCard cc = new CreditCard();
        
        cc.setName("bob");
        assertTrue(cc.getName() == "bob"); 
        
        cc.setEndDate("10/24");
        assertTrue(cc.getEndDate() == "10/24");
        
        cc.setCardnumber("123");
        assertTrue(cc.getCardnumber() == "123");
        
        cc.setIssueNumber("1");
        assertTrue(cc.getIssueNumber() == "1");
        
        cc.setCvv("321");
        assertTrue(cc.getCvv() == "321");
        
       }
    
}
