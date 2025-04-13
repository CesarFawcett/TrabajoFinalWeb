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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "API para gestión de usuarios")
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
    
    @Operation(summary = "Obtener usuario por ID", description = "Recupera un usuario específico por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "302", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto>findById(@PathVariable("id") Integer id){
        UserDto user= userService.findById(id)
         .map(t -> userMapper.toUserDto(t))
         .orElseThrow(UserNotFoundException::new);
     return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    @Operation(summary = "Obtener lista de usuarios", description = "Recupera todos los usuarios")
    @ApiResponses({
        @ApiResponse(responseCode = "302", description = "Usuarios encontrados"),
        @ApiResponse(responseCode = "404", description = "Usuarios no encontrado")
    })
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        List<User> users =userService.findAll();
        List<UserDto> userDto = users.stream()
                                             .map(t -> userMapper.toUserDto(t))
                                             .collect(Collectors.toList());
            return ResponseEntity.ok().body(userDto);
    }

    @Operation(summary = "Crear usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "409", description = "Nombre de usuario ya existe")
    })
    @PostMapping("/")
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

    @Operation(summary = "actualizar usuaio", description = "actualizar un usuaio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "usuario actualizado exitosamente")
    })
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
    
    @Operation(summary = "Eliminar usuario", 
           description = "Elimina un usuario por su ID")
    @ApiResponses({
    @ApiResponse(responseCode = "204", description = "usuario eliminada exitosamente"),
    @ApiResponse(responseCode = "404", description = "usuario no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

