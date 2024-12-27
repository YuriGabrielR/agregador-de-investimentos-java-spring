package tech.yuri.agregadorinvestimentos.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.yuri.agregadorinvestimentos.dto.CreateAccountDto;
import tech.yuri.agregadorinvestimentos.dto.CreateUserDto;
import tech.yuri.agregadorinvestimentos.dto.UpdateUserDto;
import tech.yuri.agregadorinvestimentos.entity.User;
import tech.yuri.agregadorinvestimentos.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserDto data){
        var userId = userService.createUser(data);

        return ResponseEntity.created(URI.create("/users/"+ userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        var user = userService.getUserById(userId);

        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        var users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") String userId){
        userService.deleteById(userId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserDto data){
       userService.updateUser(userId, data);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity createAccount(@PathVariable("userId") String userId, @RequestBody CreateAccountDto data){
          userService.createAccount(userId, data);
        return ResponseEntity.ok().build();
    }

}
