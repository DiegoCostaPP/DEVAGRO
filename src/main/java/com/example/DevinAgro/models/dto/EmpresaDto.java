package com.example.DevinAgro.models.dto;

import com.example.DevinAgro.models.Empresa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDto {

    private String nome;
    private String endereco;

    public Empresa converter(Empresa empresa) {

        empresa.setEndereco(endereco);
        empresa.setNome(nome);
        return empresa;
    }
}
