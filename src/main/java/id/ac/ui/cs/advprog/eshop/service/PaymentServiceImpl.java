package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(UUID.randomUUID().toString(), method, paymentData, order);

        // Save dilakukan pada penentuan status
        if (isValidPaymentData(payment)) {
            this.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        } else {
            this.setStatus(payment, PaymentStatus.REJECTED.getValue());
        }
        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException("Invalid payment status");
        }
        payment.setStatus(status);

        if (payment.getStatus().equals(PaymentStatus.SUCCESS.getValue())) {
            payment.getOrder().setStatus(OrderStatus.SUCCESS.getValue());
        } else if (payment.getStatus().equals(PaymentStatus.REJECTED.getValue())) {
            payment.getOrder().setStatus(OrderStatus.FAILED.getValue());
        }

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

    public boolean isValidPaymentData(Payment payment) {
        Map<String, String> paymentData = payment.getPaymentData();

        if (payment.getMethod().equals(PaymentMethod.VOUCHER.getValue())) {
            if (!paymentData.containsKey("voucherCode")) return false;

            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode == null || voucherCode.length() != 16 ||
                    !voucherCode.startsWith("ESHOP")) {
                return false;
            }
            int numericCount = 0;
            for (char c : voucherCode.toCharArray()) {
                if (Character.isDigit(c)) {
                    numericCount++;
                }
            }
            return numericCount == 8;

        } else {
            if (!paymentData.containsKey("address") ||
                    !paymentData.containsKey("deliveryFee")) return false;
            return !paymentData.get("address").isEmpty() &&
                    !paymentData.get("deliveryFee").isEmpty();
        }
    }
}