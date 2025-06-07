package com.conorsheppard.balance;

import java.math.BigDecimal;

import static com.conorsheppard.balance.StatusCode.SUCCESS;

public class WalletService {
    private final BalanceService balanceService;

    public WalletService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    /**
     * Performs a deposit operation in the system.
     */
    public DepositResponse deposit(DepositRequest depositRequest) {
        BigDecimal currentBalance = balanceService.getUserBalance(depositRequest.userRef());
        BigDecimal newBalance = balanceService.changeBalanceForUser(depositRequest.userRef(), currentBalance.add(depositRequest.amount()));
        return new DepositResponse(null, depositRequest.bankTxId(), newBalance, SUCCESS, null);
    }

    /**
     * Performs a withdrawal operation in the system. The account balance should not go below 0.
     */
    public WithdrawResponse withdraw(WithdrawRequest withdrawRequest) {
        BigDecimal currentBalance = balanceService.getUserBalance(withdrawRequest.userRef());
        BigDecimal newBalance = balanceService.changeBalanceForUser(withdrawRequest.userRef(), currentBalance.subtract(withdrawRequest.amount()));
        return new WithdrawResponse(null, withdrawRequest.bankTxId(), newBalance, SUCCESS, null);
    }
}
