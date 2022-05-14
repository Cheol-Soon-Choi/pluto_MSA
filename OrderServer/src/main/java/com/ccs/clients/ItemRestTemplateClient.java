package com.ccs.clients;

import com.ccs.models.entity.Item;
import com.ccs.repository.ItemRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ItemRestTemplateClient {

    //토큰 전파를 위해 기존 restTemplate -> Oauth2 호출 지원하는 Oauth2RestTemplate로 변경 -> JWT전파를 위해 RestTemplate 변경
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ItemRedisRepository itemRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(ItemRestTemplateClient.class);

    //캐시 확인
    private Item checkRedisCache(Long itemId) {
        try {
            return itemRedisRepository.findItem(itemId);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("##### Error encountered while trying to retrieve itemId: {} check Redis Cache.  Exception {}", itemId, ex);
            return null;
        }
    }

    //캐시 저장
    private void cacheItemObject(Item item) {
        try {
            itemRedisRepository.saveItem(item);
        } catch (Exception ex) {
            logger.error("##### Unable to cache itemId {} in Redis. Exception {}", item.getId(), ex);
        }
    }

    public Item getItem(Long itemId) {

        Item item = checkRedisCache(itemId);

        //캐시 o -> 캐시 전달
        if (item != null) {
            logger.debug("##### I have successfully retrieved an itemId {} from the redis cache: {}", itemId, item);
            return item;
        }

        //캐시 x -> 아이템 서버 호출
        logger.debug("##### Unable to locate item from the redis cache: {}.", itemId);
        ResponseEntity<Item> restExchange =
                restTemplate.exchange(
                        "http://zuulserver:5555/itemserver/items/{itemId}",
                        HttpMethod.GET,
                        null, Item.class, itemId);
        item = restExchange.getBody();

        //호출 후 캐시서버에 저장
        if (item != null) {
            cacheItemObject(item);
        }

        return item;
    }
}
