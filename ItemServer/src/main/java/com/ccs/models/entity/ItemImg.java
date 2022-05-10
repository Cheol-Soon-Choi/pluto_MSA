package com.ccs.models.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item_img")
@Getter
@Setter
@NoArgsConstructor
public class ItemImg {

    @Id
    @Column(name = "item_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //이미지 파일명
    private String imgName;

    //원본 이미지 파일명
    private String oriImgName;

    //이미지 조회 경로
    private String imgUrl;

    //대표 이미지 여부
    private String repimgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }

    @Builder
    public ItemImg(String imgName, String oriImgName, String imgUrl, String repimgYn, Item item) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repimgYn = repimgYn;
        this.item = item;
    }

}
