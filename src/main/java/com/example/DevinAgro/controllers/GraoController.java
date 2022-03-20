package com.example.DevinAgro.controllers;

import com.example.DevinAgro.models.Empresa;
import com.example.DevinAgro.models.Funcionario;
import com.example.DevinAgro.models.Grao;
import com.example.DevinAgro.models.dto.FuncionarioDto;
import com.example.DevinAgro.models.dto.GraoDto;
import com.example.DevinAgro.services.EmpresaService;
import com.example.DevinAgro.services.GraoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/grao")
public class GraoController {

    private GraoService graoService;
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Grao>> findAll(){

        List<Grao> list = graoService.findALL();
        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Grao> findById(@PathVariable Long id) {

        Grao grao = graoService.findById(id);
        return ResponseEntity.ok().body(grao);
    }

    @PostMapping
    public ResponseEntity<Grao> insert(@RequestBody @Valid Grao grao) {

        grao = graoService.add(grao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}")
                .buildAndExpand(grao.getId()).toUri();
        return ResponseEntity.created(uri).body(grao);
    }

    @PutMapping
    public ResponseEntity<Grao> update(@PathVariable @Valid Long id, @RequestBody GraoDto grao){

        Grao atualizaFuncionario = graoService.update(id, grao.converter(graoService.findById(id)));
        return ResponseEntity.ok().body(atualizaFuncionario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        graoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/graporempresa/{id}") //http://localhost:8082/grao/graporempresa/2 EndPoint 7
    public ResponseEntity<List<Grao>> findGraoByEmpresa(@PathVariable Long id) {

        Empresa empresa = empresaService.findById(id);
        return ResponseEntity.ok().body(graoService.graoPorEmpresa(empresa));
    }
}
