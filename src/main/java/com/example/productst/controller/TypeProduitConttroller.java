package com.example.productst.controller;

import com.example.productst.Dto.TypeProduitDto;
import com.example.productst.model.Caracteristique;
import com.example.productst.model.TypeProduit;
import com.example.productst.service.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/typeproduits")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TypeProduitConttroller {
    @Autowired
    private TypeProduitService typeProduitService;
    @PostMapping
    public ResponseEntity<TypeProduit> createTypeProduit(@RequestBody TypeProduitDto request) {
        TypeProduit createdTypeProduit = typeProduitService.createTypeProduitAndTable(request.getTypeProduit().getNom(), request.getCaracteristiques());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTypeProduit);
    }
    @GetMapping
    public List<TypeProduit> getAllTypes(){
       List<TypeProduit>list= typeProduitService.getAllTypes();
       return list;
    }
    @GetMapping("Columns/{productType}")
     public List<String> getTableColonnes( @PathVariable String productType){
        List<String> Columns=typeProduitService.getTableColumns(productType);
        return Columns;
    }
    @GetMapping("IdType/{productType}")
    public int getIdType(@PathVariable String productType){
        return typeProduitService.getId(productType);
    }
}
