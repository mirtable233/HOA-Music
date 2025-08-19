package org.csu.hoaserver.service.impl;

import DO.Reply;
import DTO.ReplySaveDTO;
import VO.ReplyVO;
import context.UserContext;
import org.csu.hoaserver.dao.ReplyDao;
import org.csu.hoaserver.dao.UserDao;
import org.csu.hoaserver.service.ReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    ReplyDao replyDao;

    @Override
    public void save(ReplySaveDTO replySaveDTO) {
        Reply reply = new Reply();
        BeanUtils.copyProperties(replySaveDTO, reply);
        reply.setCreateTime(LocalDateTime.now());
        reply.setUserId(UserContext.getCurrentId());
        replyDao.save(reply);
    }

    @Override
    public List<ReplyVO> list(Integer commentId) {
        List<Reply> replies = replyDao.list(commentId);
        List<ReplyVO> repliesVO = new ArrayList<>();
        for (Reply reply : replies) {
            ReplyVO replyVO = new ReplyVO();
            BeanUtils.copyProperties(reply, replyVO);
            repliesVO.add(replyVO);
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<repliesVO.size();i++){
            map.put(repliesVO.get(i).getId(), i);
        }
        for(ReplyVO replyVO : repliesVO){
            Integer pid = replyVO.getParentReplyId();
            if(pid != 0){
                Integer fatherIndex = map.get(pid);
                ReplyVO father = repliesVO.get(fatherIndex);
                if(father.getChildren()==null){
                    father.setChildren(new ArrayList<>());
                }
                father.getChildren().add(replyVO);
            }
        }
        return repliesVO.stream().filter(replyVO -> replyVO.getParentReplyId()==0)
                .toList();
    }
}
