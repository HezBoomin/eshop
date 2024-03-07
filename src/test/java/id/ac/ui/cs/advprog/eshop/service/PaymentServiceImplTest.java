package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    @Mock
    OrderRepository orderRepository;
    List<Payment> payments;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order);

        payments = new ArrayList<>();

        Map<String, String> paymentData1 = Map.of("voucherCode", "ESHOP12345678ABC");
        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(), paymentData1);
        payments.add(payment1);

        Map<String, String> paymentData2 = Map.of("bankName", "BNI", "referenceCode", "1234567890");
        Payment payment2 = new Payment("7f9e15bb-4b15-42f4-aebc-c3af85fb078", PaymentMethod.BANK_TRANSFER.getValue(), paymentData2);
        payments.add(payment2);

    }

    @Test
    void testAddPayment() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, PaymentMethod.VOUCHER_CODE.getValue(), Map.of("voucherCode", "ESHOP12345678ABC"));
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddPaymentIfAlreadyExists() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertNull(paymentService.addPayment(order, PaymentMethod.VOUCHER_CODE.getValue(), Map.of("voucherCode", "ESHOP12345678ABC")));
        verify(paymentRepository, times(0)).save(payment);
    }

    @Test
    void testSetSuccessStatus() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).save(order);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        verify(paymentRepository, times(1)).save(payment);
        verify(orderRepository, times(1)).save(order);
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    void testSetRejectedStatus() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).save(order);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        verify(paymentRepository, times(1)).save(payment);
        verify(orderRepository, times(1)).save(order);
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }

    @Test
    void testSetInvalidStatus() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "MEOW"));
        verify(paymentRepository, times(0)).save(payment);
        verify(orderRepository, times(0)).save(order);
    }

    @Test
    void testGetPayment() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        verify(paymentRepository, times(1)).findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfNotFound() {
        doReturn(null).when(paymentRepository).findById("zczc");

        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();
        verify(paymentRepository, times(1)).findAll();
        assertEquals(payments, result);
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        doReturn(new ArrayList<>()).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();
        verify(paymentRepository, times(1)).findAll();
        assertEquals(new ArrayList<>(), result);
    }
}
