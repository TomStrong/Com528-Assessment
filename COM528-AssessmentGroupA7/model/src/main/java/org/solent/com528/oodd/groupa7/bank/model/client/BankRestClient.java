package org.solent.com528.oodd.groupa7.bank.model.client;

import org.solent.com528.oodd.groupa7.bank.model.dto.CreditCard;
import org.solent.com528.oodd.groupa7.bank.model.dto.TransactionReplyMessage;

public interface BankRestClient {

    public  TransactionReplyMessage transferMoney(CreditCard fromCard, CreditCard toCard, Double amount);

    public  TransactionReplyMessage transferMoney(CreditCard fromCard, CreditCard toCard, Double amount, String userName, String password);
}
