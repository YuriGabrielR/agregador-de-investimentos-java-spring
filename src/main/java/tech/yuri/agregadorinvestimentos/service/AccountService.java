package tech.yuri.agregadorinvestimentos.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.yuri.agregadorinvestimentos.dto.AssociateAccountStockDto;
import tech.yuri.agregadorinvestimentos.dto.GetAssociateAccountResponseDto;
import tech.yuri.agregadorinvestimentos.entity.AccountStock;
import tech.yuri.agregadorinvestimentos.entity.AccountStockId;
import tech.yuri.agregadorinvestimentos.repository.AccountRepository;
import tech.yuri.agregadorinvestimentos.repository.AccountStockRepository;
import tech.yuri.agregadorinvestimentos.repository.StockRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
    }


    public void associateStock (String accountId, AssociateAccountStockDto data){
        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(data.stockId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));


        var id = new AccountStockId(account.getAccountId(), stock.getStockId());

        var entity = new AccountStock(id, account, stock, data.quantity());

        accountStockRepository.save(entity);

    }

    public List<GetAssociateAccountResponseDto> getAssociateStocks(String accountId) {
        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.getAccountsStocks()
                .stream()
                .map(as ->
                        new GetAssociateAccountResponseDto(as.getStock().getStockId(), as.getQuantity()))
                .toList();


    }
}
