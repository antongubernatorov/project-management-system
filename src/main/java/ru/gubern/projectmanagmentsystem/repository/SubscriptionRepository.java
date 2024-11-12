package ru.gubern.projectmanagmentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gubern.projectmanagmentsystem.models.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription findByUserId(Long userId);
}
