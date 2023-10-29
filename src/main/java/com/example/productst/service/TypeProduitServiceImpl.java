package com.example.productst.service;

import com.example.productst.model.Caracteristique;
import com.example.productst.model.TypeProduit;
import com.example.productst.repository.TypeProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        createTableSQL.append("path_image VARCHAR(255),");
        for (Caracteristique caract : caracteristiques) {
            createTableSQL.append(caract.
                    getNom() + " " + caract.getTypeDonnees() + ",");
        }
        createTableSQL.append("typeProduit_id INT, FOREIGN KEY (typeProduit_id) REFERENCES type_produit(id),");

        createTableSQL.deleteCharAt(createTableSQL.length() - 1); // Supprimez la virgule en trop
        createTableSQL.append(")");

        jdbcTemplate.execute(createTableSQL.toString());
    }

    @Override
    public List<TypeProduit> getAllTypes() {
       List<TypeProduit> AllTypes= typeProduitRepository.findAll();
       return AllTypes;
    }
    public List<String> getTableColumns(String productType) {
        try {
            // Utilisez la requête SQL pour obtenir les noms des colonnes de la table
            String query = "SELECT column_name FROM information_schema.columns WHERE table_name = ?";
            List<String> columns = jdbcTemplate.queryForList(query, new Object[]{productType}, String.class);
            return columns;
        } catch (DataAccessException ex) {
            // Gérez les exceptions ou enregistrez les erreurs selon vos besoins
            throw new RuntimeException("Failed to retrieve columns for table " + productType, ex);
        }
    }

    @Override
    public int getId(String productType) {
        try {
            // Use a SQL query to fetch the id based on the product type name
            String query = "SELECT id FROM type_produit WHERE nom = ?";
            List<Integer> ids = jdbcTemplate.queryForList(query, new Object[]{productType}, Integer.class);

            if (!ids.isEmpty()) {
                return ids.get(0); // Return the first id (assuming product types are unique)
            } else {
                throw new RuntimeException("Product type not found: " + productType);
            }
        } catch (DataAccessException ex) {
            // Handle exceptions or log errors as needed
            throw new RuntimeException("Failed to retrieve the id for product type: " + productType, ex);
        }
    }

}