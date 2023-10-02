package org.example.buckpal.account.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityJPAEntity, Long> {
}
