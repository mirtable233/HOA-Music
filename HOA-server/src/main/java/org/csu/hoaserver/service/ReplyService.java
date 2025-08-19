package org.csu.hoaserver.service;

import DTO.ReplySaveDTO;
import VO.ReplyVO;

import java.util.List;

public interface ReplyService {
    void save(ReplySaveDTO replySaveDTO);
    List<ReplyVO> list(Integer commentId);
}
