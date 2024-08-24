package com.example.product_service.service;

import com.example.product_service.dto.ProductDTO;
import com.example.product_service.model.Product;
import com.example.product_service.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>() {
        }.getType());
    }

    public ProductDTO getProductById(Integer productId) {
        Product product = productRepo.getProductById(productId);
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public String deleteProduct(ProductDTO productDTO) {
        productRepo.delete(modelMapper.map(productDTO, Product.class));
        return "Successfully Deleted";
    }

    public String deleteProduct(Integer productId) {
        productRepo.deleteById(productId);
        return "Successfully Deleted";
    }

}
