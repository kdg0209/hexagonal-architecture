package org.example.buckpal.account.application.port.in;

import lombok.Getter;
import org.example.buckpal.account.domain.AccountId;
import org.example.buckpal.account.domain.Money;
import org.example.buckpal.common.SelfValidating;

import javax.validation.constraints.NotNull;

@Getter
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

    @NotNull
    private final AccountId sourceAccountIdl;

    @NotNull
    private final AccountId tartgetAccountId;

    @NotNull
    private final Money money;

    public SendMoneyCommand(AccountId sourceAccountIdl, AccountId tartgetAccountId, Money money) {
        this.sourceAccountIdl = sourceAccountIdl;
        this.tartgetAccountId = tartgetAccountId;
        this.money = money;
    }
}
