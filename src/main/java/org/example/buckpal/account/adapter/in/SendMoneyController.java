package org.example.buckpal.account.adapter.in;

import lombok.RequiredArgsConstructor;
import org.example.buckpal.account.application.port.in.SendMoneyCommand;
import org.example.buckpal.account.application.port.in.SendMoneyUseCase;
import org.example.buckpal.account.domain.AccountId;
import org.example.buckpal.account.domain.Money;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
@RequiredArgsConstructor
public class SendMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping
    public void sendMoney(@PathVariable("sourceAccountId") Long sourceAccountId,
                          @PathVariable("targetAccountId") Long targetAccountId,
                          @PathVariable("amount") Long amount) {

        SendMoneyCommand command = new SendMoneyCommand(
                new AccountId(sourceAccountId),
                new AccountId(sourceAccountId),
                Money.of(amount)
        );

        sendMoneyUseCase.sendMoney(command);
    }
}
