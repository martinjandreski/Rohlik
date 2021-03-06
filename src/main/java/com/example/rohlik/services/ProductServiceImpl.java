package com.example.rohlik.services;

import com.example.rohlik.models.Product;
import com.example.rohlik.repositories.OrderRepository;
import com.example.rohlik.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<Object> createProduct(String name, Integer quantityInStock, Integer pricePerUnit) {
        productRepository.save(new Product(name,quantityInStock,pricePerUnit));
        return ResponseEntity.status(HttpStatus.OK).body("Product is created successfully");
    }

    @Override
    public void updateProductQuantityInStock(Product product, int quantity) {
        product.setQuantityInStock(product.getQuantityInStock()-quantity);
        productRepository.save(product);
    }

    @Override
    public ResponseEntity<Object> deleteProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            productRepository.delete(product.get());
            return ResponseEntity.status(HttpStatus.OK).body("Product is deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This product does not exist.");
    }

    @Override
    public ResponseEntity<Object> updateProduct(long id, Product updatedProduct) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            if (updatedProduct.getName()!=null){
                product.get().setName(updatedProduct.getName());
            }
            if (updatedProduct.getPricePerUnit()!=null){
                product.get().setPricePerUnit(updatedProduct.getPricePerUnit());
            }
            if (updatedProduct.getQuantityInStock()!=null){
                product.get().setQuantityInStock(updatedProduct.getQuantityInStock());
            }
            productRepository.save(product.get());
            return ResponseEntity.status(HttpStatus.OK).body("Product is updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This product does not exist.");
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findProductByName(name);
    }
}
