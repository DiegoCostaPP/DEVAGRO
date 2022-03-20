package com.example.DevinAgro.controllers;

import com.example.DevinAgro.models.Empresa;
import com.example.DevinAgro.models.Fazenda;
import com.example.DevinAgro.models.dto.EmpresaDto;
import com.example.DevinAgro.services.EmpresaService;
import com.example.DevinAgro.services.FazendaService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private EmpresaService empresaService;

    @GetMapping("/todas_empresa")//Lista de empresas http://localhost:8082/empresa/todas_empresa Endpoint 1
    public ResponseEntity<List<Empresa>> findAll(){

        List<Empresa> list = empresaService.findALL();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Empresa> findById(@PathVariable Long id) {

        Empresa empresa = empresaService.findById(id);
        return ResponseEntity.ok().body(empresa);
    }

    @PostMapping
    public ResponseEntity<Empresa> insert(@RequestBody @Valid Empresa empresa) {

        empresa = empresaService.add(empresa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}")
                .buildAndExpand(empresa.getId()).toUri();
         return ResponseEntity.created(uri).body(empresa);
    }

    @PutMapping
    public ResponseEntity<Empresa> update(@PathVariable @Valid Long id, @RequestBody EmpresaDto empresa){

        Empresa atualizaEmpresa = empresaService.update(id, empresa.converter(empresaService.findById(id)));
        return ResponseEntity.ok().body(atualizaEmpresa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
