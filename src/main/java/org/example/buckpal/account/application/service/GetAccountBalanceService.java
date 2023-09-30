package org.example.buckpal.account.application.service;

import lombok.RequiredArgsConstructor;
import org.example.buckpal.account.application.port.in.GetAccountBalanceQuery;
import org.example.buckpal.account.domain.AccountId;
import org.example.buckpal.account.domain.Money;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {

    @Override
    public Money getAccountBalance(AccountId accountId) {
        return null;
    }
}
