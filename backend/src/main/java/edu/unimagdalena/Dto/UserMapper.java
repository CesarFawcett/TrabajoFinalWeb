package edu.unimagdalena.Dto;

import org.springframework.stereotype.Component;

import edu.unimagdalena.entities.User;

@Component
public class UserMapper {
    public UserCreateDto toUserCreateDto(User user){
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setId(user.getId());
        userCreateDto.setFullname(user.getFullname());
        userCreateDto.setUsername(user.getUsername());
        userCreateDto.setPassword(user.getPassword());

        return userCreateDto;
    }
    public User toUserEntity(UserCreateDto userCreateDto) {
        User user = new User();
        user.setFullname(userCreateDto.getFullname());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        return user;
    }
    public UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullname(user.getFullname());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}

