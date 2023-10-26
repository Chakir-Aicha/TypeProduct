package com.example.productst.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TypeProduit")
public class TypeProduit {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String Nom;
}
