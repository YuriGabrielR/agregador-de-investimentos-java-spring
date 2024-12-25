package tech.yuri.agregadorinvestimentos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.yuri.agregadorinvestimentos.entity.Account;
import tech.yuri.agregadorinvestimentos.entity.User;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
