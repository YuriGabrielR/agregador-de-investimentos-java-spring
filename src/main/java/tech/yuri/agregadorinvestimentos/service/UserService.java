package tech.yuri.agregadorinvestimentos.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.yuri.agregadorinvestimentos.dto.CreateAccountDto;
import tech.yuri.agregadorinvestimentos.dto.CreateUserDto;
import tech.yuri.agregadorinvestimentos.dto.GetAccountsResponseDto;
import tech.yuri.agregadorinvestimentos.dto.UpdateUserDto;
import tech.yuri.agregadorinvestimentos.entity.Account;
import tech.yuri.agregadorinvestimentos.entity.BillingAddress;
import tech.yuri.agregadorinvestimentos.entity.User;
import tech.yuri.agregadorinvestimentos.repository.AccountRepository;
import tech.yuri.agregadorinvestimentos.repository.BillingAddressRepository;
import tech.yuri.agregadorinvestimentos.repository.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
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

    public void createAccount(String userId, CreateAccountDto data) {
        var id = UUID.fromString(userId);
        var user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var account = new Account(null, data.description(), user, null, new ArrayList<>());

        var accountCreated = accountRepository.save(account);

        var billingAdress = new BillingAddress(null, data.street(), data.number(), account);

        billingAddressRepository.save(billingAdress);

    }

    public List<GetAccountsResponseDto> getAccounts(String userId) {
        var id = UUID.fromString(userId);
        var user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var accounts = user.getAccounts().stream().map(account ->
                new GetAccountsResponseDto(account.getAccountId().toString(), account.getDescription())
        ).toList();

        return accounts;
    }
}
