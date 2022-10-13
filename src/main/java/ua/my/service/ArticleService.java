package ua.my.service;

import ua.my.model.dto.ArticleDto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ArticleService {
    public List<ArticleDto> findAllArticles();

    public ArticleDto findArticleById(UUID id);

    public void saveOrUpdate(ArticleDto articleDto);

    public void delete(ArticleDto articleDto);

    public Integer countAddedAndUpdatedArticlesPerDate(Date date);
}
