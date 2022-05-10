package com.ccs.services;

import com.ccs.models.entity.Order;
import com.ccs.models.entity.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long saveOrder(Order order) {
        return orderRepository.save(order).getId();
    }

    @Transactional
    public Long updateOrder(Order order) {
        return orderRepository.save(order).getId();
    }

    @Transactional
    public Long deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
        return orderId;
    }

    @Transactional
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }
}
