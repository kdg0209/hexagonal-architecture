package org.example.buckpal.account.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "activity")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActivityJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_account_id")
    private Long ownerAccountId;

    @Column(name = "source_account_id")
    private Long sourceAccountId;

    @Column(name = "target_account_id")
    private Long targetAccountId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Builder
    public ActivityJPAEntity(Long id, Long ownerAccountId, Long sourceAccountId, Long targetAccountId, Long amount, LocalDateTime timestamp) {
        this.id = id;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
