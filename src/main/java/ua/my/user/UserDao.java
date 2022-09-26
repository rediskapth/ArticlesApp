package ua.my.user;

import ua.my.article.ArticleDao;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserDao {
    private UUID id;
    private String username;
    private String password;
    private UserRole userRole;
    private Set<ArticleDao> articles;

    public UserDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public Set<ArticleDao> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleDao> articles) {
        this.articles = articles;
    }
}
