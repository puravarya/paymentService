package dev.purav.PaymentService04May.controller;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/razorpayWebHook")
public class RazorPayWebHookController {
    @PostMapping("/")
    public RequestEntity acceptWebHookRequest(){
        return null;
    }
}
