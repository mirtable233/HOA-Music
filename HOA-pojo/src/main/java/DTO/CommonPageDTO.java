package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonPageDTO implements Serializable {
    private Integer page =1;
    private Integer size =10;
    private Integer type;
    private Integer targetId;
}
