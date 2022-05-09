package com.ccs.services;

import com.ccs.models.entity.Item;
import com.ccs.models.entity.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    public final ItemRepository itemRepository;

    @Transactional
    public Item getItemDtl(Long itemId) {

        return itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long saveItem(Item item, List<MultipartFile> itemImgFileList) {

        return itemRepository.save(item).getId();
    }

    @Transactional
    public Long updateItem(Item newitem, List<MultipartFile> itemImgFileList) {

        Item item = itemRepository.findById(newitem.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(newitem);


        return itemRepository.save(item).getId();
    }

}
