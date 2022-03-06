package com.cydeo;

import com.cydeo.banksimmulation.entity.Account;
import com.cydeo.banksimmulation.enums.AccountType;
import com.cydeo.banksimmulation.service.AccountService;
import com.cydeo.banksimmulation.service.TransactionService;
import com.cydeo.banksimmulation.service.impl.AccountServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankSimulationApplication.class, args);
        AccountService accountService = applicationContext.getBean(AccountServiceImpl.class);
        TransactionService transactionService = applicationContext.getBean(AccountServiceImpl.class);

        Account receiver = accountService.createNewAccount( BigDecimal.TEN, new Date(), AccountType.CHECKINGS, 1L);
        Account sender =  accountService.createNewAccount(new BigDecimal(70), new Date(), AccountType.CHECKINGS, 1L);

        accountService.listAllAccount().forEach(System.out::println);

        transactionService.makeTransaction(BigDecimal.TEN, new Date(), sender, receiver, "transfer no:1");

        System.out.println(transactionService.findAll().get(0));
        accountService.listAllAccount().forEach(System.out::println);

        transactionService.makeTransaction(new BigDecimal(25), new Date(), sender, receiver, "transfer no:2");

        System.out.println(transactionService.findAll().get(1));
        accountService.listAllAccount().forEach(System.out::println);
    }

}
