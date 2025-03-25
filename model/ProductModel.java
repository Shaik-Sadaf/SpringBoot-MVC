package com.spring.java.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel
{
    private Long id;  // Add this field
//	private String name;
//	private double price;
//	private int quantity;
//	private String madeIn;
//	private String brand;
	 @NotNull(message = "Product name cannot be null")
	    @Size(min = 5, max = 100, message = "Product name must be between 1 and 100 characters")
	    private String name;

	    @NotNull(message = "Price cannot be null")
	    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
	    private double price;

	    @Min(value = 0, message = "Quantity must be zero or greater")
	    private int quantity;

	    @NotNull(message = "Brand cannot be null")
	    @Size(min = 1, max = 50, message = "Brand name must be between 1 and 50 characters")
	    private String brand;

	    @NotNull(message = "Made in cannot be null")
	    @Size(min = 2, max = 50, message = "Made in field must be between 2 and 50 characters")
	    private String madeIn;

//		public Long getId() {
//			// TODO Auto-generated method stub
//			return null;
//		}

}
