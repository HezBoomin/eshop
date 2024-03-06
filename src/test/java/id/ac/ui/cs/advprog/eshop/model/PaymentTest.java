package id.ac.ui.cs.advprog.eshop.model;



import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;
    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentVoucherCodeMethod() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", this.paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("WAITING", payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentBankTransferMethod() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", this.paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("BANK_TRANSFER", payment.getMethod());
        assertEquals("WAITING", payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "INVALID_METHOD", this.paymentData);
        });
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", this.paymentData);
        assertEquals("WAITING", payment.getStatus());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", this.paymentData, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", this.paymentData, "REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", this.paymentData, "INVALID_STATUS");
        });
    }

}
