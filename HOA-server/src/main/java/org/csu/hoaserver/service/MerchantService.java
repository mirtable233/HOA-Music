package org.csu.hoaserver.service;

import DO.Merchant;

public interface MerchantService {
    void setStatus(Integer merchantId, Integer status);
    Integer getStatus(Integer merchantId);

    void save(Merchant merchant);
}
