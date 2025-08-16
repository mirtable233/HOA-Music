package DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveDTO {
    private Integer commentId;
    private String replyUserId;
    private String content;
    private Integer parentReplyId;
}
