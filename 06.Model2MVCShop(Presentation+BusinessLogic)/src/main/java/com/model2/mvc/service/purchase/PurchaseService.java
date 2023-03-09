package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	
	public Map<String, Object> addPurchaseView() throws Exception;
	
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public void addPurchase(Purchase purchase) throws Exception;
	
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception ;
		
	public Map<String, Object> getSaleList(Search searchvo) throws Exception;
	
	public void updatePurchase(Purchase purchase) throws Exception;
	
	public void updateTranCodeByProd(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;
}
