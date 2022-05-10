package com.ccs;

import com.ccs.models.entity.Item;
import com.ccs.models.entity.ItemImg;
import com.ccs.models.entity.ItemImgRepository;
import com.ccs.models.entity.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
public class ItemServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ItemImgRepository itemImgRepository, ItemRepository itemRepository) {
        return (args) -> {
            for (int i = 1; i <= 10; i++) {
                Item item = itemRepository.findById((long) i).orElseThrow(EntityNotFoundException::new);

                String oriImgName = String.valueOf(UUID.randomUUID());
                String imgName = oriImgName + ".jpg";
                String imgUrl = oriImgName + ".com";
                ItemImg itemImg = ItemImg.builder()
                        .oriImgName(oriImgName)
                        .imgName(imgName)
                        .imgUrl(imgUrl)
                        .repimgYn("Y")
                        .item(item)
                        .build();
                itemImgRepository.save(itemImg);
            }
        };
    }
}
