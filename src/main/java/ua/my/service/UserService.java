package ua.my.service;

import ua.my.model.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public void saveOrUpdate(UserDto userDto);

    public void validateToCreateUser(UserDto userDto);

    public UserDto findUserByUsername(String username);

    public List<UserDto> findAllUsers();

    public UserDto findUserById(UUID id);

    public void delete(UserDto userDto);
}
