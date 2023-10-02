package org.example.buckpal.account.adapter.out.persistence;

import org.example.buckpal.account.adapter.out.dao.AccountDao;
import org.example.buckpal.account.adapter.out.dao.ActivityDao;
import org.example.buckpal.account.adapter.out.mapper.AccountMapper;
import org.example.buckpal.account.domain.Account;
import org.example.buckpal.account.domain.AccountId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccountPersistenceAdapterTest {

    @Autowired
    private AccountPersistenceAdapter adapter;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ActivityDao activityDao;

    @Test
    @Sql(scripts = "/sql/account/AccountPersistenceAdapterTest.sql")
    void loadAccount() {

        // when
        Account result = adapter.loadAccount(new AccountId(1L), LocalDateTime.of(2018, 8, 10, 0, 0));

        // then
        assertThat(result.findAllActivities().size()).isEqualTo(2);
    }
}