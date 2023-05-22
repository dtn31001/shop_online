package com.vti.entity.DTO;

import java.util.List;

import com.vti.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogDTO {
	private List<Product> products;
}
