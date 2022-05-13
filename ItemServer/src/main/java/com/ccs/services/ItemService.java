package com.ccs.services;

import com.ccs.events.source.SimpleSourceBean;
import com.ccs.models.constant.ItemSellStatus;
import com.ccs.models.entity.Item;
import com.ccs.models.entity.ItemRepository;
import com.ccs.utils.UserContextHolder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
    private final ItemRepository itemRepository;
    private final SimpleSourceBean simpleSourceBean;

    @Transactional
    @HystrixCommand(fallbackMethod = "FallbackItem",
            //쓰레드 풀 설정 - 벌크헤드
            threadPoolKey = "ItemThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            }
    )
    public Item getItemDtl(Long itemId) {

        logger.debug("$$$$$ ItemService.getItemDTL  Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

        return itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
    }

    private Item FallbackItem(Long itemId) {
        return Item.builder()
                .id(itemId)
                .itemName("Item_Fallback")
                .price(99999999)
                .stockNumber(10)
                .itemSellStatus(ItemSellStatus.SOLD_OUT)
                .build();
    }

    @Transactional
    public Long saveItem(Item item) {
        Long itemId = itemRepository.save(item).getId();
        simpleSourceBean.publishItemChange("SAVE", itemId);
        return itemId;
    }

    @Transactional
    public Long updateItem(Item newitem) {
        Long itemId = itemRepository.save(newitem).getId();
        simpleSourceBean.publishItemChange("UPDATE", newitem.getId());
        return itemId;
    }

    @Transactional
    public Long delItem(Long itemId) {
        itemRepository.deleteById(itemId);
        simpleSourceBean.publishItemChange("DELETE", itemId);
        return itemId;
    }

    @Transactional
    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

}
