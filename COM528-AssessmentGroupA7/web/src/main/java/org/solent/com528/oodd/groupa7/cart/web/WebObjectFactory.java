/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.oodd.groupa7.cart.web;

import org.solent.com528.oodd.groupa7.cart.service.ShoppingServiceImpl;
import org.solent.com528.oodd.groupa7.cart.service.ShoppingCartImpl;
import org.solent.com528.oodd.groupa7.cart.model.service.ShoppingCart;
import org.solent.com528.oodd.groupa7.cart.model.service.ShoppingService;
import org.solent.com528.oodd.groupa7.cart.service.ServiceObjectFactory;

/**
 *
 * @author cgallen
 */
public class WebObjectFactory {

    static ShoppingService shoppingService = ServiceObjectFactory.getShoppingService();
    
    // cannot instantiate
    private WebObjectFactory(){
        
    }
    
    public static ShoppingService getShoppingService(){
        return shoppingService;
    }
    
    public static ShoppingCart getNewShoppingCart(){
        return ServiceObjectFactory.getNewShoppingCart();
    }
    
}
