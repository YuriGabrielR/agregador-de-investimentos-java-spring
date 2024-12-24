package tech.yuri.agregadorinvestimentos.service;

import org.springframework.stereotype.Service;
import tech.yuri.agregadorinvestimentos.dto.CreateUserDto;
import tech.yuri.agregadorinvestimentos.entity.User;
import tech.yuri.agregadorinvestimentos.repository.UserRepository;

import java.time.Instant;
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
}