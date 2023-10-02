package org.example.buckpal.account.adapter.out.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.buckpal.account.adapter.out.persistence.AccountJPAEntity;
import org.example.buckpal.account.adapter.out.persistence.AccountRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountDao {

    private final JPAQueryFactory queryFactory;
    private final AccountRepository accountRepository;

    public AccountJPAEntity findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(IllegalStateException::new);
    }
}
