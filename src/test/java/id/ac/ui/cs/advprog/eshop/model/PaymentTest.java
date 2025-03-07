package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Order order;
    private Map<String, String> paymentDataCod;
    private Map<String, String> paymentDataVoucher;

    @BeforeEach
    void setUp() {
        this.paymentDataCod = new HashMap<>();
        this.paymentDataCod.put("address", "Jalan bagus 12");
        this.paymentDataCod.put("deliveryFee", "8000");
        this.paymentDataVoucher = new HashMap<>();
        this.paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6a63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(1);
        products.add(product1);
        products.add(product2);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testCreateValidPayment() {
        Payment payment = new Payment("f887b638-99fe-4fd3-9ed3-7f4398351cc4",
                PaymentMethod.VOUCHER.getValue(), paymentDataVoucher, order);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
    }

    @Test
    void testCreateRejectedPayment() {
        Payment payment = new Payment("f887b638-99fe-4fd3-9ed3-7f4398351cc4",
                PaymentMethod.COD.getValue(), paymentDataCod, order, PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(PaymentMethod.COD.getValue(), payment.getMethod());
    }

    @Test
    void testInvalidPaymentMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("f887b638-99fe-4fd3-9ed3-7f4398351cc4",
                    "Method Free", paymentDataCod, order);
        });
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("f887b638-99fe-4fd3-9ed3-7f4398351cc4",
                    PaymentMethod.COD.getValue(), paymentDataCod, order, "INVALID STATUS");
        });
    }

    @Test
    void testSetStatusToValidStatus() {
        Payment payment = new Payment("f887b638-99fe-4fd3-9ed3-7f4398351cc4",
                PaymentMethod.COD.getValue(), this.paymentDataCod, order);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("f887b638-99fe-4fd3-9ed3-7f4398351cc4",
                PaymentMethod.COD.getValue(), this.paymentDataCod, order);
        assertThrows(IllegalArgumentException.class, () -> order.setStatus("AAAA"));
    }

    @Test
    void testCreatePaymentEmptyData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("f887b638-99fe-4fd3-9ed3-7f4398351cc4",
                    PaymentMethod.COD.getValue(), null, order);});
    }

    @Test
    void testCreatePaymentEmptyOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("f887b638-99fe-4fd3-9ed3-7f4398351cc4",
                    PaymentMethod.COD.getValue(), paymentDataCod, null);});
    }
}