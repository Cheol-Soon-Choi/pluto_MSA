package com.ccs.models.entity;

import com.ccs.models.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer stockNumber;
    private ItemSellStatus itemSellStatus;

}
