package org.example.buckpal.account.application.port.in;

import org.example.buckpal.account.domain.AccountId;
import org.example.buckpal.account.domain.Money;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(AccountId accountId);
}
