package ch.hszt.mdp.service;

import org.springframework.beans.factory.annotation.Autowired;

import ch.hszt.mdp.dao.StockDAO;
import ch.hszt.mdp.domain.Stock;

public class StockServiceImpl implements StockService {

	@Autowired
	StockDAO stockDao;

	public void setStockDao(StockDAO stockDao) {
		this.stockDao = stockDao;
	}

	public void save(Stock stock) {
		stockDao.save(stock);
	}

	public void update(Stock stock) {
		stockDao.update(stock);
	}

	public void delete(Stock stock) {
		stockDao.delete(stock);
	}

	public Stock findByStockCode(String stockCode) {
		return stockDao.findByStockCode(stockCode);
	}

}
