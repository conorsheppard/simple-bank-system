package com.conorsheppard.balance;

import java.math.BigDecimal;

public record DepositRequest(String userRef, String bankTxId, BigDecimal amount) {
}
