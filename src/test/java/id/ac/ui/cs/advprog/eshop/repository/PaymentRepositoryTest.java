package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private Order order;
    private Map<String, String> paymentDataVoucher;
    private Map<String, String> paymentDataCod = new HashMap<>();
    private List<Payment> payments = new ArrayList<>();

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        paymentDataCod.put("address", "Jalan bagus 12");
        paymentDataCod.put("deliveryFee", "8000");
        this.paymentDataVoucher = new HashMap<>();
        this.paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6a63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        Payment payment1 = new Payment("f887b638-99fe-4fd3-9ed3-7f4398351cc4",
                PaymentMethod.COD.getValue(), paymentDataCod, order, PaymentStatus.REJECTED.getValue());
        Payment payment2 = new Payment("8314bc6e-4070-4ec6-8d02-510c97427138",
                PaymentMethod.VOUCHER.getValue(), paymentDataVoucher, order);
        Payment payment3 = new Payment("1db42020-3b3d-4dba-bcc4-8b2511b3bbe5",
                PaymentMethod.COD.getValue(), paymentDataCod, order);
        payments.add(payment1);
        payments.add(payment2);
        payments.add(payment3);
    }

    @Test
    void testSaveCreatePayment() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);

        Payment savedPayment = paymentRepository.findById(payment.getId());
        assertNotNull(savedPayment);
        assertEquals(payment.getId(), savedPayment.getId());
        assertEquals(payment.getMethod(), savedPayment.getMethod());
        assertEquals(payment.getPaymentData(), savedPayment.getPaymentData());
        assertEquals(payment.getOrder(), savedPayment.getOrder());
        assertEquals(payment.getStatus(), savedPayment.getStatus());
    }

    @Test
    void testSaveUpdatePayment() {
        Payment payment = payments.get(2);
        paymentRepository.save(payment);

        Payment newPayment = new Payment("1db42020-3b3d-4dba-bcc4-8b2511b3bbe5",
                PaymentMethod.COD.getValue(), paymentDataCod, order, PaymentStatus.REJECTED.getValue());
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payments.get(2).getId());
        assertNotNull(findResult);
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());

    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(2).getId());
        assertNotNull(findResult);
        assertEquals(payments.get(2).getId(), findResult.getId());
        assertEquals(payments.get(2).getMethod(), findResult.getMethod());
        assertEquals(payments.get(2).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(2).getOrder(), findResult.getOrder());
        assertEquals(payments.get(2).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("idk");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertTrue(allPayments.contains(payments.get(0)));
        assertTrue(allPayments.contains(payments.get(1)));
        assertTrue(allPayments.contains(payments.get(2)));
        assertEquals(payments.size(), allPayments.size());
    }

    @Test
    void testGetPaymentsEmpty() {
        List<Payment> allPayments = paymentRepository.getAllPayments();

        assertEquals(0, allPayments.size());
        assertTrue(allPayments.isEmpty());
    }
}