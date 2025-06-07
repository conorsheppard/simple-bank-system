package com.conorsheppard.balance;

import java.math.BigDecimal;

public record WithdrawRequest(String userRef, String bankTxId, BigDecimal amount) {
}
