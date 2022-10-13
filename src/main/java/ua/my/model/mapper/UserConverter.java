package ua.my.model.mapper;

import org.springframework.stereotype.Service;
import ua.my.model.UserModel;
import ua.my.model.dto.UserDto;

@Service
public class UserConverter {

    public UserDto convert(UserModel dao) {
        UserDto dto = new UserDto();
        dto.setId(dao.getId());
        dto.setUsername(dao.getUsername());
        dto.setPassword(dao.getPassword());
        dto.setUserRole(dao.getUserRole());
        return dto;
    }

    public UserModel convert(UserDto dto) {
        UserModel dao = new UserModel();
        dao.setId(dto.getId());
        dao.setUsername(dto.getUsername());
        dao.setPassword(dto.getPassword());
        dao.setUserRole(dto.getUserRole());
        return dao;
    }
}
