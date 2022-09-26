package ua.my.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.my.user.UserDto;
import ua.my.user.UserDtoPropertyEditor;
import ua.my.user.UserService;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(path = "/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @ModelAttribute
    public ArticleDto getDefaultArticleDto() {
        return new ArticleDto();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(UserDto.class, new UserDtoPropertyEditor());
    }

    @GetMapping(path = "/findArticles")
    public String findAllArticles(Model model) {
        List<ArticleDto> articles = articleService.findAllArticles();
        model.addAttribute("articles", articles);
        return "findArticles";
    }

    @GetMapping(path = "/form/create")
    public String createArticleForm(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "createArticleForm";
    }

    @PostMapping
    public ModelAndView createArticle(@ModelAttribute("articleDto") @Valid ArticleDto articleDto, BindingResult bindingResult,
                                      ModelAndView model) {
        if (bindingResult.hasErrors()) {
            List<UserDto> users = userService.findAllUsers();
            model.addObject("users", users);
            model.setViewName("createArticleForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        articleDto.setDateCreation(new Date());
        articleService.saveOrUpdate(articleDto);
        model.setViewName("createArticle");
        model.setStatus(HttpStatus.CREATED);
        return model;
    }

    @GetMapping(path = "/form/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateArticleForm(@PathVariable("id") UUID id, Model model) {
        ArticleDto articleDto = articleService.findArticleById(id);
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("articleDto", articleDto);
        return "updateArticleForm";
    }

    @PostMapping(path = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateArticle(@PathVariable("id") UUID id,
                                      @ModelAttribute("articleDto") @Valid ArticleDto articleDto, BindingResult bindingResult,
                                      ModelAndView model) {
        if (bindingResult.hasErrors()) {
            List<UserDto> users = userService.findAllUsers();
            model.addObject("users", users);
            model.setViewName("updateArticleForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        articleDto.setDateCreation(new Date());
        articleService.saveOrUpdate(articleDto);
        model.setViewName("updateArticle");
        model.setStatus(HttpStatus.CREATED);
        return model;
    }

    @GetMapping(path = "/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteArticle(@RequestParam("id") UUID id, ModelAndView model) {
        ArticleDto articleDto = articleService.findArticleById(id);
        articleService.delete(articleDto);
        model.addObject("id", id);
        return "deleteArticle";
    }

    @GetMapping(path = "/statistic")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showStatistic(Model model) {
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
        model.addAttribute("result", result);
        return "statistic";
    }
}
