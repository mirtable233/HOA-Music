package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonPageDTO {
    private Integer page =1;
    private Integer size =10;
    private Integer type;
    private Integer targetId;
}
