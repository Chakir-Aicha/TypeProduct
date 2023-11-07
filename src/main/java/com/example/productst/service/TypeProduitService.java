package com.example.productst.service;

import com.example.productst.model.Caracteristique;
import com.example.productst.model.TypeProduit;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
public interface TypeProduitService {
    TypeProduit createTypeProduitAndTable(String nomTypeProduit, List<Caracteristique> caracteristiques);
    void createProductTable(TypeProduit typeProduit, List<Caracteristique> caracteristiques);
    List<TypeProduit> getAllTypes();
    List<String> getTableColumns(String databaseName,String productType);
    int getId(String productType);


}
