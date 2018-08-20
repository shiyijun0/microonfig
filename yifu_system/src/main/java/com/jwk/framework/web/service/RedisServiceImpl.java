package com.jwk.framework.web.service;

import com.jwk.common.utils.ObjectByteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Boolean hxists(String mapName, String field) {
        return redisTemplate.opsForHash().hasKey(mapName, field);
    }

    @Override
    public Object hGet(String tableName, String hashKey) {
        return redisTemplate.opsForHash().get(tableName, hashKey);
    }

    @Override
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
    @Override
    public Long hIncrementLong(String hKey, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(hKey, hashKey, delta);
    }

    @Override
    public Double hIncrementDouble(String hKey, String hashKey, Double delta) {
        return redisTemplate.opsForHash().increment(hKey, hashKey, delta);
    }

    @Override
    public void hSet(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public void hSet(String key, Map map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public Set<Object> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    @Override
    public Long hLen(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    @Override
    public List<Object> hVals(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    @Override
    public Long hDel(String key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    @Override
    public void lPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }
    @Override
    public void lPushAll(String key, List list){
        redisTemplate.opsForList().leftPushAll(key,list);
    }

    @Override
    public String lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public List<String> lRange(String key, Long start, Long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public Long lRem(String key, long i, Object value) {
        return redisTemplate.opsForList().remove(key, i, value);
    }

    @Override
    public String lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public void lSet(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    @Override
    public void lTrim(String key, Long start, Long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    @Override
    public void rPush(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public void rPushAll(String key, String... value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    @Override
    public String rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public Long sAdd(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Long sCard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public Set<String> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public Boolean sIsMember(String key, Object o) {
        return redisTemplate.opsForSet().isMember(key, o);
    }

    @Override
    public Boolean sMove(String key, String value, String destKey){
        return redisTemplate.opsForSet().move(key, value, destKey);
    }

    @Override
    public String sPop(String key){
        return redisTemplate.opsForSet().pop(key);
    }

    @Override
    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Object getToObj(String key) {
        byte[] bytes = redisTemplate.execute((RedisCallback<byte[]>) connection -> {
            return connection.get(key.getBytes());
        });
        return ObjectByteUtil.toObject(bytes);
    }

    @Override
    public String getRange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    @Override
    public Long incrBy(String key, Long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Double incrBy(String key, Double delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.set(key.getBytes(), ObjectByteUtil.toByteArray(value));
            return 1L;
        });
    }

    @Override
    public String getSet(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public void setEx(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }
    @Override
    public long ttl(String key){
        return redisTemplate.getExpire(key);
    }

}