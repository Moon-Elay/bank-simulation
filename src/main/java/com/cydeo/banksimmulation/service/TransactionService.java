package com.cydeo.banksimmulation.service;

import com.cydeo.banksimmulation.entity.Account;
import com.cydeo.banksimmulation.entity.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {
    Transaction makeTransaction(BigDecimal amount, Date creationDate, Account sender, Account receiver, String  message);

    List<Transaction> findAll();
}
