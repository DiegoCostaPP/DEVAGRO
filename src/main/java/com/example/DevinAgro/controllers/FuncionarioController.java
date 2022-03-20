package com.example.DevinAgro.controllers;

import com.example.DevinAgro.models.Empresa;
import com.example.DevinAgro.models.Fazenda;
import com.example.DevinAgro.models.Funcionario;
import com.example.DevinAgro.models.dto.FuncionarioDto;
import com.example.DevinAgro.services.EmpresaService;
import com.example.DevinAgro.services.FuncionarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private FuncionarioService funcionarioService;
    private EmpresaService empresaService;


    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll(){

        List<Funcionario> list = funcionarioService.findALL();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {

        Funcionario funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok().body(funcionario);
    }

    @PostMapping
    public ResponseEntity<Funcionario> insert(@RequestBody @Valid Funcionario funcionario) {

        funcionario = funcionarioService.add(funcionario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}")
                .buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).body(funcionario);
    }

    @PutMapping
    public ResponseEntity<Funcionario> update(@PathVariable @Valid Long id, @RequestBody FuncionarioDto funcionario){

        Funcionario atualizaFuncionario = funcionarioService.update(id, funcionario.converter(funcionarioService.findById(id)));
        return ResponseEntity.ok().body(atualizaFuncionario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/funcionarioporempresa/{id}") //http://localhost:8082/funcionario/funcionarioporempresa/1 EndPoint 9
    public ResponseEntity<List<Funcionario>> FindFazendaByEmpresa(@PathVariable Long id) {

        Empresa empresa = empresaService.findById(id);
        return ResponseEntity.ok().body(funcionarioService.fazendaPorEmpresa(empresa));
    }

    @GetMapping(value = "/contafuncionario/{id}") // http://localhost:8082/funcionario/contafuncionario/1 EndPoint 10
    public ResponseEntity<Integer> FindGraoByEmpresa(@PathVariable Long id) {

        Empresa empresa = empresaService.findById(id);
        return ResponseEntity.ok().body(funcionarioService.fazendaPorEmpresa(empresa).size());
    }


}
