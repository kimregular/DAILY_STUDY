package sample.cafekiosk.spring.api.controller.order.request;

import lombok.Builder;

import java.util.List;

public class OrderCreateRequest {

    private List<String> productNumbers;

    @Builder
    private OrderCreateRequest(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }
}
