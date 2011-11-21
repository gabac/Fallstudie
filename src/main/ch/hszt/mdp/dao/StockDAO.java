package ch.hszt.mdp.dao;

import ch.hszt.mdp.domain.Stock;

public interface StockDAO {
	void save(Stock stock);

	void update(Stock stock);

	void delete(Stock stock);

	Stock findByStockCode(String stockCode);
}
