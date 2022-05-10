package com.ccs.services;

import com.ccs.models.entity.ItemImg;
import com.ccs.models.entity.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemImgService {

    private final ItemImgRepository itemImgRepository;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) {
        String oriImgName = String.valueOf(UUID.randomUUID());
        String imgName = oriImgName+".jpg";
        String imgUrl = oriImgName+".com";

        //업로드 및 파일저장 생략

        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }
}
