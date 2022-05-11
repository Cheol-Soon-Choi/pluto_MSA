package com.ccs.services;

import com.ccs.clients.ItemRestTemplateClient;
import com.ccs.clients.MemberRestTemplateClient;
import com.ccs.models.entity.Item;
import com.ccs.models.entity.Member;
import com.ccs.models.entity.Order;
import com.ccs.models.entity.OrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRestTemplateClient itemRestTemplateClient;
    private final MemberRestTemplateClient memberRestTemplateClient;

    @Transactional
    public Order getOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        Item item = this.getItem(order.getItemId());
        Member member = this.getMember(order.getMemberId());

        order.setMemberName(member.getName());
        order.setRole(member.getRole());
        order.setItemName(item.getItemName());
        order.setItemPrice(item.getPrice());

        return order;
    }

    @Transactional
    public Long saveOrder(Order order) {

        Item item = this.getItem(order.getItemId());

        order.setTotalPrice(item.getPrice() * order.getOrderCount());
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order).getId();
    }

    @HystrixCommand
    public Item getItem(Long itemId) {
        return itemRestTemplateClient.getItem(itemId);
    }

    @HystrixCommand
    public Member getMember(Long memberId) {
        return memberRestTemplateClient.getMember(memberId);
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
