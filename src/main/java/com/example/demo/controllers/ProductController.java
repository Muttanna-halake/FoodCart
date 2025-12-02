package com.example.demo.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Product;
import com.example.demo.services.ProductServices;

@Controller
public class ProductController {

	@Autowired
	private ProductServices productServices;

	// ===== ADD PRODUCT (from Admin) =====
	@PostMapping("/addingProduct")
	public String addProduct(@RequestParam("pname") String pname,
			@RequestParam("pprice") double pprice,
			@RequestParam("pdescription") String pdescription,
			@RequestParam("imageFile") MultipartFile file) {

		String folder = "src/main/resources/static/Images/";
		String fileName = (file != null && !file.isEmpty()) ? file.getOriginalFilename() : null;

		try {
			if (fileName != null) {
				Path path = Paths.get(folder + fileName);
				Files.createDirectories(path.getParent());
				Files.write(path, file.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Product p = new Product();
		p.setPname(pname);
		p.setPprice(pprice);
		p.setPdescription(pdescription);
		p.setImage(fileName); // save only the file name (or null)

		productServices.addProduct(p);
		return "redirect:/admin/services";
	}

	// SHOW PRODUCT EDIT PAGE (namespaced to avoid collisions)
	// Note: Admin page still uses /updateProduct/{productId} and is handled by
	// AdminController.
	@GetMapping("/product/update/{productId}")
	public String showUpdateProduct(@PathVariable("productId") int id, Model model) {
		Product p = productServices.getProduct(id);
		model.addAttribute("product", p);
		return "Update_Product";
	}

	// UPDATE PRODUCT (namespaced)
	@PostMapping("/product/updating/{productId}")
	public String updateProduct(@PathVariable("productId") int id,
			@RequestParam("pname") String pname,
			@RequestParam("pprice") double pprice,
			@RequestParam("pdescription") String pdescription,
			@RequestParam(value = "imageFile", required = false) MultipartFile file) {

		Product product = productServices.getProduct(id);
		product.setPname(pname);
		product.setPprice(pprice);
		product.setPdescription(pdescription);

		try {
			if (file != null && !file.isEmpty()) {
				String folder = "src/main/resources/static/Images/";
				String fileName = file.getOriginalFilename();
				Path path = Paths.get(folder + fileName);
				Files.createDirectories(path.getParent());
				Files.write(path, file.getBytes());
				product.setImage(fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		productServices.addProduct(product);
		return "redirect:/admin/services";
	}

	// DELETE PRODUCT (keep original path used in admin page) =====
	@GetMapping("/deleteProduct/{productId}")
	public String delete(@PathVariable("productId") int id) {
		productServices.deleteProduct(id);
		return "redirect:/admin/services";
	}

}
