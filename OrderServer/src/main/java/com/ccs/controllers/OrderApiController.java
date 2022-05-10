package com.ccs.controllers;

import com.ccs.config.ServiceConfig;
import com.ccs.models.entity.Order;
import com.ccs.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;
    private final ServiceConfig serviceConfig;
    private static final Logger logger = LoggerFactory.getLogger(OrderApiController.class);

    //주문 조회
    @GetMapping("/orders/{orderId}")
    public Order getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getOrder(orderId);
    }

    //주문 실시
    @PostMapping("/orders")
    public Long saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    //주문 수정
    @PutMapping("/orders/{orderId}")
    public Long updateOrder(@PathVariable("orderId") Long OrderId, @RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    //주문 삭제
    @DeleteMapping("/orders/{orderId}")
    public Long deleteOrder(@PathVariable("orderId") Long orderId) {
        return orderService.deleteOrder(orderId);
    }

    //주문 리스트
    @GetMapping("/orders")
    public List<Order> getOrderList() {
        return orderService.getOrderList();
    }


    @GetMapping("/")
    public String getConfigValue() {
        return serviceConfig.getExampleProperty();
    }

}
