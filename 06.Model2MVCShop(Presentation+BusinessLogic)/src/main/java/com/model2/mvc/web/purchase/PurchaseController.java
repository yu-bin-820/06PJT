package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	//setter Method 구현 않음
	
	public PurchaseController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping("/addPurchase.do")
	public String addPurchase( @ModelAttribute("Product") Purchase Purchase, Model model ) throws Exception {
		
		System.out.println("/addPurchase.do");
		//Purchase.setManuDate(Purchase.getOrderDate().replaceAll("-", ""));
		
		//Business Logic
		purchaseService.addPurchase(Purchase);
		// Model 과 View 연결
		model.addAttribute("Purchase", Purchase);
		
		
		return "forward:/Purchase/addPurchase.jsp";
	}
	
	@RequestMapping("/getPurchase.do")
	public String getPurchase(@RequestParam("tranNo") String tranNo, Model model) throws Exception{
		
		System.out.println("getPurchase");
		//Business Logic
		Purchase Purchase = purchaseService.getPurchase(Integer.parseInt(tranNo));
		//Model 과 View 연결
		model.addAttribute("Purchase", Purchase);
		
		return "forward:/Purchase/getPurchase.jsp";
	}
	
	@RequestMapping("/listPurchase.do")
	public String listPurchase(@RequestParam("menu") String menu,  @ModelAttribute("search") Search search, Model model, HttpServletRequest request) throws Exception{
		System.out.println("/listPurchase.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		//Map<String, Object> map=PurchaseService.getPurchaseList(search);
		
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		System.out.println(resultPage);
		
		// Model 과 View 연결
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//		model.addAttribute("search", search);
		
		if(menu.equals("manage"))
		return "forward:/Purchase/listPurchase.jsp";
		else
		return "forward:/Purchase/listPurchaseSearch.jsp";
	}
	
//	@RequestMapping("/updatePurchase.do")
//	public String updatePurchase( @ModelAttribute("Purchase") Purchase Purchase, Model model , HttpSession session) throws Exception{
//		System.out.println("/updatePurchase.do");
//		Purchase.setManuDate(Purchase.getOrderDate().replaceAll("-", ""));
		
//		PurchaseService.updatePurchase(Purchase);
		
//		model.addAttribute("Purchase",Purchase);
		
//		return "redirect:/getPurchase.do?prodNo="+Purchase.getProdNo();
//	}
	
	
	@RequestMapping("/updatePurchaseView.do")
	public String updatePurchaseView( @RequestParam("prodNo") String prodNo, Model model) throws Exception{
		System.out.println("/updatePurchaseView.do");
		
//		Purchase Purchase = PurchaseService.getPurchase(Integer.parseInt(prodNo));
		//Model 과 View 연결
//		model.addAttribute("Purchase", Purchase);
		
		return "forward:/Purchase/updatePurchase.jsp";
	}
	
	
	
	
	
	

}
