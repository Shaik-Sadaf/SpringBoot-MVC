package com.spring.java.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.java.entity.ProductEntity;
import com.spring.java.model.ProductModel;
import com.spring.java.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository ;

	public void saveProductData(ProductModel productModel)
	{
		double price=productModel.getPrice();
		double taxAmount=price*0.18;
		double totalAmount=price+taxAmount;
		ProductEntity productEntity=new ProductEntity();
		productEntity.setName(productModel.getName());
		productEntity.setPrice(productModel.getPrice());
		productEntity.setBrand(productModel.getBrand());
		productEntity.setMadeIn(productModel.getMadeIn());
		productEntity.setQuantity(productModel.getQuantity());
		productEntity.setTaxAmount(taxAmount);
		productEntity.setTotalAmount(totalAmount);

		productEntity.setCreatedAt(LocalDateTime.now());
		productEntity.setCreatedBy("sadaf");
		productRepository.save(productEntity);
	}

	public List<ProductEntity> getAllProducts() 
	{
		List<ProductEntity> products=productRepository.findAll();
		return products;
	}

	public Optional<ProductEntity>  getProductById(Long Id)
	{
		 Optional<ProductEntity> optionalProduct=productRepository.findById(Id);
		return optionalProduct;
	}

	public void deleteById(Long id) 
	{
		productRepository.deleteById(id);
	}

	//	 public void editById(Long id) {
//	        productRepository.findById(id).ifPresent(product -> {
//	            product.setName("Updated Name"); // Example update
//	            productRepository.save(product);
//	        });
//	    }
	
//	public ProductModel getEditProduct(Long id) {
//		// TODO Auto-generated method stub
//		ProductEntity productEntity=productRepository.findById(id).get();
//		ProductModel productModel=new ProductModel();
//		productModel.setName(productEntity.getName());
//		productModel.setMadeIn(productEntity.getMadeIn());
//		productModel.setPrice(productEntity.getPrice());
//		productModel.setQuantity(productEntity.getQuantity());
//		productModel.setBrand(productEntity.getBrand());
//		return productModel;
	

//	}	
//	public ProductModel getEditProduct(Long id) {
//	    ProductEntity productEntity = productRepository.findById(id)
//	        .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
//
//	    ProductModel productModel = new ProductModel();
//	    productModel.setName(productEntity.getName());
//	    productModel.setMadeIn(productEntity.getMadeIn());
//	    productModel.setPrice(productEntity.getPrice());
//	    productModel.setQuantity(productEntity.getQuantity());
//	    productModel.setBrand(productEntity.getBrand());
//	    return productModel;
//	}
//
//	public void updateProduct(ProductModel productModel) {
//	    productRepository.findById(productModel.getId()).ifPresent(product -> {
//	    	
//	        product.setName(productModel.getName());
//	        product.setPrice(productModel.getPrice());
//	        product.setQuantity(productModel.getQuantity());
//	        product.setMadeIn(productModel.getMadeIn());
//	        productRepository.save(product);
//	    });
//	}
	 public ProductModel getEditProduct(Long id) {
	        ProductEntity productEntity = productRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
	        
	        ProductModel productModel = new ProductModel();
	        productModel.setId(productEntity.getId());  // Ensure the id is set
	        productModel.setName(productEntity.getName());
	        productModel.setMadeIn(productEntity.getMadeIn());
	        productModel.setPrice(productEntity.getPrice());
	        productModel.setQuantity(productEntity.getQuantity());
	        productModel.setBrand(productEntity.getBrand());
	        
	        return productModel;
	    }
	 public void updateProduct(ProductModel productModel) {
	        // Check if the product with the given id exists in the database
	        Optional<ProductEntity> optionalProduct = productRepository.findById(productModel.getId());
	        
	        if (optionalProduct.isPresent()) {
	            ProductEntity productEntity = optionalProduct.get();
	            
	            // Update the product entity fields with the new data from the productModel
	            productEntity.setName(productModel.getName());
	            productEntity.setPrice(productModel.getPrice());
	            productEntity.setQuantity(productModel.getQuantity());
	            productEntity.setMadeIn(productModel.getMadeIn());
	            productEntity.setBrand(productModel.getBrand());

	            // Save the updated product entity to the database
	            productRepository.save(productEntity);
	        } else {
	            // If no product is found with the provided id, throw an exception
	            throw new RuntimeException("Product not found with id: " + productModel.getId());
	        }
	    }



	

	

	

	
}
