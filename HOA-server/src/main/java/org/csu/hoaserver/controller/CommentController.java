package org.csu.hoaserver.controller;

import DO.Comment;
import DTO.CommentSaveDTO;
import org.csu.hoaserver.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.CommonResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/save")
    public CommonResponse<String> save(@RequestBody CommentSaveDTO commentSaveDTO) {
        commentService.save(commentSaveDTO);
        return CommonResponse.createForSuccess();
    }

    @PostMapping("/list")
    public CommonResponse<List<Comment>> list(Integer id) {
        return CommonResponse.createForSuccess(new ArrayList<>());
    }
}
