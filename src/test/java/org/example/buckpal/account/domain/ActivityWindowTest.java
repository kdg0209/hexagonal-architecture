package org.example.buckpal.account.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ActivityWindowTest {

    @Test
    void 세개의_Activity_가_ActivityWindow에_정상적으로_저장된다() {

        // given
        AccountId account1 = new AccountId(1L);
        AccountId account2 = new AccountId(2L);
        Money money = Money.of(999);

        Activity activity1 = Activity.builder()
                .sourceAccountId(account1)
                .targetAccountId(account2)
                .money(money)
                .timestamp(LocalDateTime.of(2019, 8, 3, 0, 0))
                .build();

        Activity activity2 = Activity.builder()
                .sourceAccountId(account1)
                .targetAccountId(account2)
                .money(money)
                .timestamp(LocalDateTime.of(2019, 8, 4, 0, 0))
                .build();

        Activity activity3 = Activity.builder()
                .sourceAccountId(account1)
                .targetAccountId(account2)
                .money(money)
                .timestamp(LocalDateTime.of(2019, 8, 5, 0, 0))
                .build();

        // when
        ActivityWindow activityWindow = new ActivityWindow(List.of(activity1, activity2, activity3));

        // then
        assertThat(activityWindow).isNotNull();
    }

    @Test
    void 특정_계좌의_잔액을_계산한다() {

        // given
        AccountId account1 = new AccountId(1L);
        AccountId account2 = new AccountId(2L);
        Money money = Money.of(999);

        Activity activity1 = Activity.builder()
                .sourceAccountId(account1)
                .targetAccountId(account2)
                .money(money)
                .timestamp(LocalDateTime.of(2019, 8, 3, 0, 0))
                .build();

        Activity activity2 = Activity.builder()
                .sourceAccountId(account1)
                .targetAccountId(account2)
                .money(money)
                .timestamp(LocalDateTime.of(2019, 8, 4, 0, 0))
                .build();

        Activity activity3 = Activity.builder()
                .sourceAccountId(account1)
                .targetAccountId(account2)
                .money(money)
                .timestamp(LocalDateTime.of(2019, 8, 5, 0, 0))
                .build();

        ActivityWindow activityWindow = new ActivityWindow(List.of(activity1, activity2, activity3));

        // when
        Money result = activityWindow.calculateBalance(account1);

        //then
        assertThat(result.getAmount()).isNegative();
    }
}