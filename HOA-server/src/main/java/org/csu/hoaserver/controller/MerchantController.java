package org.csu.hoaserver.controller;

import DO.Merchant;
import org.csu.hoaserver.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.CommonResponse;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/apply")
    public CommonResponse<String> applyForMerchant(@RequestBody Merchant merchant) {
        merchantService.save(merchant);
        return CommonResponse.createForSuccess();
    }

    @PostMapping("/{id}/open")
    public CommonResponse<String> openMerchant(@PathVariable Integer id) {
        merchantService.setStatus(id,1);
        return CommonResponse.createForSuccess();
    }

    @PostMapping("/{id}/close")
    public CommonResponse<String> closeMerchant(@PathVariable Integer id) {
        merchantService.setStatus(id,0);
        return CommonResponse.createForSuccess();
    }

    @GetMapping("/{id}/status")
    public CommonResponse<Integer> getStatus(@PathVariable Integer id) {
        return CommonResponse.createForSuccess(merchantService.getStatus(id));
    }
}
