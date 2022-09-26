package ua.my.user;

import ua.my.article.ArticleDto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

public class UserDto {
    private UUID id;
    private String username;
    private String password;
    private UserRole userRole;
    private Set<ArticleDto> articles;

    public UserDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Size(min = 5, max = 50, message = "Username must be minimum 5 characters long and no longer then 50")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Username must contains only letters or/and digits")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Size(min = 8, max = 100, message = "Password must be minimum 8 characters long and no longer then 100")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Set<ArticleDto> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleDto> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return id +
                "," + username;
    }
}
