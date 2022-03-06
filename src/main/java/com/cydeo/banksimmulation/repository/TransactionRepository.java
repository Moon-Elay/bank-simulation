package com.cydeo.banksimmulation.repository;

import com.cydeo.banksimmulation.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
public List<Transaction> transactionList = new ArrayList<>();

public Transaction save(Transaction transaction){
    transactionList.add(transaction);
    return transaction;
}

public List<Transaction> findAll(){
    return transactionList;
}


}
