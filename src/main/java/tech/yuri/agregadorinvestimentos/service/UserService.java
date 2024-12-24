package tech.yuri.agregadorinvestimentos.service;

import org.springframework.stereotype.Service;
import tech.yuri.agregadorinvestimentos.dto.CreateUserDto;
import tech.yuri.agregadorinvestimentos.dto.UpdateUserDto;
import tech.yuri.agregadorinvestimentos.entity.User;
import tech.yuri.agregadorinvestimentos.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UUID createUser(CreateUserDto data){
        var dataToEntity = new User(
                null,
                data.username(),
                data.email(),
                data.password(),
                Instant.now(),
                null
        );

        var userSaved = userRepository.save(dataToEntity);

        return userSaved.getId();
    }

    public Optional<User> getUserById(String id){
        var userId = userRepository.findById(UUID.fromString(id));
        return  userId;
    }

    public List<User> getAllUsers(){
        var users = userRepository.findAll();

        return users;
    }

    public void deleteById(String userId){
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);

        if(userExists){
            userRepository.deleteById(id);
        }
    }

    public void updateUser(String userId, UpdateUserDto data){
        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);

        if(userEntity.isPresent()){
            var user = userEntity.get();

            if(data.username() != null){
                user.setUsername(data.username());
            }

            if(data.password() != null){
                user.setPassword(data.password());
            }

            userRepository.save(user);
        }

    }
}
