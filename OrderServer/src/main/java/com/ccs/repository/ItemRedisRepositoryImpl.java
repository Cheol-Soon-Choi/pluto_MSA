package com.ccs.repository;

import com.ccs.models.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class ItemRedisRepositoryImpl implements ItemRedisRepository {

    private static final String HASH_NAME = "item";
    private RedisTemplate<String, Item> redisTemplate;
    private HashOperations hashOperations;

    public ItemRedisRepositoryImpl() {
        super();
    }

    @Autowired
    private ItemRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveItem(Item item) {
        hashOperations.put(HASH_NAME, "item" + item.getId(), item);
    }

    @Override
    public void updateItem(Item item) {
        hashOperations.put(HASH_NAME, "item" + item.getId(), item);
    }

    @Override
    public void deleteItem(Long itemId) {
        hashOperations.delete(HASH_NAME, "item" + itemId);
    }

    @Override
    public Item findItem(Long itemId) {
        return (Item) hashOperations.get(HASH_NAME, "item" + itemId);
    }
}
