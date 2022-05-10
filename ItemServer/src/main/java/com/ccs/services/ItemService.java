package com.ccs.services;

import com.ccs.models.entity.Item;
import com.ccs.models.entity.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item getItemDtl(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long saveItem(Item item) {
        return itemRepository.save(item).getId();
    }

    @Transactional
    public Long updateItem(Item newitem) {
        return itemRepository.save(newitem).getId();
    }

    @Transactional
    public Long delItem(Long itemId){
        itemRepository.deleteById(itemId);
        return itemId;
    }

    @Transactional
    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

}
