package org.example.buckpal.account.domain;

import java.time.LocalDateTime;

public class Account {

    private final AccountId id;
    private final Money baselineBalance;
    private final ActivityWindow activityWindow;

    private Account(AccountId id, Money baselineBalance, ActivityWindow activityWindow) {
        this.id = id;
        this.baselineBalance = baselineBalance;
        this.activityWindow = activityWindow;
    }

    public Money calculateBalance() {
        return Money.add(this.baselineBalance, this.activityWindow.calculateBalance(this.id));
    }

    public boolean withdraw(Money money, AccountId tartgetAccountId) {
        if (!mayWithdraw(money)) {
            return false;
        }

        Activity activity = new Activity(
                this.id,
                this.id,
                tartgetAccountId,
                LocalDateTime.now(),
                money
        );

        this.activityWindow.addActivity(activity);
        return true;
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {
        Activity activity = new Activity(
                this.id,
                sourceAccountId,
                this.id,
                LocalDateTime.now(),
                money
        );

        this.activityWindow.addActivity(activity);
        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(this.baselineBalance, money.negate()).isPositive();
    }

}
