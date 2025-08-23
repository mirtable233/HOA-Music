package org.csu.hoaserver.service.impl;

import DO.Merchant;
import DO.User;
import context.UserContext;
import jakarta.annotation.PostConstruct;
import org.csu.hoaserver.dao.MerchantDao;
import org.csu.hoaserver.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    MerchantDao merchantDao;

    private HashOperations<Object, Object, Object> hashOps;

    @PostConstruct
    public void init() {
        hashOps = redisTemplate.opsForHash();
    }
    private static final String MERCHANT_STATUS_KEY = "merchant:status";

    @Override
    public void setStatus(Integer merchantId, Integer status) {
        hashOps.put(MERCHANT_STATUS_KEY, String.valueOf(merchantId), String.valueOf(status));
    }

    @Override
    public Integer getStatus(Integer merchantId) {
        String status = (String) hashOps.get(MERCHANT_STATUS_KEY, String.valueOf(merchantId));
        return status == null?0:Integer.parseInt(status);
    }

    @Override
    public void save(Merchant merchant) {
        merchant.setCreateTime(LocalDateTime.now());
        merchant.setUpdateTime(LocalDateTime.now());
        merchant.setUserId(UserContext.getCurrentId());
        merchantDao.save(merchant);
    }
}
