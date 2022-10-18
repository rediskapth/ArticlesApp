package ua.my.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.my.model.UserRole;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDto {
    private UUID id;

    @Size(min = 5, max = 50, message = "Username must be minimum 5 characters long and no longer then 50")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Username must contains only letters or/and digits")
    private String username;

    @Size(min = 8, max = 100, message = "Password must be minimum 8 characters long and no longer then 100")
    private String password;

    private UserRole userRole;

    private Set<ArticleDto> articles;

}
