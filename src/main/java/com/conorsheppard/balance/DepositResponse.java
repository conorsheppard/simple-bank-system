package com.conorsheppard.balance;

import java.math.BigDecimal;

public record DepositResponse(String internalTxId, String bankTxId, BigDecimal newBalance, StatusCode status,
                              String error) {
}
