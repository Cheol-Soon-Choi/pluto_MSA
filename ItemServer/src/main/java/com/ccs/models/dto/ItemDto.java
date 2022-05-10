package com.ccs.models.dto;

import com.ccs.models.constant.ItemSellStatus;
import com.ccs.models.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemName;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    @Min(value = 0, message = "최소 가격은 0원 이상입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    @Min(value = 0, message = "최소 재고는 0개 이상입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    //ItemFormDto -> Item 객체 전환
    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    //Item -> ItemFormDto 객체 전환
    public static ItemDto of(Item item) {
        return modelMapper.map(item, ItemDto.class);
    }
}
