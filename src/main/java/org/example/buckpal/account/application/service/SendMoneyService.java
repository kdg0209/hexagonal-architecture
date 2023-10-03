package org.example.buckpal.account.application.service;

import lombok.RequiredArgsConstructor;
import org.example.buckpal.account.application.port.in.SendMoneyCommand;
import org.example.buckpal.account.application.port.in.SendMoneyUseCase;
import org.example.buckpal.account.application.port.out.LoadAccountPort;
import org.example.buckpal.account.application.port.out.UpdateAccountStatePort;
import org.example.buckpal.account.domain.Account;
import org.example.buckpal.account.domain.AccountId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class SendMoneyService implements SendMoneyUseCase {

    private static final long TEN_DAY = 10L;

    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        LocalDateTime baselineDate = LocalDateTime.now().minusDays(TEN_DAY);
        Account sourceAccount = loadAccountPort.loadAccount(command.getSourceAccountIdl(), baselineDate);
        Account targetAccount = loadAccountPort.loadAccount(command.getTartgetAccountId(), baselineDate);

        AccountId sourceAccountId = sourceAccount.getId();
        AccountId targetAccountId = targetAccount.getId();

        if (!sourceAccount.withdraw(command.getMoney(), targetAccountId)) {
            return false;
        }

        if (!targetAccount.deposit(command.getMoney(), sourceAccountId)) {
            return false;
        }

        // 모델 상태 조작
        updateAccountStatePort.updateActivities(sourceAccount);
        updateAccountStatePort.updateActivities(targetAccount);

        // 출력 값 반환
        return true;
    }
}
