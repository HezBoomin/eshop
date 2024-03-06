package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class PaymentVoucherCode extends Payment{
    public PaymentVoucherCode(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }

    public PaymentVoucherCode(String id, String method, Map<String, String> paymentData, String status) {
        super(id, method, paymentData, status);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        } else if (paymentData.get("voucherCode").isEmpty()) {
            this.setStatus(PaymentStatus.REJECTED.getValue());
        } else if (paymentData.get("voucherCode").length() < 16) {
            this.setStatus(PaymentStatus.REJECTED.getValue());
        } else if (!paymentData.get("voucherCode").startsWith("ESHOP")) {
            this.setStatus(PaymentStatus.REJECTED.getValue());
        } else {
            this.paymentData = paymentData;
            this.setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }
}
