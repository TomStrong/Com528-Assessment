/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.assessmentgroupa7.service.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.ood.assessmentgroupa7.dao.WebObjectFactory;
import org.solent.ood.assessmentgroupa7.service.Authenticator;

/**
 *
 * @author Tom
 */
public class AuthenticatorTest {
    
    @Test
    public void ValidVerifyAdminTest() {
        Authenticator auth = WebObjectFactory.getAuthenticator();
        boolean result = auth.verifyAdmin("admin", "admin");
        assertEquals(true, result);
    }
    
    @Test
    public void InvalidVerifyAdminTest1() {
        Authenticator auth = WebObjectFactory.getAuthenticator();
        boolean result = auth.verifyAdmin("admin", "wrongpassword");
        assertEquals(false, result);
    }
    
    @Test
    public void InvalidVerifyAdminTest2() {
        Authenticator auth = WebObjectFactory.getAuthenticator();
        boolean result = auth.verifyAdmin("wrongusername", "admin");
        assertEquals(false, result);
    }
    
    @Test
    public void InvalidVerifyAdminTest3() {
        Authenticator auth = WebObjectFactory.getAuthenticator();
        boolean result = auth.verifyAdmin("wrongusername", "wrongpassword");
        assertEquals(false, result);
    }
}
