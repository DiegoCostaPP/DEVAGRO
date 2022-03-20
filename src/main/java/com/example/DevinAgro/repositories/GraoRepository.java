package com.example.DevinAgro.repositories;

import com.example.DevinAgro.models.Empresa;
import com.example.DevinAgro.models.Grao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GraoRepository extends JpaRepository<Grao,Long> {

    List<Grao> findByEmpresa(Empresa empresa);
}
