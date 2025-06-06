package com.conorsheppard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankTest {

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("depositCases")
    void testDeposit(String name, Bank bank, int account, long amount, boolean expected) {
        assertEquals(expected, bank.deposit(account, amount));
    }

    static Stream<Arguments> depositCases() {
        return Stream.of(
                Arguments.of("Valid deposit into account 1", new Bank(new long[]{100, 200, 300}), 1, 50, true),
                Arguments.of("Invalid deposit into non-existent account 4", new Bank(new long[]{100, 200, 300}), 4, 100, false)
        );
    }

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("withdrawCases")
    void testWithdraw(String name, Bank bank, int account, long amount, boolean expected) {
        assertEquals(expected, bank.withdraw(account, amount));
    }

    static Stream<Arguments> withdrawCases() {
        return Stream.of(
                Arguments.of("Valid withdraw from account 2", new Bank(new long[]{100, 200, 300}), 2, 100, true),
                Arguments.of("Withdraw too much from account 3", new Bank(new long[]{100, 200, 300}), 3, 500, false),
                Arguments.of("Withdraw from non-existent account 5", new Bank(new long[]{100, 200, 300}), 5, 10, false)
        );
    }

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("transferCases")
    void testTransfer(String name, Bank bank, int fromAcc, int toAcc, long amount, boolean expected) {
        assertEquals(expected, bank.transfer(fromAcc, toAcc, amount));
    }

    static Stream<Arguments> transferCases() {
        return Stream.of(
                Arguments.of("Valid transfer from account 1 to 2", new Bank(new long[]{100, 200, 300}), 1, 2, 50, true),
                Arguments.of("Insufficient funds for transfer from 1 to 2", new Bank(new long[]{100, 200, 300}), 1, 2, 500, false),
                Arguments.of("Transfer from non-existent account 4 to 2", new Bank(new long[]{100, 200, 300}), 4, 2, 50, false),
                Arguments.of("Transfer to non-existent account 5", new Bank(new long[]{100, 200, 300}), 1, 5, 50, false)
        );
    }
}
