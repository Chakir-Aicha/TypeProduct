package com.example.productst.service;

import com.example.productst.model.Caracteristique;
import com.example.productst.model.TypeProduit;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

public interface TypeProduitService {
    TypeProduit createTypeProduitAndTable(String nomTypeProduit, List<Caracteristique> caracteristiques);
    void createProductTable(TypeProduit typeProduit, List<Caracteristique> caracteristiques);
    List<TypeProduit> getType();
}
