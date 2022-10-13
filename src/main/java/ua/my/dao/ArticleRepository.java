package ua.my.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.my.model.ArticleModel;

import java.util.Date;
import java.util.UUID;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleModel, UUID> {

    @Query(value = "SELECT count(*) FROM articles WHERE articles.date_creation=(?1)",
            nativeQuery = true)
    Integer countAddedAndUpdatedArticlesPerDate(Date date);
}
