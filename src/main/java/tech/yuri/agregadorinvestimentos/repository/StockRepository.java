package tech.yuri.agregadorinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.yuri.agregadorinvestimentos.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {
}
