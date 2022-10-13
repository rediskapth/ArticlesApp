package ua.my.model.mapper;

import org.springframework.stereotype.Service;
import ua.my.model.ArticleModel;
import ua.my.model.dto.ArticleDto;

@Service
public class ArticleConverter {

    public ArticleDto convert(ArticleModel dao) {
        UserConverter userConverter = new UserConverter();
        ArticleDto dto = new ArticleDto();
        dto.setId(dao.getId());
        dto.setTitle(dao.getTitle());
        dto.setContent(dao.getContent());
        dto.setDateCreation(dao.getDateCreation());
        dto.setUser(userConverter.convert(dao.getUser()));
        return dto;
    }

    public ArticleModel convert(ArticleDto dto) {
        UserConverter userConverter = new UserConverter();
        ArticleModel dao = new ArticleModel();
        dao.setId(dto.getId());
        dao.setTitle(dto.getTitle());
        dao.setContent(dto.getContent());
        dao.setDateCreation(dto.getDateCreation());
        dao.setUser(userConverter.convert(dto.getUser()));
        return dao;
    }
}
