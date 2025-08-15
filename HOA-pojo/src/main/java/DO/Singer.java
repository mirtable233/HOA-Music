package DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Singer {
    private Integer id;
    private Integer userId;
    private String name;
    private String avatar;
    private Integer regionId;
    private String description;
}
