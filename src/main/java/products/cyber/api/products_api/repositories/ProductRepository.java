package products.cyber.api.products_api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


import products.cyber.api.products_api.models.ProductModel;


public interface ProductRepository extends JpaRepository<ProductModel, UUID>{
    
    List<ProductModel> findAll(); 
}
