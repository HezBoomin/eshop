package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentBankTransfer extends Payment{

    public PaymentBankTransfer(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }

    public PaymentBankTransfer(String id, String method, Map<String, String> paymentData, String status) {
        super(id, method, paymentData, status);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.isEmpty()) {
            this.setStatus("REJECTED");
        } else if (paymentData.get("bankName").isEmpty() || paymentData.get("referenceCode").isEmpty()) {
            this.setStatus("REJECTED");
        } else {
            this.paymentData = paymentData;
            this.setStatus("SUCCESS");
        }
    }
}
