package com.example.productst.service;

import com.example.productst.model.Caracteristique;
import com.example.productst.model.TypeProduit;
import org.springframework.stereotype.Service;
import java.util.List;
public interface TypeProduitService {
    TypeProduit createTypeProduitAndTable(String nomTypeProduit, List<Caracteristique> caracteristiques);
    void createProductTable(TypeProduit typeProduit, List<Caracteristique> caracteristiques);
    List<TypeProduit> getAllTypes();
    List<String> getTableColumns(String productType);
    int getId(String productType);

}
