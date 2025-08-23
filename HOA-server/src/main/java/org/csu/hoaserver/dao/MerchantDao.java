package org.csu.hoaserver.dao;

import DO.Merchant;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchantDao {
    void save(Merchant merchant);
}
