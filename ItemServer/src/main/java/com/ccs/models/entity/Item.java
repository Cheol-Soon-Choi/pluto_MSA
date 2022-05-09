package com.ccs.models.entity;

import com.ccs.models.constant.ItemSellStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

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
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    @Builder
    public Item(String itemName, int price, int stockNumber, String itemDetail, ItemSellStatus itemSellStatus) {
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus = itemSellStatus;
    }

    public void updateItem(Item newitem){
        this.itemName = newitem.getItemName();
        this.price = newitem.getPrice();
        this.stockNumber = newitem.getStockNumber();
        this.itemDetail = newitem.getItemDetail();
        this.itemSellStatus = newitem.getItemSellStatus();
    }
}
