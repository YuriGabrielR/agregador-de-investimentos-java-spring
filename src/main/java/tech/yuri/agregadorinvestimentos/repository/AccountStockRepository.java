package tech.yuri.agregadorinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.yuri.agregadorinvestimentos.entity.AccountStock;
import tech.yuri.agregadorinvestimentos.entity.AccountStockId;

import java.util.UUID;

public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
