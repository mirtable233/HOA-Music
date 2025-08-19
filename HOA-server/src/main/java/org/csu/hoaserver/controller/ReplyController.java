package org.csu.hoaserver.controller;

import DO.Reply;
import DTO.ReplySaveDTO;
import VO.ReplyVO;
import com.github.pagehelper.PageInfo;
import org.csu.hoaserver.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.CommonResponse;

import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @PostMapping("/save")
    public CommonResponse<String> save(@RequestBody ReplySaveDTO replySaveDTO){
        replyService.save(replySaveDTO);
        return CommonResponse.createForSuccess();
    }

    @PostMapping("/list")
    public CommonResponse<List<ReplyVO>> list(Integer commentId){
        return CommonResponse.createForSuccess(replyService.list(commentId));
    }
}
