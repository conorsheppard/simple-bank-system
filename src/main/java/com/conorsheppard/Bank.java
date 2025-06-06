package com.conorsheppard;

import java.util.Arrays;

class Bank {
    private final long[] accounts;

    public Bank(long[] balance) {
        accounts = Arrays.copyOf(balance, balance.length);
    }

    public boolean transfer(int account1, int account2, long money) {
        if (!allValidAccounts(account1, account2)) return false;
        if (!hasSufficientFunds(account1, money)) return false;
        debitAccount(account1, money);
        creditAccount(account2, money);
        return true;
    }

    public boolean deposit(int account, long money) {
        if (!isValidAccount(account)) return false;
        creditAccount(account, money);
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (!isValidAccount(account)) return false;
        if (!hasSufficientFunds(account, money)) return false;
        debitAccount(account, money);
        return true;
    }

    private void creditAccount(int account, long amount) {
        accounts[account - 1] = accounts[account - 1] + amount;
    }

    private void debitAccount(int account, long amount) {
        accounts[account - 1] = accounts[account - 1] - amount;
    }

    private boolean hasSufficientFunds(int account, long money) {
        return (accounts[account - 1] >= money);
    }

    private boolean allValidAccounts(int... accounts) {
        return Arrays.stream(accounts).allMatch(this::isValidAccount);
    }

    private boolean isValidAccount(int account) {
        return account >= 1 && account <= accounts.length;
    }
}