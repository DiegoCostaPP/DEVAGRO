package com.example.DevinAgro.repositories;

import com.example.DevinAgro.models.Empresa;
import com.example.DevinAgro.models.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FazendaRepository extends JpaRepository<Fazenda,Long> {

  List<Fazenda> findByEmpresa(Empresa empresa);

}
