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
import java.util.Map;
@CrossOrigin
@RequestMapping("/typeproduits")
@RestController
public class TypeProduitConttroller {
    @Autowired
    private TypeProduitService typeProduitService;
    @GetMapping
    public List<TypeProduit> gettype(){
        return typeProduitService.getType();
    }
    @PostMapping
    public ResponseEntity<TypeProduit> createTypeProduit(@RequestBody TypeProduitDto request) {
        TypeProduit createdTypeProduit = typeProduitService.createTypeProduitAndTable(request.getTypeProduit().getNom(), request.getCaracteristiques());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTypeProduit);
    }

}
