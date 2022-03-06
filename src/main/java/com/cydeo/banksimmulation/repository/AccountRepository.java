package com.cydeo.banksimmulation.repository;

import com.cydeo.banksimmulation.entity.Account;
import com.cydeo.banksimmulation.exception.RecordNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {
    AccountRepository accountRepository;
    public static List<Account> accountList = new ArrayList<>();

    public  Account save(Account account){
        accountList.add(account);
        return account;
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account findById(UUID accountId) {
        accountList.stream().filter(account -> account.getId().equals(accountId)).findAny().orElseThrow(() ->
                new RecordNotFoundException("This account is not in the database"));
        return null;
    }
}
