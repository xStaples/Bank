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

    public BankAccount(String accountType, String accountOwner, double accountBalance){
        this.accountType = accountType;
        this.accountOwner = accountOwner;
        this.accountBalance = accountBalance;
    }

    public BankAccount(int accountNum, String accountType, String accountOwner, double accountBalance) {
        this.accountNum = accountNum;
        this.accountType = accountType;
        this.accountOwner = accountOwner;
        this.accountBalance = accountBalance;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(accountBalance);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + accountId;
        result = prime * result + accountNum;
        result = prime * result + ((accountOwner == null) ? 0 : accountOwner.hashCode());
        result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BankAccount other = (BankAccount) obj;
        if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
            return false;
        if (accountId != other.accountId)
            return false;
        if (accountNum != other.accountNum)
            return false;
        if (accountOwner == null) {
            if (other.accountOwner != null)
                return false;
        } else if (!accountOwner.equals(other.accountOwner))
            return false;
        if (accountType == null) {
            if (other.accountType != null)
                return false;
        } else if (!accountType.equals(other.accountType))
            return false;
        return true;
    }

}
