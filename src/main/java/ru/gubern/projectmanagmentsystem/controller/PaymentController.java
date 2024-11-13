package ru.gubern.projectmanagmentsystem.controller;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import ru.gubern.projectmanagmentsystem.models.PlanType;
import ru.gubern.projectmanagmentsystem.models.User;
import ru.gubern.projectmanagmentsystem.response.PaymentLinkResponse;
import ru.gubern.projectmanagmentsystem.service.UserService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    private UserService userService;

    public PaymentController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{planType}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @PathVariable PlanType planType,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        int amount = 799*100;
        if (planType.equals(PlanType.ANNUALLY)){
            amount = amount * 12;
            amount = (int) (amount * 0.17);
        }

        RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", amount);
        paymentLinkRequest.put("currency", "RUB");

        JSONObject customer = new JSONObject();
        customer.put("name", user.getFullName());
        customer.put("email", user.getEmail());
        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("email", true);
        paymentLinkRequest.put("notify", notify);

        paymentLinkRequest.put("callback_url","http://localhost:5173/upgrade_plan/success?panType=" + planType);

        PaymentLink paymentLink = razorpay.paymentLink.create(paymentLinkRequest);

        String paymentLinkId = paymentLink.get("id");
        String paymentLinkUrl = paymentLink.get("url");

        PaymentLinkResponse res = new PaymentLinkResponse();
        res.setPayment_link_id(paymentLinkId);
        res.setPayment_link_url(paymentLinkUrl);

        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }
}
