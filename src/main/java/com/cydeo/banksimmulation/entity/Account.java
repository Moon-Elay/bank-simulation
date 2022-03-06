package com.cydeo.banksimmulation.entity;

import com.cydeo.banksimmulation.enums.AccountType;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
@Builder
@lombok.Data
public class Account {
    private UUID id;
    private BigDecimal balance;
    private AccountType accountType;
    private Date creationDate;
    private  Long userId;


}
