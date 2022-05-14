package com.ccs.models.entity;

import com.ccs.models.constant.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private Long memberId;

    private Long itemId;

    private LocalDateTime orderDate;

    private Integer orderCount;

    @Transient
    private String memberName;

    @Transient
    @Enumerated(EnumType.STRING)
    private Role role;

    @Transient
    private String itemName;

    @Transient
    private Integer itemPrice;

}
