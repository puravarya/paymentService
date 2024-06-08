package dev.purav.PaymentService04May.controller;

import dev.purav.PaymentService04May.dto.InitiatePaymentRequest;
import dev.purav.PaymentService04May.service.PaymentService;
import dev.purav.PaymentService04May.service.RazorPayPaymentService;
import dev.purav.PaymentService04May.service.StripePaymentService;
import dev.purav.PaymentService04May.strategy.PaymentGatewaySelectionStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PaymentController {
    private PaymentService razorPayPaymentService;
    private PaymentService stripePaymentService;
    private PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;


    public PaymentController(
            @Qualifier("razorpay") PaymentService razorPaymentService,
            @Qualifier("stripe") PaymentService stripePaymentService,
            PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy
    ){
this.razorPayPaymentService = razorPaymentService;
this.stripePaymentService = stripePaymentService;
this.paymentGatewaySelectionStrategy = paymentGatewaySelectionStrategy;
    }
    @PostMapping("/payment")
    public String initiatePayment(@RequestBody InitiatePaymentRequest requestDto) {
// return razorPayPaymentService.doPayment(requestDto.getEmail(),
//         requestDto.getPhoneNo(),
//         requestDto.getAmount(),
//         requestDto.getOrderId());
        int paymentGatewayOption = choosePaymentGateway();
        switch (paymentGatewayOption) {
            case 1:
                return razorPayPaymentService.doPayment(requestDto.getEmail(),
                        requestDto.getPhoneNo(),
                        requestDto.getAmount(),
                        requestDto.getOrderId());
            case 2:
                return stripePaymentService.doPayment(requestDto.getEmail(),
                        requestDto.getPhoneNo(),
                        requestDto.getAmount(),
                        requestDto.getOrderId());
//            default:
//                return razorPayPaymentService.doPayment(requestDto.getEmail(),
//                        requestDto.getPhoneNo(),
//                        requestDto.getAmount(),
//                        requestDto.getOrderId());


        }
        return null;
    }
    private int  choosePaymentGateway(){
        return paymentGatewaySelectionStrategy.choosePaymentGateway();
    }
}
