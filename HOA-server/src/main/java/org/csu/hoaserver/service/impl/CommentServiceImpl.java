package org.csu.hoaserver.service.impl;

import DO.Comment;
import DTO.CommentSaveDTO;
import DTO.CommonPageDTO;
import Enumeration.CommentType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.csu.hoaserver.dao.CommentDao;
import org.csu.hoaserver.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.CommonResponse;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Override
    public void save(CommentSaveDTO commentSaveDTO) {
        log.info("save comment");
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentSaveDTO, comment);
        comment.setCreateTime(LocalDateTime.now());
        comment.setUserId(UserContext.getCurrentId());
        commentDao.save(comment);
    }

    @Override
    public PageInfo<Comment> list(CommonPageDTO commonPageDTO) {
        PageHelper.startPage(commonPageDTO.getPage(), commonPageDTO.getSize());
        List<Comment> list = commentDao.list(commonPageDTO.getType(),
                commonPageDTO.getTargetId());
        return new PageInfo<>(list);
    }
}
