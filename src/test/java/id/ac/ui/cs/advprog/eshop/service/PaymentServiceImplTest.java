package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;
    Order order;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        Map<String, String> paymentDataCod = new HashMap<>();
        paymentDataCod.put("address", "Jalan bagus 12");
        paymentDataCod.put("deliveryFee", "8000");
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6a63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("8314bc6e-4070-4ec6-8d02-510c97427138",
                PaymentMethod.VOUCHER.getValue(), paymentDataVoucher, order, PaymentStatus.SUCCESS.getValue());
        Payment payment2 = new Payment("f887b638-99fe-4fd3-9ed3-7f4398351cc4",
                PaymentMethod.COD.getValue(), paymentDataCod, order, PaymentStatus.REJECTED.getValue());
        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testCreatePayment() {
        Payment paymenttest = payments.getFirst();
        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        doReturn(paymenttest).when(paymentRepository).save(paymentCaptor.capture());

        Payment result = paymentService.addPayment(paymenttest.getOrder(), paymenttest.getMethod(), paymenttest.getPaymentData());
        verify(paymentRepository, times(1)).save(paymentCaptor.getValue());
        assertEquals(paymenttest.getMethod(), paymentCaptor.getValue().getMethod());
        assertEquals(paymenttest.getPaymentData(), paymentCaptor.getValue().getPaymentData());
        assertEquals(paymenttest.getOrder(), paymentCaptor.getValue().getOrder());
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getOrder().getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), result.getOrder().getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusInvalid() {
        Payment payment = payments.getFirst();

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.setStatus(payment, "invalid status"));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPayment() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentNotFound() {
        doReturn(null).when(paymentRepository).findById("w132132131sdfsdf");

        Payment result = paymentService.getPayment("w132132131sdfsdf");
        assertNull(result);
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayments();

        List<Payment> result = paymentService.getAllPayments();
        assertEquals(payments.size(), result.size());
        assertTrue(result.containsAll(payments));
    }
}