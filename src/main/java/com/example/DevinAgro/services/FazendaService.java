package com.example.DevinAgro.services;

import com.example.DevinAgro.models.Empresa;
import com.example.DevinAgro.models.Fazenda;
import com.example.DevinAgro.models.Grao;
import com.example.DevinAgro.repositories.FazendaRepository;
import com.example.DevinAgro.repositories.GraoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FazendaService {

    private final FazendaRepository fazendaRepository;
   // private final GraoRepository graoRepository;

    public List<Fazenda> findALL(){

        return fazendaRepository.findAll();
    }

    public Fazenda findById(Long id){

        Optional<Fazenda> fazenda = fazendaRepository.findById(id);
        return fazenda.get();
    }

    public Fazenda add(Fazenda fazenda){

        return fazendaRepository.save(fazenda);
    }

    public Fazenda update(Long id, Fazenda fazenda){

        Fazenda atualizaFazenda = fazendaRepository.save(fazenda);
        return atualizaFazenda;
    }

    public void delete(Long id){

        fazendaRepository.deleteById(id);
    }

    public List<Fazenda> findALL2(){

        return fazendaRepository.findAll();
    }

    public List<Fazenda> fazendaPorEmpresa (Empresa empresa){

        return fazendaRepository.findByEmpresa(empresa);
    }

}
