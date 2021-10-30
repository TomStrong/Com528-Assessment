/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.oodd.groupa7.cart.model.service;

import org.solent.com528.oodd.groupa7.cart.model.dto.ShoppingItem;
import java.util.List;

/**
 *
 * @author cgallen
 */
public interface ShoppingService {
    
        public List<ShoppingItem> getAvailableItems();
        
        public boolean purchaseItems(ShoppingCart shoppingCart);
        
        public ShoppingItem getNewItemByName(String uuid);

}
