package ch.hszt.mdp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ch.hszt.mdp.domain.Stock;
import ch.hszt.mdp.util.CustomHibernateDaoSupport;

@Repository("stockDAO")
public class StockDAOImpl extends CustomHibernateDaoSupport implements StockDAO{
 
	public void save(Stock stock){
		getHibernateTemplate().save(stock);
	}
 
	public void update(Stock stock){
		getHibernateTemplate().update(stock);
	}
 
	public void delete(Stock stock){
		getHibernateTemplate().delete(stock);
	}
 
	public Stock findByStockCode(String stockCode){
		List list = getHibernateTemplate().find(
                     "from Stock where stockCode=?",stockCode
                );
		return (Stock)list.get(0);
	}
 
}
