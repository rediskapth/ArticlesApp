package ua.my.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.my.model.dto.UserDto;
import ua.my.security.jwt.JwtUser;
import ua.my.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.findUserByUsername(username);

        if (userDto == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found.");
        }

        return new JwtUser(userDto);
    }
}
