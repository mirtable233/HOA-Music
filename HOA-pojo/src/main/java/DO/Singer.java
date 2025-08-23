package DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Singer implements Serializable {
    private Integer id;
    private Integer userId;
    private String name;
    private String avatar;
    private Integer regionId;
    private String description;
}
