package org.example.buckpal.account.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.example.buckpal.account.adapter.out.dao.AccountDao;
import org.example.buckpal.account.adapter.out.dao.ActivityDao;
import org.example.buckpal.account.adapter.out.mapper.AccountMapper;
import org.example.buckpal.account.application.port.out.LoadAccountPort;
import org.example.buckpal.account.application.port.out.UpdateAccountStatePort;
import org.example.buckpal.account.domain.Account;
import org.example.buckpal.account.domain.AccountId;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {

    private final AccountMapper accountMapper;
    private final AccountDao accountDao;
    private final ActivityDao activityDao;

    @Override
    public Account loadAccount(AccountId accountId, LocalDateTime baselineDate) {
        AccountJPAEntity entity = accountDao.findById(accountId.getValue());
        List<ActivityJPAEntity> activities = activityDao.findByOwnerSince(accountId.getValue(), baselineDate);
        Long withdrawalBalance = activityDao.getWithdrawalBalanceUntil(accountId.getValue(), baselineDate);
        Long depositBalance = activityDao.getDepositBalanceUntil(accountId.getValue(), baselineDate);

        return accountMapper.mapToDomainEntity(entity, activities, withdrawalBalance, depositBalance);
    }

    @Override
    public void updateActivities(Account account) {
        account.findAllActivities().stream()
                .filter(activity -> activity.getId() == null)
                .map(accountMapper::mapToJpaEntity)
                .forEach(activityDao::save);
    }
}
