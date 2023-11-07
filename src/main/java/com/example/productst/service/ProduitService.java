package com.example.productst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class ProduitService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
public static String uploadedDirectory=System.getProperty("user.dir")+"/src/WebApp/images";
    public void insertProductEntry(String productType, Map<String, Object> productData, MultipartFile imageFile) throws IOException {
        String tableName = productType; // Use the product type as the table name

        // Encode the image data to Base64
        String originalFinalName=imageFile.getOriginalFilename();
        Path fileNameAndPath= Paths.get(uploadedDirectory,originalFinalName);
        Files.write(fileNameAndPath,imageFile.getBytes());
//        String base64Image = Base64.getEncoder().encodeToString(imageFile.getBytes());

        // Build the SQL INSERT statement dynamically based on the product data
        StringBuilder insertSql = new StringBuilder("INSERT INTO " + tableName + " (path_image,");
        StringBuilder valuesSql = new StringBuilder(" VALUES (?,");

        productData.forEach((column, value) -> {
            insertSql.append(column + ",");
            valuesSql.append("?,");
        });

        // Remove the trailing comma
        insertSql.deleteCharAt(insertSql.length() - 1);
        valuesSql.deleteCharAt(valuesSql.length() - 1);

        insertSql.append(")").append(valuesSql).append(")");

        List<Object> parameterValues = new ArrayList<>();
        parameterValues.add(originalFinalName); // Add the Base64-encoded image data as the first parameter

        // Add the remaining product data values
        parameterValues.addAll(productData.values());

        try {
            jdbcTemplate.update(insertSql.toString(), parameterValues.toArray());
        } catch (DataAccessException ex) {
            // Handle exceptions or log errors as needed
            throw new RuntimeException("Failed to insert data into " + tableName, ex);
        }
    }

    public List<Map<String, Object>> GetProductsOfAType(String productType) {
        String selectSql = "SELECT * FROM " + productType;

        try {
            List<Map<String, Object>> products = jdbcTemplate.queryForList(selectSql);
            for (Map<String, Object> product : products) {
                // Retrieve the image path from the result
                String imagePath = (String) product.get("path_image");

                // Read the image file and encode it as Base64
                byte[] imageBytes = Files.readAllBytes(Paths.get(uploadedDirectory, imagePath));
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                // Add the Base64-encoded image to the result
                product.put("image", base64Image);
            }

            return products;
        } catch (DataAccessException | IOException ex) {
            // Handle exceptions or log errors as needed
            throw new RuntimeException("Failed to retrieve products of type " + productType, ex);
        }
    }


}
