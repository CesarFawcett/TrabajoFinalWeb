package edu.unimagdalena.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.unimagdalena.Dto.UserCreateDto;
import edu.unimagdalena.Dto.UserDto;
import edu.unimagdalena.Dto.UserMapper;
import edu.unimagdalena.entities.User;
import edu.unimagdalena.exceptions.DuplicateCodigoException;
import edu.unimagdalena.exceptions.UserNotFoundException;
import edu.unimagdalena.services.UserService;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostConstruct
    public void init1() {
        User newUser = new User();
        newUser.setFullname("Cesar Fawcett");
        newUser.setUsername("adm");
        newUser.setPassword("123adm");

        userService.create(newUser);
    }
    
    @PostConstruct
    public void init2() {
        User newUser = new User();
        newUser.setFullname("Juan Fer");
        newUser.setUsername("admin");
        newUser.setPassword("123");

        userService.create(newUser);
    }

    //1. obtiene un usuario por el id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto>findById(@PathVariable("id") Integer id){
        UserDto user= userService.findById(id)
         .map(t -> userMapper.toUserDto(t))
         .orElseThrow(UserNotFoundException::new);
     return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    //2. listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<User> users =userService.findAll();
        List<UserDto> userDto = users.stream()
                                             .map(t -> userMapper.toUserDto(t))
                                             .collect(Collectors.toList());
            return ResponseEntity.ok().body(userDto);
    }

    //3. crear usuario
    @PostMapping
    public ResponseEntity<UserCreateDto>create(@RequestBody UserCreateDto User){
        User newUser = userMapper.toUserEntity(User);
        User userCreate = null;
        try{
            userCreate = userService.create(newUser);
        }catch (Exception e){
            throw new DuplicateCodigoException();
        }
        UserCreateDto userCreateDto = userMapper.toUserCreateDto(userCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(userCreateDto.getId())
                       .toUri();
            return ResponseEntity.created(location).body(userCreateDto);
    }

    //4. actualizar usuario id
    @PutMapping("/{id}")
    public ResponseEntity<UserCreateDto> update(@PathVariable("id") Integer id, @RequestBody UserCreateDto user) {
    Optional<User> optionalUser = userService.findById(id);
    if (optionalUser.isEmpty()) {
        throw new UserNotFoundException("User not found with id: " );
    }
    User userToUpdate = userMapper.toUserEntity(user);
    Optional<User> updatedUser = userService.update(id, userToUpdate);
    if (updatedUser.isPresent()) {
        UserCreateDto updatedDto = userMapper.toUserCreateDto(updatedUser.get());
        return ResponseEntity.ok().body(updatedDto);
    } else {
        UserCreateDto createdDto = userMapper.toUserCreateDto(userToUpdate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdDto);
    }
    }
    
    //5. eliminar usuario id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

