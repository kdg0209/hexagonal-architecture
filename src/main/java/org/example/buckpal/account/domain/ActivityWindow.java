package org.example.buckpal.account.domain;

import java.util.List;

public class ActivityWindow {

    private List<Activity> activities;

    public ActivityWindow(List<Activity> activities) {
        this.activities = activities;
    }

    public Money calculateBalance(AccountId accountId) {
        Money depositBalance = this.activities.stream()
                .filter(a -> a.isEqualsAccountId(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::add);

        Money withdrawalBalance = this.activities.stream()
                .filter(a -> a.isEqualsAccountId(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::add);

        return Money.add(depositBalance, withdrawalBalance.negate());
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
}
