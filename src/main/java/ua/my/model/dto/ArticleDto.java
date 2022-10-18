package ua.my.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ArticleDto {
    private UUID id;

    @Size(min = 3, max = 100, message = "Title must be minimum 5 characters long and no longer then 100")
    private String title;

    @Size(min = 5, max = 10000, message = "Content must be minimum 5 characters long and no longer then 10000")
    private String content;

    private Date dateCreation;

    private UserDto user;
}
