package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData; // to save payment sub-feature data.
    Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String[] validPaymentMethod = {"Voucher Code", "Cash on Delivery"};
        if (Arrays.stream(validPaymentMethod).noneMatch(item -> (item.equals(method)))) {
            throw new IllegalArgumentException();
        } else {
            this.method = method;
        }


        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        if (order == null) throw new IllegalArgumentException();
        else this.order = order;
        this.setStatus("SUCCESS");
    }

    public Payment(String id, String method, Map<String, String> paymentData, Order order, String status) {
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}