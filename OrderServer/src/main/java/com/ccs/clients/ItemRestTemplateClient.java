package com.ccs.clients;

import com.ccs.models.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ItemRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    public Item getItem(Long itemId) {

        ResponseEntity<Item> restExchange =
                restTemplate.exchange(
                        "http://itemserver/items/{itemId}",
                        HttpMethod.GET,
                        null, Item.class, itemId);

        return restExchange.getBody();
    }
}
