package com.ccs.repository;

import com.ccs.models.entity.Item;

public interface ItemRedisRepository {
    void saveItem(Item item);

    void updateItem(Item item);

    void deleteItem(Long itemId);

    Item findItem(Long itemId);
}
