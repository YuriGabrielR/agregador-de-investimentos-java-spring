package tech.yuri.agregadorinvestimentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.yuri.agregadorinvestimentos.dto.AssociateAccountStockDto;
import tech.yuri.agregadorinvestimentos.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity associateStock(
            @PathVariable("accountId") String accountId, @RequestBody AssociateAccountStockDto data){

        accountService.associateStock(accountId, data);

        return ResponseEntity.noContent().build();
    }
}
