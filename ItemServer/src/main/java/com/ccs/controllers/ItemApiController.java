package com.ccs.controllers;

import com.ccs.config.ServiceConfig;
import com.ccs.models.entity.Item;
import com.ccs.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;
    private final ServiceConfig serviceConfig;
    private static final Logger logger = LoggerFactory.getLogger(ItemApiController.class);

    //아이템 조회
    @GetMapping("/items/{itemId}")
    public Item getItemDtl(@PathVariable("itemId") Long itemId) {
        return itemService.getItemDtl(itemId);
    }

    //아이템 등록
    @PostMapping("/items")
    public Long newItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    //아이템 수정
    @PutMapping("/items/{itemId}")
    public Long editItem(@PathVariable("itemId") Long itemId, @RequestBody Item item) {
        return itemService.updateItem(item);
    }

    //아이템 삭제
    @DeleteMapping("/items/{itemId}")
    public Long delItem(@PathVariable("itemId") Long itemId) {
        return itemService.delItem(itemId);
    }

    //아이템 리스트
    @GetMapping("/items")
    public List<Item> items() {
        return itemService.getItemList();
    }

    @GetMapping("/")
    public String getConfigValue() {
        return serviceConfig.getExampleProperty();
    }
}
