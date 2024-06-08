package dev.purav.PaymentService04May.service;

import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    String doPayment(String email,
                     String phoneNo,
                     Long amount,
                     String orderId) throws RazorpayException;
}
