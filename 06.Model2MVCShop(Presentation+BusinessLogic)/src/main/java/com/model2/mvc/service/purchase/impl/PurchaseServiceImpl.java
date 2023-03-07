package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
//import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService{
	
	///Field

	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	
	public PurchaseServiceImpl() {

	}

	public Purchase getPurchase(int tranNo) throws Exception {
		//System.out.println(purchaseDAO.findPurchase(prodNo).getPurchaseProd());
		
		return purchaseDao.findPurchase(tranNo);
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception{
		// TODO Auto-generated method stub
		purchaseDao.insertPurchase(purchase);
	}


	@Override
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {
		List<Purchase> list = purchaseDao.getPurchaseList(search, userId);
		int totalCount = purchaseDao.getTotalCount(userId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		return map;
	}

	@Override
	public Map<String, Object> getSaleList(Search searchvo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDao.updatePurchase(purchase);	
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		purchaseDao.updateTranCode(purchase);	
	}

}
