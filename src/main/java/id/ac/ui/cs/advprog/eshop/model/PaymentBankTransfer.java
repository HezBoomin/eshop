package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (paymentData.get("bankName") == null && paymentData.get("referenceCode") == null) {
            throw new IllegalArgumentException();
        }

        if (paymentData.get("bankName").isEmpty() || paymentData.get("referenceCode").isEmpty()) {
            this.setStatus(PaymentStatus.REJECTED.getValue());
        } else {
            this.paymentData = paymentData;
            this.setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }
}
