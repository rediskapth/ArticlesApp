package ua.my.article;

import ua.my.user.UserDto;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

public class ArticleDto {
    private UUID id;
    private String title;
    private String content;
    private Date dateCreation;
    private UserDto user;

    public ArticleDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Size(min = 3, max = 100, message = "Title must be minimum 5 characters long and no longer then 100")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Size(min = 5, max = 10000, message = "Content must be minimum 5 characters long and no longer then 10000")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
