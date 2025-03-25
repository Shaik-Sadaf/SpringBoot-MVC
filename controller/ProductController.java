package com.spring.java.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.java.entity.ProductEntity;
import com.spring.java.model.ProductModel;
import com.spring.java.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/productform")
	public String getMethodName(Model model) 
	{
//		ProductEntity productEntity=new ProductEntity();
//
//		model.addAttribute("productEntity", productEntity);
		ProductModel productModel=new ProductModel();
		model.addAttribute("productModel", productModel);
	    return "add-product";
	}
	@PostMapping("/saveproduct")
	public String saveProductData(@Valid @ModelAttribute ProductModel productModel,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			return"add-product";

		}
		productService.saveProductData(productModel);
		return "success";
	}
	
	@GetMapping("/getAllProducts")
	public String getAllProducts(Model model ) 
	{
		List<ProductEntity> products=productService.getAllProducts();
		model.addAttribute("products", products);
		return "product-list";
	}
//	@GetMapping("getproduct/{id}")
//	public String getProductById(@PathVariable Long id,Model model) 
//	{
//		
//		ProductEntity product=productService.getProductById(id).orElse(null);
//	model.addAttribute("product", product);
//		return "product-list1";
//	}
	@GetMapping("getproduct/{id}")
	public String getProductById(@PathVariable Long id,Model model)
	{
		Optional<ProductEntity>optionalProduct=productService.getProductById(id);
		if(optionalProduct.isPresent())
		{
			ProductEntity productEntity=optionalProduct.get();
			model.addAttribute("product", productEntity);
		}
		else
		{
			model.addAttribute("errorMessage", "Product with id"+id+"not found");

		}
		return "product-list1";
	}
	
	
	
	@GetMapping("/deleteproduct/{id}")
	public String deleteProductById(@PathVariable Long id) 
	{
		productService.deleteById(id);
		return "redirect:/getAllProducts";
	}
//	@GetMapping("/editproduct/{id}")
//	public String editProductById(@PathVariable Long id )
//	{
//		productService.editById(id);
//		return new String();
//	}
//	@PostMapping("/editproduct")
//	public String editProductData(@ModelAttribute ProductModel productModel)
//	{
//		productService.saveProductData(productModel);
//		return "success";
//	}
	
	
	
//	@GetMapping("/editproduct/{id}")
//	public String editProductById(@PathVariable Long id, Model model)
//	{
//	    ProductEntity product = productService.getProductById(id).orElse(null);
//	    if (product == null) {
//	        return "redirect:/getAllProducts"; // Redirect if product not found
//	    }
//	    model.addAttribute("product", product);
//	    return "editform"; // Ensure this HTML file exists
//	}
//	@PostMapping("/editproduct")
//	public String editProductData(@ModelAttribute ProductModel productModel)
//	{
//	    productService.updateProduct(productModel);
//	    return "redirect:/getAllProducts"; // Redirect to updated list
//	}
	
//	@GetMapping("/editproduct/{id}")
//	public String getEditProduct(@PathVariable("id") Long id, Model model)
//	{
//		ProductModel productModel=productService.getEditProduct(id);
//		model.addAttribute("productModel", productModel);
//		model.addAttribute("id", id);
//
//		return "editform";
//	}
//	@PostMapping("/editproduct")
//	public String editProductData(@ModelAttribute ProductModel productModel)
//	{
//	    productService.updateProduct(productModel);
//	    return "redirect:/getAllProducts"; // Redirect to updated list
//	}
//	
	

	
//    @GetMapping("/editproduct/{id}")
//    public String getEditProduct(@PathVariable("id") Long id, Model model) {
//        Optional<ProductEntity> optionalProductEntity = productService.getProductById(id);
//
//        if (optionalProductEntity.isPresent()) {
//            ProductEntity productEntity = optionalProductEntity.get();
//            ProductModel productModel = new ProductModel();
//            productModel.setId(productEntity.getId());
//            productModel.setName(productEntity.getName());
//            productModel.setPrice(productEntity.getPrice());
//            productModel.setQuantity(productEntity.getQuantity());
//            productModel.setMadeIn(productEntity.getMadeIn());
//            productModel.setBrand(productEntity.getBrand());
//            model.addAttribute("productModel", productModel);
//        } else {
//            model.addAttribute("errorMessage", "Product with id " + id + " not found.");
//            return "redirect:/getAllProducts";  // Redirect to product list if product is not found
//        }
//        return "editform";  // Show the edit form
//    }
//
//    // Update product after editing
//    @PostMapping("/editproduct")
//    public String editProductData(@ModelAttribute("productModel") @Valid ProductModel productModel,
//                                  BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "editform";  // If there are validation errors, return to the form
//        }
//
//        // Call service to update the product in the database
//        productService.updateProduct(productModel);
//
//        return "redirect:/getAllProducts";  // After update, redirect to product list
//    }
//}

	
	
	// The GET method for fetching the product to edit
	@GetMapping("/editproduct/{id}")
	public String getEditProduct(@PathVariable("id") Long id, Model model) {
	    Optional<ProductEntity> productEntity = productService.getProductById(id);
	    if (productEntity.isPresent()) {
	        ProductModel productModel = new ProductModel();
	        ProductEntity entity = productEntity.get();
	        productModel.setId(entity.getId());
	        productModel.setName(entity.getName());
	        productModel.setPrice(entity.getPrice());
	        productModel.setQuantity(entity.getQuantity());
	        productModel.setMadeIn(entity.getMadeIn());
	        productModel.setBrand(entity.getBrand());

	        model.addAttribute("productModel", productModel);
	        return "editform"; // This should map to your form for editing
	    } else {
	        model.addAttribute("errorMessage", "Product not found");
	        return "product-list"; // Redirect to the product list
	    }
	}

	// The POST method for updating the product
	@PostMapping("/editproduct")
	public String editProductData(@ModelAttribute("productModel") @Valid ProductModel productModel,
	                               BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        // If there are validation errors, return to the edit form
	        return "editform";
	    }

	    productService.updateProduct(productModel); // Update the product using the service
	    return "redirect:/getAllProducts"; // Redirect to the product list after successful update
	}
}

