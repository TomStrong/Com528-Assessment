/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.oodd.groupa7.cart.service.test;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com528.oodd.groupa7.cart.model.dto.ShoppingItem;
import org.solent.com528.oodd.groupa7.cart.model.service.ShoppingCart;
import org.solent.com528.oodd.groupa7.cart.service.ServiceObjectFactory;

/**
 *
 * @author cgallen
 */
public class ShoppingCartTest {

    ShoppingCart shoppingCart = null;

    @Before
    public void before() {
        shoppingCart = ServiceObjectFactory.getNewShoppingCart();
        shoppingCart.getShoppingCartItems().clear();
    }

    @Test
    public void test1() {
        assertNotNull(shoppingCart);
    }

    @Test
    public void testshoppingcart() {
        assertNotNull(shoppingCart);
        
        List<ShoppingItem> items = shoppingCart.getShoppingCartItems();
        
        assertTrue(items.isEmpty());
        
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setName("fred");
        
        shoppingCart.addItemToCart(shoppingItem);
        
        assertEquals(1, shoppingCart.getShoppingCartItems().size() );

    }

    // add your own tests here
}
