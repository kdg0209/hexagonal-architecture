package org.example.buckpal.account.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Account {

    private final AccountId id;
    private final Money baselineBalance;
    private final ActivityWindow activityWindow;

    private Account(AccountId id, Money baselineBalance, ActivityWindow activityWindow) {
        this.id = id;
        this.baselineBalance = baselineBalance;
        this.activityWindow = activityWindow;
    }

    public static Account withId(AccountId accountId, Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(accountId, baselineBalance, activityWindow);
    }

    public Money calculateBalance() {
        return Money.add(this.baselineBalance, this.activityWindow.calculateBalance(this.id));
    }

    public boolean withdraw(Money money, AccountId tartgetAccountId) {
        if (!mayWithdraw(money)) {
            return false;
        }

        Activity activity = Activity.builder()
                .ownerAccountId(this.id)
                .sourceAccountId(this.id)
                .targetAccountId(tartgetAccountId)
                .timestamp(LocalDateTime.now())
                .money(money)
                .build();

        this.activityWindow.addActivity(activity);
        return true;
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {
        Activity activity = Activity.builder()
                .ownerAccountId(this.id)
                .sourceAccountId(sourceAccountId)
                .targetAccountId(this.id)
                .timestamp(LocalDateTime.now())
                .money(money)
                .build();

        this.activityWindow.addActivity(activity);
        return true;
    }

    public List<Activity> findAllActivities() {
        return this.activityWindow.getActivities();
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(this.baselineBalance, money.negate()).isPositive();
    }

}
