package com.example.DevinAgro.models.dto;

import com.example.DevinAgro.models.Empresa;
import com.example.DevinAgro.models.Fazenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FazendaDto {

    private String nome;
    private String endereco;

    public Fazenda converter(Fazenda fazenda) {

        fazenda.setEndereco(endereco);
        fazenda.setNome(nome);
        return fazenda;
    }
}
