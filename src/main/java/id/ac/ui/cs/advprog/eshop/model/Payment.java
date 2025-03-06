package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    @Setter
    String status;
    Map<String, String> paymentData; // to save payment sub-feature data.

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {}

    public Payment(String id, String method, Map<String, String> paymentData, Order order, String status) {}

    public void setStatus(String status) {}
}