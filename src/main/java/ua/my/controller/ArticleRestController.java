package ua.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.my.model.dto.ArticleDto;
import ua.my.service.ArticleService;
import ua.my.service.UserService;

import java.util.*;

@RestController
@RequestMapping(path = "/articles")
public class ArticleRestController {
    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public ArticleRestController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ArticleDto>> findAllArticles() {
        List<ArticleDto> articles = articleService.findAllArticles();
        return articles != null && !articles.isEmpty()
                ? new ResponseEntity<>(articles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ArticleDto> findArticleById(@PathVariable("id") UUID id) {
        ArticleDto articleDto = articleService.findArticleById(id);
        return articleDto != null
                ? new ResponseEntity<>(articleDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticleDto articleDto) {
        articleDto.setDateCreation(new Date());
        articleService.saveOrUpdate(articleDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable("id") UUID id, @RequestBody ArticleDto articleDto) {
        articleDto.setId(id);
        articleDto.setDateCreation(new Date());
        articleService.saveOrUpdate(articleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") UUID id) {
        ArticleDto articleDto = articleService.findArticleById(id);
        articleService.delete(articleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/statistic")
    public ResponseEntity<Map<Date, Integer>> showStatistic() {
        Map<Date, Integer> result = new LinkedHashMap<>();
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        Date resultDate = calendar.getTime();
        result.put(resultDate, articleService.countAddedAndUpdatedArticlesPerDate(resultDate));
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.DATE, -1);
            resultDate = calendar.getTime();
            result.put(resultDate, articleService.countAddedAndUpdatedArticlesPerDate(resultDate));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
