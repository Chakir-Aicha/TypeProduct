package com.example.productst.Dto;

import com.example.productst.model.Caracteristique;
import com.example.productst.model.TypeProduit;
import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TypeProduitDto {
    private TypeProduit typeProduit;
    private List<Caracteristique> caracteristiques;
}
