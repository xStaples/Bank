package com.revature.model;

public class BankAccount {
    private int accountId;
    private int accountNum;
    private String accountType;
    private String accountOwner;
    private double accountBalance;

    public BankAccount() {
        super();
    }

    public BankAccount(int accountId, int accountNum, String accountType, String accountOwner, double accountBalance) {
        this.accountId = accountId;
        this.accountNum = accountNum;
        this.accountType = accountType;
        this.accountOwner = accountOwner;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "BankAccount [accountBalance=" + accountBalance + ", accountId=" + accountId + ", accountNum="
                + accountNum + ", accountOwner=" + accountOwner + ", accountType=" + accountType + "]";
    }
    

    
}
