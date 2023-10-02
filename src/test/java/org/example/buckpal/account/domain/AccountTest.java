package org.example.buckpal.account.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    private ActivityWindow activityWindow;

    @BeforeEach
    void setUp() {
        Activity activity1 = Activity.builder()
                .id(new ActivityId(42L))
                .ownerAccountId(new AccountId(42L))
                .sourceAccountId(new AccountId(42L))
                .targetAccountId(new AccountId(42L))
                .timestamp(LocalDateTime.now())
                .money(Money.of(999L))
                .build();

        Activity activity2 = Activity.builder()
                .id(new ActivityId(42L))
                .ownerAccountId(new AccountId(42L))
                .sourceAccountId(new AccountId(42L))
                .targetAccountId(new AccountId(42L))
                .timestamp(LocalDateTime.now())
                .money(Money.of(1L))
                .build();

        List<Activity> activities = new ArrayList<>();
        activities.add(activity1);
        activities.add(activity2);
        activityWindow = new ActivityWindow(activities);
    }

    @Test
    void withdrawalSucceeds() {

        // given
        Account account = defaultAccount();

        // when
        boolean result = account.withdraw(Money.of(555L), new AccountId(99L));

        // then
        assertThat(result).isTrue();
        assertThat(account.findAllActivities().size()).isEqualTo(3);
        assertThat(account.calculateBalance().getAmount()).isEqualTo("444");
    }


    private Account defaultAccount() {
        return Account.builder()
                .id(new AccountId(42L))
                .baselineBalance(Money.of(999L))
                .activityWindow(activityWindow)
                .build();
    }
}