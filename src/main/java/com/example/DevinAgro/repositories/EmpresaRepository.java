package com.example.DevinAgro.repositories;

import com.example.DevinAgro.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa,Long> {
}
