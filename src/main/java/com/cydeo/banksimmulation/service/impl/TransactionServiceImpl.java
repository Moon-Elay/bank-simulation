package com.cydeo.banksimmulation.service.impl;

import com.cydeo.banksimmulation.entity.Account;
import com.cydeo.banksimmulation.entity.Transaction;
import com.cydeo.banksimmulation.enums.AccountType;
import com.cydeo.banksimmulation.exception.AccountOwnerShipException;
import com.cydeo.banksimmulation.exception.BadRequestException;
import com.cydeo.banksimmulation.exception.BalanceNotSuff;
import com.cydeo.banksimmulation.exception.UnderConstructionException;
import com.cydeo.banksimmulation.repository.AccountRepository;
import com.cydeo.banksimmulation.repository.TransactionRepository;
import com.cydeo.banksimmulation.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {
    @Value("{under_construction}")
    private Boolean underConstruction;

    AccountRepository accountRepository;
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransaction(BigDecimal amount, Date creationDate, Account sender, Account recevier, String message) {
        checkAccountOwnerShip(sender, recevier);
        validateAccounts(sender, recevier);
        executeBalanceUpdateIfRequried(amount, sender, recevier);
        return transactionRepository.save(Transaction.builder().
                amount(amount).
                creationDate(creationDate).
                sender(sender.getId()).
                receiver(recevier.getId()).
                message(message).build());
    else{
        throw new UnderConstructionException("Make Transfer is not avaible");
    }

}
    private void executeBalanceUpdateIfRequried(BigDecimal amount, Account sender, Account recevier) {
        if (checkSenderBalance(sender, amount)){
            sender.setBalance(sender.getBalance().subtract(amount));
            recevier.setBalance(recevier.getBalance().add(amount));
        }
        else {
            throw new BalanceNotSuff("Balance is not suus");
        }
    }

    private boolean checkSenderBalance(Account sender, BigDecimal amount) {
        return  sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO)>0;
    }

    private void validateAccounts(Account sender, Account recevier) {

     if (sender ==null || recevier == null){
      throw  new BadRequestException("sender or recevier cannot be null");
     }
     if (sender.getId().equals(recevier.getId())){
         throw  new BadRequestException("Sender account needs to be different from recevier account");
     }
     findAccountById(sender.getId());
     findAccountById(recevier.getId());
    }

    private Account findAccountById(UUID accountId) {
        return accountRepository.findById(accountId);
    }

    private void checkAccountOwnerShip(Account sender, Account recevier) {
        if((sender.getAccountType().equals(AccountType.SAVINGS) || recevier.getAccountType().equals(AccountType.SAVINGS))
        && !sender.getId().equals(recevier.getId())){
            throw  new AccountOwnerShipException("When one of the account is Savings, sender and recevier has same person");


        }
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
