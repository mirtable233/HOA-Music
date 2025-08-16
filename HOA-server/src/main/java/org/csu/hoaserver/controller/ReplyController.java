package org.csu.hoaserver.controller;

import DTO.ReplySaveDTO;
import org.springframework.web.bind.annotation.*;
import response.CommonResponse;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    @PostMapping("/save")
    public CommonResponse<String> save(@RequestBody ReplySaveDTO replySaveDTO){
        return CommonResponse.createForSuccess();
    }
}
