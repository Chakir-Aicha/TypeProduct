package com.example.productst.service;

import com.example.productst.model.Caracteristique;
import com.example.productst.model.TypeProduit;
import com.example.productst.repository.TypeProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeProduitServiceImpl implements TypeProduitService{
    @Autowired
    private TypeProduitRepository typeProduitRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public TypeProduit createTypeProduitAndTable(String nomTypeProduit, List<Caracteristique> caracteristiques) {
        TypeProduit typeProduit = new TypeProduit();
        typeProduit.setNom(nomTypeProduit);
        typeProduitRepository.save(typeProduit);
        createProductTable(typeProduit, caracteristiques);
        return typeProduit;
    }

    @Override
    public void createProductTable(TypeProduit typeProduit, List<Caracteristique> caracteristiques) {
        StringBuilder createTableSQL = new StringBuilder("CREATE TABLE " + typeProduit.getNom() + " (");
        createTableSQL.append("id INT AUTO_INCREMENT PRIMARY KEY,");

        for (Caracteristique caract : caracteristiques) {
            createTableSQL.append(caract.
                    getNom() + " " + caract.getTypeDonnees() + ",");
        }
        createTableSQL.append("typeProduit_id INT, FOREIGN KEY (typeProduit_id) REFERENCES type_produit(id),");

        createTableSQL.deleteCharAt(createTableSQL.length() - 1); // Supprimez la virgule en trop
        createTableSQL.append(")");

        jdbcTemplate.execute(createTableSQL.toString());
    }
}