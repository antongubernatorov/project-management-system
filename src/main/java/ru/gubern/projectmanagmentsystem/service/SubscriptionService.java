package ru.gubern.projectmanagmentsystem.service;

import ru.gubern.projectmanagmentsystem.models.PlanType;
import ru.gubern.projectmanagmentsystem.models.Subscription;
import ru.gubern.projectmanagmentsystem.models.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUsersSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}
