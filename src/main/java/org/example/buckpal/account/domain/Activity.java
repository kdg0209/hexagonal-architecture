package org.example.buckpal.account.domain;

import java.time.LocalDateTime;

public class Activity {

    private ActivityId id;

    /**
     * The account that owns this activity.
     */
    private final AccountId ownerAccountId;

    /**
     * The debited account.
     */
    private final AccountId sourceAccountId;

    /**
     * The credited account.
     */
    private final AccountId targetAccountId;

    private final LocalDateTime timestamp;

    private final Money money;

    public Activity(AccountId ownerAccountId, AccountId sourceAccountId, AccountId targetAccountId, LocalDateTime timestamp, Money money) {
        this.id = null;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.timestamp = timestamp;
        this.money = money;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Money getMoney() {
        return money;
    }

    public AccountId getTargetAccountId() {
        return targetAccountId;
    }

    public AccountId getSourceAccountId() {
        return sourceAccountId;
    }

    public boolean isEqualsAccountId(AccountId accountId) {
        return this.id.equals(accountId);
    }
}
