package products.cyber.api.products_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import products.cyber.api.products_api.dtos.ProductRecordDto;
import products.cyber.api.products_api.models.Message;
import products.cyber.api.products_api.models.ProductModel;
import products.cyber.api.products_api.repositories.ProductRepository;

@Service
public class ProductService {
   @Autowired
    Message message;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        var productModel = new ProductModel();

        BeanUtils.copyProperties(productRecordDto, productModel);


        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));

    }

    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    public ResponseEntity<?> getOne(@PathVariable UUID id){
  
       
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isEmpty()){
            message.setMessage("Poduct Not Found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }

    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id")UUID id, 
                                                @RequestBody @Valid ProductRecordDto productRecordDto){
        
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isEmpty()){
            message.setMessage("Poduct Not Found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
                                            
        var productModel = productO.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isEmpty()){
            message.setMessage("PRODUCT NOT FOUND!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        message.setMessage("Product Deleted!");
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body(message);

    }
    
}