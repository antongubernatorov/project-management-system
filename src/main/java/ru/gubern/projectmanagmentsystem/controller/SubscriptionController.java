package ru.gubern.projectmanagmentsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gubern.projectmanagmentsystem.models.PlanType;
import ru.gubern.projectmanagmentsystem.models.Subscription;
import ru.gubern.projectmanagmentsystem.models.User;
import ru.gubern.projectmanagmentsystem.service.SubscriptionService;
import ru.gubern.projectmanagmentsystem.service.UserService;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    private UserService userService;
    public SubscriptionController(SubscriptionService subscriptionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscription(@RequestHeader("Authorization") String jwt) throws Exception {
        var user = userService.findUserProfileByJwt(jwt);

        var usersSubscription = subscriptionService.getUsersSubscription(user.getId());

        return new ResponseEntity<>(usersSubscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> upgradeSubscription(@RequestHeader("Authorization") String jwt,
    @RequestParam PlanType planType) throws Exception {
        var user = userService.findUserProfileByJwt(jwt);

        var usersSubscription = subscriptionService.upgradeSubscription(user.getId(), planType);

        return new ResponseEntity<>(usersSubscription, HttpStatus.OK);
    }
}
