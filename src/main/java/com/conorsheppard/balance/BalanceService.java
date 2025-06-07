package com.conorsheppard.balance;

import java.math.BigDecimal;

public interface BalanceService {
    BigDecimal getUserBalance(String userRef);
    BigDecimal changeBalanceForUser(String userRef, BigDecimal newBalance);
}
