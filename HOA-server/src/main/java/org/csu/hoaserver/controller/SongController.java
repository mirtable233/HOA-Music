package org.csu.hoaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.CommonResponse;

@RestController
@RequestMapping("/song")
public class SongController {

    @PostMapping("/batchUpload")
    public CommonResponse<String> batchUploadSongs(){
        return CommonResponse.createForSuccess();
    }

    @PostMapping("/test")
    public String test() {
        return "test";
    }
}
