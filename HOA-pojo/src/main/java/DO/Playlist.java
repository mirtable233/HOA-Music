package DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
    private Integer id;
    private String name;
    private String cover;
    private Integer type;
    private Integer genreId;
    private String description;
    private LocalDate releaseDate;
    private LocalDate uploadDate;
}
