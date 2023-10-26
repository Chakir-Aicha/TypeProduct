package com.example.productst.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeProduit {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String Nom;
}
