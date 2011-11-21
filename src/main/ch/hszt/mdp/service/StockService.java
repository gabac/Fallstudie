package ch.hszt.mdp.service;

import ch.hszt.mdp.domain.Stock;

public interface StockService {
	void save(Stock stock);
	void update(Stock stock);
	void delete(Stock stock);
	Stock findByStockCode(String stockCode);
}
