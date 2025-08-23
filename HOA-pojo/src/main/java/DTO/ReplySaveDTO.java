package DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveDTO implements Serializable {
    private Integer commentId;
    private String replyUserId;
    private String content;
    private Integer parentReplyId;
}
