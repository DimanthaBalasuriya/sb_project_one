package com.example.product_service.controller;

import com.example.product_service.dto.ProductDTO;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById/{productId}")
    public ProductDTO getProductById(@PathVariable Integer productID) {
        return productService.getProductById(productID);
    }

    @PostMapping("/addProduct")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @PutMapping("/updateProduct")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestBody ProductDTO productDTO) {
        return productService.deleteProduct(productDTO);
    }

    @DeleteMapping("deleteProduct/{productId}")
    public String deleteProduct(@PathVariable Integer productId) {
        return productService.deleteProduct(productId);
    }

}
