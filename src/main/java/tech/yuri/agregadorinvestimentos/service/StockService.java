package tech.yuri.agregadorinvestimentos.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import tech.yuri.agregadorinvestimentos.dto.CreateStockDto;
import tech.yuri.agregadorinvestimentos.entity.Stock;
import tech.yuri.agregadorinvestimentos.repository.StockRepository;

@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto data){
        var stockEntity = new Stock(data.stockId(), data.description());
        stockRepository.save(stockEntity);
    }
}
