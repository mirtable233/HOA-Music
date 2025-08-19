package org.csu.hoaserver.service;

import DO.Comment;
import DTO.CommentSaveDTO;
import DTO.CommonPageDTO;
import Enumeration.CommentType;
import com.github.pagehelper.PageInfo;
import response.CommonResponse;

import java.util.List;

public interface CommentService {
    void save(CommentSaveDTO commentSaveDTO);

    PageInfo<Comment> list(CommonPageDTO commonPageDTO);
}
