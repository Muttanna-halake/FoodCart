package com.example.demo.controllers;

// import java.util.List;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Orders;
// import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.loginCredentials.AdminLogin;
import com.example.demo.loginCredentials.UserLogin;
import com.example.demo.services.AdminServices;
import com.example.demo.services.OrderServices;
import com.example.demo.services.ProductServices;
import com.example.demo.services.UserServices;

@Controller
public class AdminController {

	@Autowired
	private UserServices services;

	@Autowired
	private AdminServices adminServices;

	@Autowired
	private ProductServices productServices;

	@Autowired
	private OrderServices orderServices;

	// ADMIN LOGIN
	@PostMapping("/adminLogin")
	public String adminLogin(@ModelAttribute("adminLogin") AdminLogin login,
			Model model,
			HttpSession session) {

		Admin admin = adminServices.getAdminByEmail(login.getEmail());

		if (admin != null && admin.getAdminPassword().equals(login.getPassword())) {

			session.setAttribute("loggedAdmin", admin);

			return "redirect:/admin/services"; // go to dashboard
		}

		model.addAttribute("error", "Invalid email or password");
		return "AdminLogin"; // show admin login page again
	}

	// USER LOGIN
	@PostMapping("/userLogin")
	public String userLogin(@ModelAttribute("userLogin") UserLogin login,
			Model model,
			HttpSession session) {

		String email = login.getUserEmail();
		String password = login.getUserPassword();

		if (services.validateLoginCredentials(email, password)) {

			User loggedUser = services.getUserByEmail(email);
			session.setAttribute("loggedUser", loggedUser);

			return "redirect:/products";
		}

		model.addAttribute("error2", "Invalid email or password");
		return "Login";
	}

	// LOGOUT
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	// ADMIN SERVICES PAGE
	@GetMapping("/admin/services")
	public String adminServices(Model model, HttpSession session) {

		if (session.getAttribute("loggedAdmin") == null) {
			return "redirect:/admin-login"; // Not logged in → go to login page
		}

		model.addAttribute("users", services.getAllUser());
		model.addAttribute("admins", adminServices.getAll());
		model.addAttribute("products", productServices.getAllProducts());
		model.addAttribute("orders", orderServices.getOrders());

		return "Admin_Page";
	}

	// ADMIN CRUD
	@GetMapping("/addAdmin")
	public String addAdminPage() {
		return "Add_Admin";
	}

	@PostMapping("/addingAdmin")
	public String addAdmin(@ModelAttribute Admin admin) {
		adminServices.addAdmin(admin);
		return "redirect:/admin/services";
	}

	@GetMapping("/updateAdmin/{adminId}")
	public String showUpdateAdmin(@PathVariable int adminId, Model model) {
		model.addAttribute("admin", adminServices.getAdmin(adminId));
		return "Update_Admin";
	}

	@GetMapping("/updatingAdmin/{id}")
	public String updateAdmin(@ModelAttribute Admin admin, @PathVariable int id) {
		adminServices.update(admin, id);
		return "redirect:/admin/services";
	}

	@GetMapping("/deleteAdmin/{id}")
	public String deleteAdmin(@PathVariable int id) {
		adminServices.delete(id);
		return "redirect:/admin/services";
	}

	// PRODUCT CRUD
	@GetMapping("/addProduct")
	public String addProduct() {
		return "Add_Product";
	}

	@GetMapping("/updateProduct/{productId}")
	public String showUpdateProduct(@PathVariable int productId, Model model) {
		model.addAttribute("product", productServices.getProduct(productId));
		return "Update_Product";
	}

	// USER CRUD
	@GetMapping("/addUser")
	public String addUser() {
		return "Add_User";
	}

	@GetMapping("/updateUser/{userId}")
	public String showUpdateUser(@PathVariable int userId, Model model) {
		model.addAttribute("user", services.getUser(userId));
		return "Update_User";
	}

	// ADMIN ORDER STATUS UPDATE (FIXED)
	@PostMapping("/admin/updateStatus/{id}")
	public String updateOrderStatus(@PathVariable int id,
			@RequestParam("status") String status) {

		Orders order = orderServices.getOrderById(id);

		if (order == null) {
			return "redirect:/admin/services?error=notfound";
		}

		order.setStatus(status);
		orderServices.saveOrder(order);

		return "redirect:/admin/services?updated=true";
	}

	// NEW ADMIN LOGIN PAGE SEPARATE
	@GetMapping("/admin-login")
	public String adminLoginPage() {
		return "AdminLogin";
	}

}
