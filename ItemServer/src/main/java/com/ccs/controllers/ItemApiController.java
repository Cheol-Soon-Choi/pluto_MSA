package com.ccs.controllers;

import com.ccs.config.ServiceConfig;
import com.ccs.models.dto.ItemDto;
import com.ccs.models.entity.Item;
import com.ccs.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;
    private final ServiceConfig serviceConfig;
    private static final Logger logger = LoggerFactory.getLogger(ItemApiController.class);

    //관리자 상품 세부정보
    @GetMapping("/admin/items/{itemId}")
    public ItemDto getItemDtl(@PathVariable("itemId") Long itemId) {

        return itemService.getItemDtl(itemId);
    }

    //관리자 상품 등록
    @PostMapping("/admin/items")
    public Long newItem(@RequestBody Item item, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {
        return itemService.saveItem(item, itemImgFileList);
    }

    //관리자 상품 수정
    @PostMapping("/admin/items/{itemId}")
    public Long editItem(@RequestBody Item item, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {
        return itemService.updateItem(item, itemImgFileList);
    }

    //아이템 리스트
    @GetMapping("/itemlist")
    public List<Item> items() {
        return itemService.getItemList();
    }

    @GetMapping("/")
    public String getConfigValue() {
        return serviceConfig.getExampleProperty();
    }
}
