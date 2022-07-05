package net.codejava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.entity.Product;
import net.codejava.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		
		List<Product> products = service.listAll();
		
		model.addAttribute("products", products);
		
		return "index";
	}
	
	@GetMapping("/new")
	public String shsowNewProductForm(Model model) {
		
		Product product = new Product();
		
		model.addAttribute("product", product);
		
		return "new-product";
	}
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product product) {
		
		service.save(product);
		
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView shsowEditProductForm(@PathVariable(name = "id") Long id) {
		
		ModelAndView mav = new ModelAndView("edit-product");
		
		Product product = service.get(id);
		
		mav.addObject("product", product);
		
		return mav;
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return "redirect:/";
	}
}
