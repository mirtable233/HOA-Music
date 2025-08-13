package DO;

import Enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String name;
    private String password;
    private Role role;
    private String email;
    private String avatar;
    private String phone;
    private LocalDateTime creatTime;
    private LocalDateTime lastLoginTime;
    private LocalDateTime updateTime;
    private String oauthProvider;
    private String oauthUid;
}
