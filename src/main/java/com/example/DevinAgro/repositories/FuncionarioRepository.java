package com.example.DevinAgro.repositories;

import com.example.DevinAgro.models.Empresa;
import com.example.DevinAgro.models.Fazenda;
import com.example.DevinAgro.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    List<Funcionario> findByEmpresa(Empresa empresa);
}
