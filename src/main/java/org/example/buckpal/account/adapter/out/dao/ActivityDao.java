package org.example.buckpal.account.adapter.out.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.buckpal.account.adapter.out.persistence.ActivityJPAEntity;
import org.example.buckpal.account.adapter.out.persistence.ActivityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.buckpal.account.adapter.out.persistence.QActivityJPAEntity.activityJPAEntity;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ActivityDao {

    private final JPAQueryFactory queryFactory;
    private final ActivityRepository activityRepository;

    public List<ActivityJPAEntity> findByOwnerSince(Long ownerAccountId, LocalDateTime since) {
        return queryFactory
                .selectFrom(activityJPAEntity)
                .where(
                        activityJPAEntity.ownerAccountId.eq(ownerAccountId),
                        activityJPAEntity.timestamp.goe(since)
                )
                .fetch();
    }

    public Long getDepositBalanceUntil(Long accountId, LocalDateTime until) {
        return queryFactory
                .select(activityJPAEntity.amount.sum())
                .from(activityJPAEntity)
                .where(
                        activityJPAEntity.targetAccountId.eq(accountId),
                        activityJPAEntity.ownerAccountId.eq(accountId),
                        activityJPAEntity.timestamp.lt(until)
                )
                .fetchFirst();
    }

    public Long getWithdrawalBalanceUntil(Long accountId, LocalDateTime until) {
        return queryFactory
                .select(activityJPAEntity.amount.sum())
                .from(activityJPAEntity)
                .where(
                        activityJPAEntity.sourceAccountId.eq(accountId),
                        activityJPAEntity.ownerAccountId.eq(accountId),
                        activityJPAEntity.timestamp.lt(until)
                )
                .fetchFirst();
    }

    public void save(ActivityJPAEntity entity) {
        this.activityRepository.save(entity);
    }
}
