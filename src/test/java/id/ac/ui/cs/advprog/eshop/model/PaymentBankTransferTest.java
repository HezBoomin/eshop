package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentBankTransferTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testSetPaymentData(){
        this.paymentData.put("bankName", "Mandiri");
        this.paymentData.put("referenceCode", "1234567890");
        PaymentBankTransfer paymentBankTransfer = new PaymentBankTransfer("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", this.paymentData);
        paymentBankTransfer.setPaymentData(this.paymentData);
        assertEquals("SUCCESS", paymentBankTransfer.getStatus());
    }

    @Test
    void testSetPaymentDataWithEmptyPaymentData(){
        PaymentBankTransfer paymentBankTransfer = new PaymentBankTransfer("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", this.paymentData);
        paymentBankTransfer.setPaymentData(this.paymentData);
        assertEquals("REJECTED", paymentBankTransfer.getStatus());
    }

    @Test
    void testSetPaymentDataWithEmptyBankName(){
        this.paymentData.put("bankName", "");
        this.paymentData.put("referenceCode", "1234567890");
        PaymentBankTransfer paymentBankTransfer = new PaymentBankTransfer("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", this.paymentData);
        paymentBankTransfer.setPaymentData(this.paymentData);
        assertEquals("REJECTED", paymentBankTransfer.getStatus());
    }

    @Test
    void testSetPaymentDataWithEmptyReferenceCode(){
        this.paymentData.put("bankName", "Mandiri");
        this.paymentData.put("referenceCode", "");
        PaymentBankTransfer paymentBankTransfer = new PaymentBankTransfer("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", this.paymentData);
        paymentBankTransfer.setPaymentData(this.paymentData);
        assertEquals("REJECTED", paymentBankTransfer.getStatus());
    }
}
