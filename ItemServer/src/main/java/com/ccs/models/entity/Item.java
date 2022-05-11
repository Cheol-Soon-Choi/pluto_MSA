package com.ccs.models.entity;

import com.ccs.models.constant.ItemSellStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Setter
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemName;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stockNumber;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    @Builder
    public Item(Long id, String itemName, Integer price, Integer stockNumber, ItemSellStatus itemSellStatus) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemSellStatus = itemSellStatus;
    }

}
