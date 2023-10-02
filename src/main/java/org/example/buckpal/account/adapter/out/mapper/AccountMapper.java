package org.example.buckpal.account.adapter.out.mapper;

import org.example.buckpal.account.adapter.out.persistence.AccountJPAEntity;
import org.example.buckpal.account.adapter.out.persistence.ActivityJPAEntity;
import org.example.buckpal.account.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper {

    public Account mapToDomainEntity(AccountJPAEntity account, List<ActivityJPAEntity> activities, Long withdrawalBalance, Long depositBalance) {
        Money baselineBalance = Money.subtract(Money.of(depositBalance), Money.of(withdrawalBalance));

        return Account.withId(
                new AccountId(account.getId()),
                baselineBalance,
                mapToActivityWindow(activities));

    }

    public ActivityJPAEntity mapToJpaEntity(Activity activity) {
        return ActivityJPAEntity.builder()
                .id(activity.getId() == null ? null : activity.getId().getValue())
                .ownerAccountId(activity.getOwnerAccountId().getValue())
                .sourceAccountId(activity.getSourceAccountId().getValue())
                .targetAccountId(activity.getTargetAccountId().getValue())
                .amount(activity.getMoney().getAmount().longValue())
                .timestamp(activity.getTimestamp())
                .build();

    }

    private ActivityWindow mapToActivityWindow(List<ActivityJPAEntity> activities) {
        List<Activity> mappedActivities = new ArrayList<>();

        for (ActivityJPAEntity activity : activities) {
            Activity build = Activity.builder()
                    .id(new ActivityId(activity.getId()))
                    .ownerAccountId(new AccountId(activity.getOwnerAccountId()))
                    .sourceAccountId(new AccountId(activity.getSourceAccountId()))
                    .targetAccountId(new AccountId(activity.getTargetAccountId()))
                    .timestamp(activity.getTimestamp())
                    .money(Money.of(activity.getAmount()))
                    .build();
            mappedActivities.add(build);
        }

        return new ActivityWindow(mappedActivities);
    }

}
