package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentVoucherCodeTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
    this.paymentData = new HashMap<>();
    }
    @Test
    void testSetPaymentDataWithEmptyPaymentData() {
    PaymentVoucherCode paymentVoucherCode = new PaymentVoucherCode("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", this.paymentData);
    assertThrows(IllegalArgumentException.class, () -> {
        paymentVoucherCode.setPaymentData(this.paymentData);
    });
    }

    @Test
    void testSetPaymentDataWithValidVoucherCode() {
    this.paymentData.put("voucherCode", "ESHOP12345678ABC");
    PaymentVoucherCode paymentVoucherCode = new PaymentVoucherCode("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", this.paymentData);
    paymentVoucherCode.setPaymentData(this.paymentData);
    assertEquals("SUCCESS", paymentVoucherCode.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidVoucherCodeWithShortLength() {
    this.paymentData.put("voucherCode", "ESHOP12345678AB");
    PaymentVoucherCode paymentVoucherCode = new PaymentVoucherCode("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", this.paymentData);
    paymentVoucherCode.setPaymentData(this.paymentData);
    assertEquals("REJECTED", paymentVoucherCode.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidVoucherCodeWithoutEshop() {
    this.paymentData.put("voucherCode", "12345678ABC");
    PaymentVoucherCode paymentVoucherCode = new PaymentVoucherCode("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", this.paymentData);
    paymentVoucherCode.setPaymentData(this.paymentData);
    assertEquals("REJECTED", paymentVoucherCode.getStatus());
    }
}
