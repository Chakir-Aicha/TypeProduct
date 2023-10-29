package com.example.productst.controller;

import com.example.productst.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/product-entries")
public class ProduitController {
    @Autowired
    private ProduitService productEntryService;

    @PostMapping("/{productType}")
    public ResponseEntity<?> insertProductEntry(
            @PathVariable String productType,
            @RequestBody Map<String, Object> productData
    ) {
        productEntryService.insertProductEntry(productType, productData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{productType}")
    public  ResponseEntity<?>  getPro( @PathVariable String productType){
        List<Map<String, Object>> products =productEntryService.GetProductsOfAType(productType);
        return ResponseEntity.ok(products);
    }
}

