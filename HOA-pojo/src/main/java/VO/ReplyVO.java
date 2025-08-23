package VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVO implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer replyUserId;
    private String content;
    private LocalDateTime createTime;
    private Integer parentReplyId;

    private List<ReplyVO> children;

}
