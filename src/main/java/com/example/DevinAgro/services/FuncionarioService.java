package com.example.DevinAgro.services;

import com.example.DevinAgro.models.Empresa;
import com.example.DevinAgro.models.Fazenda;
import com.example.DevinAgro.models.Funcionario;
import com.example.DevinAgro.repositories.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findALL(){

        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id){

        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.get();
    }

    public Funcionario add(Funcionario funcionario){

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Long id, Funcionario funcionario){

        Funcionario atualizaFuncionario = funcionarioRepository.save(funcionario);
        return atualizaFuncionario;
    }

    public void delete(Long id){

        funcionarioRepository.deleteById(id);
    }

    public List<Funcionario> fazendaPorEmpresa (Empresa empresa){
        return funcionarioRepository.findByEmpresa(empresa);
    }

}
