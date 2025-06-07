package com.conorsheppard.balance;

import java.math.BigDecimal;

public record WithdrawResponse(String internalTxId, String bankTxId, BigDecimal newBalance, StatusCode status,
                               String error) {
}
