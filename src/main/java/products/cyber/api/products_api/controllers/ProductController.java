package products.cyber.api.products_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import products.cyber.api.products_api.dtos.ProductRecordDto;
import products.cyber.api.products_api.models.ProductModel;
import products.cyber.api.products_api.repositories.ProductRepository;
import products.cyber.api.products_api.services.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

 

    @Autowired
    ProductService productService;

    @PostMapping(value="/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        return productService.saveProduct(productRecordDto);
    }
    
    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
      return productService.getAllProducts();
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<?> getOne(@PathVariable(value = "id")UUID id){
        return productService.getOne(id);
    }


    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id")UUID id, 
                                                @RequestBody @Valid ProductRecordDto productRecordDto){
        return productService.updateProduct(id, productRecordDto);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        return productService.deleteProduct(id);
    }
}
