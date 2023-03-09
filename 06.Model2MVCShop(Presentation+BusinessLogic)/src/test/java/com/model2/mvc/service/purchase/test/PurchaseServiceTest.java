package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:config/commonservice.xml"})
@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl") 
	PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	
		
//	@Test
	public void testAddPurchase() throws Exception {
		User user = new User();
		user.setUserId("user15");
		
		
		Product product = new Product();
		product.setProdNo(10004);
		
		Purchase purchase = new Purchase();
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("receiverName");
		purchase.setReceiverPhone("receicerPhone");
		purchase.setDivyAddr("receiverAddr");
		purchase.setDivyRequest("receiverRequest");
		purchase.setTranCode("1");
		purchase.setDivyDate("20220314");
		
		purchaseService.addPurchase(purchase);
		
	}
	
//	@Test
	public void testGetPurchase() throws Exception {
		int tranNo = 10000;
		
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		System.out.println(purchase);
		Assert.assertEquals(10004, purchase.getPurchaseProd().getProdNo());
	}
	
//	@Test
	public void testUpdatePurchase() throws Exception {
		
		Purchase purchase = purchaseService.getPurchase(10000);
		
		System.out.println(purchase);
		purchase.setPaymentOption("2");
		purchase.setReceiverName("이름");
		purchase.setReceiverPhone("receiverPhone");
		purchase.setDivyAddr("receiverAddr");
		purchase.setDivyRequest("receiverRequest");
		purchase.setDivyDate("20220314");
		System.out.println(purchase);
		purchaseService.updatePurchase(purchase);
		
	}
	
//	@Test
	public void testUpdateTranCode() throws Exception {
		
		Product product = new Product();
		product.setProdNo(10004);
		
		System.out.println(product);
		Purchase purchase = new Purchase();
		
		purchase.setTranCode("2");
		purchase.setPurchaseProd(product);
		
		purchaseService.updateTranCodeByProd(purchase);
		
	}
	
//	@Test
	public void testGetPurchaseListAll() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		String userId = "user15";
		
		Map<String, Object> map = purchaseService.getPurchaseList(search, userId);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1,list.size());
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
		
	}
	
	

}
