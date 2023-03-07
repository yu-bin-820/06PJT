package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao{
	
	///field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	UserService uservice=new UserServiceImpl();
	ProductService pservice= new ProductServiceImpl();
	
	///Constructor
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public Purchase findPurchase(int tranNo) throws Exception {
		
		return sqlSession.selectOne("PurchaseMapper.findPurchase", tranNo);
	}
	
	
	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception {
		
		//System.out.println("PurchaseDAO에서 받은 userId"+userId);
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("search", search);
		
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);

	}

	
	public HashMap<String, Object> getSaleList(Search search) throws Exception {
		
		return null;
	}
	
	public void insertPurchase(Purchase purchase) throws Exception {
		
		sqlSession.insert("PurchaseMapper.insertPurchase", purchase);
	}
	
	public void updatePurchase(Purchase purchase) throws Exception {
		
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	public void updateTranCode(Purchase purchase) throws Exception {
		
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(String userId) throws Exception {
		
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", userId);
	}

	
	
}

