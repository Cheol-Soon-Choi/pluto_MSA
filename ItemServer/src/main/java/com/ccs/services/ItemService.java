package com.ccs.services;

import com.ccs.models.dto.ItemDto;
import com.ccs.models.dto.ItemImgDto;
import com.ccs.models.entity.Item;
import com.ccs.models.entity.ItemImg;
import com.ccs.models.entity.ItemImgRepository;
import com.ccs.models.entity.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;

    @Transactional
    public ItemDto getItemDtl(Long itemId) {

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        for (ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        ItemDto itemFormDto = ItemDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);

        return itemFormDto;
    }

    @Transactional
    public Long saveItem(Item item, List<MultipartFile> itemImgFileList) {

        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if (i == 0)
                itemImg.setRepimgYn("Y");
            else
                itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return itemRepository.save(item).getId();
    }

    @Transactional
    public Long updateItem(Item newitem, List<MultipartFile> itemImgFileList) {

        Item item = itemRepository.findById(newitem.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(newitem);

        return itemRepository.save(item).getId();
    }

    @Transactional
    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

}
