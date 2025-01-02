package tech.yuri.agregadorinvestimentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.yuri.agregadorinvestimentos.dto.AssociateAccountStockDto;
import tech.yuri.agregadorinvestimentos.dto.GetAssociateAccountResponseDto;
import tech.yuri.agregadorinvestimentos.service.AccountService;

import java.util.List;

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

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<GetAssociateAccountResponseDto>> getAssociateStocks(@PathVariable("accountId") String accountId){
        var stocks = accountService.getAssociateStocks(accountId);
        return ResponseEntity.ok(stocks);
    }
}
