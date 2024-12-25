package tech.yuri.agregadorinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.yuri.agregadorinvestimentos.entity.BillingAddress;

import java.util.UUID;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {
}
