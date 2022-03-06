package com.cydeo.banksimmulation.service;

import com.cydeo.banksimmulation.entity.Account;
import com.cydeo.banksimmulation.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {
   Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId);

   List<Account> listAllAccount();


}
