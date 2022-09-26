package ua.my.article;

import org.springframework.stereotype.Service;
import ua.my.user.UserConverter;

@Service
public class ArticleConverter {

    public ArticleDto convert(ArticleDao dao) {
        UserConverter userConverter = new UserConverter();
        ArticleDto dto = new ArticleDto();
        dto.setId(dao.getId());
        dto.setTitle(dao.getTitle());
        dto.setContent(dao.getContent());
        dto.setDateCreation(dao.getDateCreation());
        dto.setUser(userConverter.convert(dao.getUser()));
        return dto;
    }

    public ArticleDao convert(ArticleDto dto) {
        UserConverter userConverter = new UserConverter();
        ArticleDao dao = new ArticleDao();
        dao.setId(dto.getId());
        dao.setTitle(dto.getTitle());
        dao.setContent(dto.getContent());
        dao.setDateCreation(dto.getDateCreation());
        dao.setUser(userConverter.convert(dto.getUser()));
        return dao;
    }
}
