package org.csu.hoaserver.service;

import DO.Comment;
import DTO.CommentSaveDTO;

public interface CommentService {
    void save(CommentSaveDTO commentSaveDTO);
}
