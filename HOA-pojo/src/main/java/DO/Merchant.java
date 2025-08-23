package DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Merchant implements Serializable {
    private Integer id;
    private Integer userId;
    private String storeName;
    private String businessLicense;
    private Integer addressBookId;
    private String contactName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
