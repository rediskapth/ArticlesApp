package ua.my.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.my.error.UserAlreadyExistsException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public void saveOrUpdate(UserDto userDto) {
        validateToCreateUser(userDto);
        userRepository.save(userConverter.convert(userDto));
    }

    public void validateToCreateUser(UserDto userDto) {
        userRepository.findUserByUsername(userDto.getUsername())
                .ifPresent((user) -> {
                    throw new UserAlreadyExistsException("User with username " + user.getUsername() +
                            " already exists");
                });
    }

    public UserDto findUserByUsername(String username) {
        return userConverter.convert(userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with username %s not exists", username))));
    }

    public List<UserDto> findAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDto findUserById(UUID id) {
        return userConverter.convert(userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with id %s not exists", id))));
    }

    public void delete(UserDto userDto) {
        userRepository.delete(userConverter.convert(userDto));
    }

    public UserDto getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = null;
        if (userDetails instanceof UserDto) {
            userDto = (UserDto) userDetails;
        }
        return userDto;
    }
}
