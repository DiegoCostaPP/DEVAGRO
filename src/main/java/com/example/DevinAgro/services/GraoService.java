package com.example.DevinAgro.services;

import com.example.DevinAgro.models.Empresa;
import com.example.DevinAgro.models.Funcionario;
import com.example.DevinAgro.models.Grao;
import com.example.DevinAgro.repositories.GraoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GraoService {

    private final GraoRepository graoRepository;

    public List<Grao> findALL(){

        return graoRepository.findAll();
    }

    public Grao findById(Long id){

        Optional<Grao> grao = graoRepository.findById(id);
        return grao.get();
    }

    public Grao add(Grao grao){

        return graoRepository.save(grao);
    }

    public Grao update(Long id, Grao grao){

        Grao atualizaGrao = graoRepository.save(grao);
        return atualizaGrao;
    }

    public void delete(Long id){

        graoRepository.deleteById(id);
    }

    public List<Grao> graoPorEmpresa (Empresa empresa){
        return graoRepository.findByEmpresa(empresa);
    }
}
