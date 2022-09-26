package ua.my.user;

import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public UserDto convert(UserDao dao) {
        UserDto dto = new UserDto();
        dto.setId(dao.getId());
        dto.setUsername(dao.getUsername());
        dto.setPassword(dao.getPassword());
        dto.setUserRole(dao.getUserRole());
        return dto;
    }

    public UserDao convert(UserDto dto) {
        UserDao dao = new UserDao();
        dao.setId(dto.getId());
        dao.setUsername(dto.getUsername());
        dao.setPassword(dto.getPassword());
        dao.setUserRole(dto.getUserRole());
        return dao;
    }
}
