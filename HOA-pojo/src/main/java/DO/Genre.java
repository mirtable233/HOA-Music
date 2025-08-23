package DO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre implements Serializable {
    private Integer id;
    private String name;
    private String chName;
    private Integer parentId;
    private String cover;
    private String description;
}
