package com.example.DevinAgro.models.dto;

import com.example.DevinAgro.models.Fazenda;
import com.example.DevinAgro.models.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDto {

    private String nome;
    private String sobreNome;
    private String endereco;
    private String telefone;
    private String dataNascimento;

    public Funcionario converter(Funcionario funcionario) {

        funcionario.setNome(nome);
        funcionario.setSobreNome(sobreNome);
        funcionario.setEndereco(endereco);
        funcionario.setTelefone(telefone);
        funcionario.setDataNascimento(dataNascimento);

        return funcionario;
    }
}
