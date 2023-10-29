package com.example.productst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProduitService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertProductEntry(String productType, Map<String, Object> productData) {
        String tableName = productType; // Use the product type as the table name

        // Build the SQL INSERT statement dynamically based on the product data
        StringBuilder insertSql = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder valuesSql = new StringBuilder(" VALUES (");

        productData.forEach((column, value) -> {
            insertSql.append(column + ",");
            valuesSql.append("?,");
        });

        // Remove the trailing comma
        insertSql.deleteCharAt(insertSql.length() - 1);
        valuesSql.deleteCharAt(valuesSql.length() - 1);

        insertSql.append(")").append(valuesSql).append(")");

        try {
            jdbcTemplate.update(insertSql.toString(), productData.values().toArray());
        } catch (DataAccessException ex) {
            // Handle exceptions or log errors as needed
            throw new RuntimeException("Failed to insert data into " + tableName, ex);
        }
    }

    public List<Map<String, Object>> GetProductsOfAType(String productType){
        String selectSql = "SELECT * FROM " + productType;

        try {
            List<Map<String, Object>> products = jdbcTemplate.queryForList(selectSql);
            return products;
        } catch (DataAccessException ex) {
            // Handle exceptions or log errors as needed
            throw new RuntimeException("Failed to retrieve products of type " + productType, ex);
        }


    }

}
