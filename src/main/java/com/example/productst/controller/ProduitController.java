package com.example.productst.controller;

import com.example.productst.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/product-entries")
public class ProduitController {
    @Autowired
    private ProduitService productEntryService;
    public static String uploadedDirectory=System.getProperty("user.dir")+"/src/WebApp/images";
    @PostMapping("/{productType}")
    public ResponseEntity<?> insertProductEntry(
            @RequestParam("image") MultipartFile imageFile,
            @PathVariable String productType,
            @RequestParam Map<String, Object> productData

    ) throws IOException {
        productEntryService.insertProductEntry(productType, productData,imageFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{productType}")
    public ResponseEntity<?> getPro(@PathVariable String productType) {
        List<Map<String, Object>> products = productEntryService.GetProductsOfAType(productType);

        // Iterate through the products and add the image data as Base64 strings
        for (Map<String, Object> product : products) {
            String imagePath = (String) product.get("path_image");

            if (imagePath != null) {
                try {
                    // Read the image file and encode it as Base64
                    byte[] imageBytes = Files.readAllBytes(Paths.get(uploadedDirectory, imagePath));
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    product.put("image", base64Image);
                } catch (IOException e) {
                    // Handle exceptions appropriately
                }
            }
        }

        return ResponseEntity.ok(products);
    }
    //--------------------------------------------

}

