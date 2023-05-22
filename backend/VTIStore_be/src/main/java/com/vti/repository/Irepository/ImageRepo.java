package com.vti.repository.Irepository;

import com.vti.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepo extends JpaRepository<ProductImage, Long> {
    Optional<ProductImage> findByName(String name);
}
