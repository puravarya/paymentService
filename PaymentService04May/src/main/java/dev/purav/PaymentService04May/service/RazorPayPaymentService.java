package dev.purav.PaymentService04May.service;

import com.razorpay.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPayPaymentService implements PaymentService {

    private final RazorpayClient razorpayClient;
    public RazorPayPaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String doPayment(String email, String phoneNo, Long amount, String orderId) throws RazorpayException {
       try {

           JSONObject paymentLinkRequest = new JSONObject();
           paymentLinkRequest.put("amount", amount);
           paymentLinkRequest.put("currency", "INR");
           paymentLinkRequest.put("receipt", "orderId");
           JSONObject notes = new JSONObject();
           JSONObject customer = new JSONObject();
           customer.put("email", email);
           customer.put("phoneNo", phoneNo);
           paymentLinkRequest.put("customers", customer);
           JSONObject notify = new JSONObject();
           notify.put("sms", true);
           notify.put("email", true);
           paymentLinkRequest.put("notify", notify);

           paymentLinkRequest.put("callback url", "https://xyz.com/razorWebHook");

           paymentLinkRequest.put("callback method", "post");

           PaymentLink response = razorpayClient.paymentLink.create(paymentLinkRequest);
           return response.toString();
       } catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }
}
