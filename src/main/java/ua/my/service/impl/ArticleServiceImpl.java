package ua.my.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.my.dao.ArticleRepository;
import ua.my.exception.ArticleNotFoundException;
import ua.my.model.dto.ArticleDto;
import ua.my.model.mapper.ArticleConverter;
import ua.my.service.ArticleService;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleConverter articleConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
    }

    public List<ArticleDto> findAllArticles() {
        return StreamSupport.stream(articleRepository.findAll().spliterator(), false)
                .map(articleConverter::convert)
                .collect(Collectors.toList());
    }

    public ArticleDto findArticleById(UUID id) {
        return articleConverter.convert(articleRepository.findById(id).orElseThrow(() ->
                new ArticleNotFoundException("Article with id %s not exists", id)));
    }

    public void saveOrUpdate(ArticleDto articleDto) {
        articleRepository.save(articleConverter.convert(articleDto));
    }

    public void delete(ArticleDto articleDto) {
        articleRepository.delete(articleConverter.convert(articleDto));
    }

    public Integer countAddedAndUpdatedArticlesPerDate(Date date) {
        return articleRepository.countAddedAndUpdatedArticlesPerDate(date);
    }
}
