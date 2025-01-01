package tech.yuri.agregadorinvestimentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.yuri.agregadorinvestimentos.dto.CreateStockDto;
import tech.yuri.agregadorinvestimentos.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity createStock(@RequestBody CreateStockDto data){
        stockService.createStock(data);
        return ResponseEntity.ok().build();
    }
}
