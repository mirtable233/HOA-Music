package org.csu.hoaserver.service.impl;

import DO.Comment;
import DTO.CommentSaveDTO;
import context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.csu.hoaserver.dao.CommentDao;
import org.csu.hoaserver.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
