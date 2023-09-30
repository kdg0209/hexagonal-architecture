package org.example.buckpal.account.application.service;

import lombok.RequiredArgsConstructor;
import org.example.buckpal.account.application.port.in.SendMoneyCommand;
import org.example.buckpal.account.application.port.in.SendMoneyUseCase;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class SendMoneyService implements SendMoneyUseCase {

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        // TODO: 비지니스 규칙 검증
        // TODO: 모델 상태 조작
        // TODO: 출력 값 반환
        return false;
    }
}
