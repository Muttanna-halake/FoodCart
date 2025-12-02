package com.example.demo.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// import com.example.demo.entities.User;
import com.example.demo.entities.Product;
import com.example.demo.loginCredentials.AdminLogin;
import com.example.demo.services.ProductServices;

// import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private ProductServices productServices;

	// HOME PAGE
	@GetMapping(value = { "/home", "/" })
	public String home(Model model) {
		model.addAttribute("products", productServices.getAllProducts());
		return "Home";
	}

	// PRODUCTS PAGE (ONLY ONE METHOD)
	@GetMapping("/products")
	public String showProducts(Model model,
			@RequestParam(value = "keyword", required = false) String keyword) {

		// If search bar empty → show ALL products
		if (keyword == null || keyword.trim().isEmpty()) {
			model.addAttribute("products", productServices.getAllProducts());
			model.addAttribute("searchText", "");
			return "Products";
		}

		// Filter products if search is NOT empty
		List<Product> filtered = productServices.getAllProducts()
				.stream()
				.filter(p -> p.getPname().toLowerCase().contains(keyword.toLowerCase()))
				.toList();

		model.addAttribute("products", filtered);
		model.addAttribute("searchText", keyword);

		return "Products";
	}

	// SEARCH (redirect to GET /products so layout stays correct)
	@PostMapping("/products/search")
	public String searchProducts(@RequestParam("keyword") String keyword) {

		return "redirect:/products?keyword=" + keyword;
	}

	// SINGLE PRODUCT PAGE
	@GetMapping("/product/{id}")
	public String singleProduct(@PathVariable int id, Model model) {

		Product p = productServices.getProduct(id);
		model.addAttribute("product", p);

		List<Product> related = productServices.getAllProducts();
		related.removeIf(prod -> prod.getPid() == id);
		Collections.shuffle(related);

		if (related.size() > 4)
			related = related.subList(0, 4);

		model.addAttribute("related", related);
		return "Single_Product";
	}

	@GetMapping("/location")
	public String location() {
		return "Locate_us";
	}

	@GetMapping("/about")
	public String about() {
		return "About";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("adminLogin", new AdminLogin());
		return "Login";
	}

	@PostMapping("/checkout")
	public String checkoutPage(
			@RequestParam("name") String name,
			@RequestParam("price") float price,
			@RequestParam("image") String image,
			@RequestParam("qty") int qty,
			Model model) {

		float total = price * qty;

		model.addAttribute("pName", name);
		model.addAttribute("pPrice", price);
		model.addAttribute("pQty", qty);
		model.addAttribute("pImage", image);
		model.addAttribute("total", total);

		return "Checkout";
	}

}
