package com.model2.mvc.web.product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
	
	
	////////////////////////////////////////////////////////
	private static final Logger logger = 
			LoggerFactory.getLogger(ProductController.class);
	
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping("/addProduct.do")
	public String addProduct( @ModelAttribute("product") Product product, @RequestParam("file") MultipartFile[] file, Model model ) throws Exception {
		
		System.out.println("/addProduct.do");
		
		////////////////////////////////////////////////////////////////
		String uploadFolder = "C:\\Users\\majja\\git\\06PJT\\06.Model2MVCShop(Presentation+BusinessLogic)\\src\\main\\webapp\\images\\uploadFiles";
		
		List<String> list = new ArrayList<String>();
		for(MultipartFile multipartFile : file) {
			logger.info("-----------");
			logger.info("파일명 : " + multipartFile.getOriginalFilename());
			logger.info("파일크기 : " + multipartFile.getSize());
			
			// uploadFolder\\gongu03.jpg으로 조립
			// 이렇게 업로드 하겠다라고 설계
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				//파일 실제 명을 list에 담음
				list.add(multipartFile.getOriginalFilename());
				//transferTo() : 물리적으로 파일 업로드가 됨
				multipartFile.transferTo(saveFile);
			}catch(Exception e) {
				logger.info(e.getMessage());
			}//end catch
		}//end for
		
		//list : 파일명들이 들어있음
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		for (int i = 0; i < list.size(); i++) {
			product.setFileName(list.get(i));
		}
		product.setFileName(list.get(0));
		
		//Business Logic
		productService.insertProduct(product);
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		//forward
		return "forward:/product/addProduct.jsp";
	}
		
		
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prodNo") String prodNo, Model model) throws Exception{
		
		System.out.println("getProduct");
		//Business Logic
		Product product = productService.findProduct(Integer.parseInt(prodNo));
		//Model 과 View 연결
		model.addAttribute("product", product);
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping("/listProduct.do")
	public String listProduct(@RequestParam("menu") String menu,  @ModelAttribute("search") Search search, Model model, HttpServletRequest request) throws Exception{
		System.out.println("/listProduct.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String, Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		if(menu.equals("manage"))
		return "forward:/product/listProduct.jsp";
		else
		return "forward:/product/listProductSearch.jsp";
	}
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct( @ModelAttribute("product") Product product, Model model , HttpSession session) throws Exception{
		System.out.println("/updateProduct.do");
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		
		productService.updateProduct(product);
		
		model.addAttribute("product",product);
		
		return "redirect:/getProduct.do?prodNo="+product.getProdNo();
	}
	
	
	@RequestMapping("/updateProductView.do")
	public String updateProductView( @RequestParam("prodNo") String prodNo, Model model) throws Exception{
		System.out.println("/updateProductView.do");
		
		Product product = productService.findProduct(Integer.parseInt(prodNo));
		//Model 과 View 연결
		model.addAttribute("product", product);
		
		return "forward:/product/updateProduct.jsp";
	}
	
	
	
	
	
	

}
