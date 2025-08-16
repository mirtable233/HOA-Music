package DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private Integer id;
    private Integer commentId;
    private Integer userId;
    private String replyUserId;
    private String content;
    private LocalDateTime createTime;
    private Integer parentReplyId;
}
