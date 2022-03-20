package com.example.DevinAgro.services;

import com.example.DevinAgro.models.Empresa;
import com.example.DevinAgro.repositories.EmpresaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public List<Empresa> findALL(){

        return empresaRepository.findAll();
    }

    public Empresa findById(Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.get();
    }

    public Empresa add(Empresa empresa){

        return empresaRepository.save(empresa);
    }

    public Empresa update(Long id, Empresa empresa){

        Empresa atualizaEmpresa = empresaRepository.save(empresa);
        return atualizaEmpresa;
    }

    public void delete(Long id){

        empresaRepository.deleteById(id);
    }

    /*public List<Fazenda> findALL2(){

        return empresaRepository.findAll();
    }*/

}
