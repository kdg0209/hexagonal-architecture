package org.example.buckpal.account.application.port.out;

import org.example.buckpal.account.domain.Account;
import org.example.buckpal.account.domain.AccountId;

import java.time.LocalDateTime;

public interface LoadAccountPort {

    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
