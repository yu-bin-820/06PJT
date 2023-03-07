package com.model2.mvc.service.product.test;

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
import com.model2.mvc.service.product.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)

//==>Meta-Data를 다양하게 Wiring하자..
//@ContextConfiguration(locations = {"classpath:config/context-*.xml})
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl") 
	ProductService productService;
		
	//@Test
	public void testInsertProduct() throws Exception {
		Product product = new Product();
		product.setProdName("prodName");
		product.setProdDetail("prodDetail");
		product.setManuDate("manuDate");
		product.setPrice(1234);
		product.setFileName("fileName");
		
		productService.insertProduct(product);
		
	}
	
	//@Test
	public void testFindProduct() throws Exception {
		Product product = new Product();
		
		product = productService.findProduct(10035);
		
		System.out.println(product);
		
	}
	
	//@Test
	public void testUpdateProduct() throws Exception {
		
		Product product = productService.findProduct(10035);
		
		product.setFileName("파일명");
		product.setPrice(20000);
		product.setProdName("상품명");
		
		productService.updateProduct(product);
		
	}
	
	//@Test
	public void testGetProductList() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = productService.getProductList(search);
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3,list.size());
		
		Integer totalCount = (Integer)map.get("totalCount");
		
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		totalCount = (Integer)map.get("totalCount");
		
	}
	
	//@Test
	public void testGetProductListByProductNo() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("10035");
		Map<String, Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCout");
		System.out.println(totalCount);
		
		search.setSearchCondition("0");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
		
	//@Test
	public void testGetProductListByProductName() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("fff");
		Map<String, Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		search.setSearchCondition("1");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());
		
		System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		
		System.out.println(totalCount);
	}
	
	//@Test
	public void testGetProductListByProductPrice() throws Exception{
			
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("2");
		search.setSearchKeyword("100");
		Map<String, Object> map = productService.getProductList(search);
			
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
			
		System.out.println(list);
			
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
			
		search.setSearchCondition("2");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);
			
		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());
			
		System.out.println(list);
			
		totalCount = (Integer)map.get("totalCount");
			
		System.out.println(totalCount);
	}

}
