package com.example.DevinAgro.models.dto;

import com.example.DevinAgro.models.Funcionario;
import com.example.DevinAgro.models.Grao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraoDto {

    private String nome;
    private Integer tempoColheita;


    public Grao converter(Grao grao) {

        grao.setNome(nome);
        grao.setTempoColheita(tempoColheita);

        return grao;
    }
}
