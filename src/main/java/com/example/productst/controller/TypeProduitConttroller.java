package com.example.productst.controller;

import com.example.productst.Dto.TypeProduitDto;
import com.example.productst.model.Caracteristique;
import com.example.productst.model.TypeProduit;
import com.example.productst.service.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/typeproduits")
@RestController
public class TypeProduitConttroller {
    @Autowired
    private TypeProduitService typeProduitService;
    @PostMapping
    public ResponseEntity<TypeProduit> createTypeProduit(@RequestBody TypeProduitDto request) {
        TypeProduit createdTypeProduit = typeProduitService.createTypeProduitAndTable(request.getTypeProduit().getNom(), request.getCaracteristiques());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTypeProduit);
    }
}
