package com.example.DevinAgro.controllers;

import com.example.DevinAgro.models.*;
import com.example.DevinAgro.models.dto.FazendaDto;
import com.example.DevinAgro.services.EmpresaService;
import com.example.DevinAgro.services.FazendaService;
import com.example.DevinAgro.models.FazendaAtributo;
import com.example.DevinAgro.services.FuncionarioService;
import com.example.DevinAgro.services.GraoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController

@RequestMapping("/fazenda")
public class FazendaController {

    private FazendaService fazendaService;
    private EmpresaService empresaService;
    //private FazendaAtributo fazendaAtributo;
    //private FuncionarioService funcionarioService;
    //private GraoService graoService;

    @GetMapping
    public ResponseEntity<List<Fazenda>> findAll(){

        List<Fazenda> list = fazendaService.findALL();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Fazenda> findById(@PathVariable Long id) {

        Fazenda fazenda = fazendaService.findById(id);
        return ResponseEntity.ok().body(fazenda);
    }

    @PostMapping
    public ResponseEntity<Fazenda> insert(@RequestBody @Valid Fazenda fazenda) {

        fazenda = fazendaService.add(fazenda);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}")
                .buildAndExpand(fazenda.getId()).toUri();
        return ResponseEntity.created(uri).body(fazenda);
    }

    @PutMapping
    public ResponseEntity<Fazenda> update(@PathVariable @Valid Long id, @RequestBody FazendaDto fazenda){

        Fazenda atualizaEmpresa = fazendaService.update(id, fazenda.converter(fazendaService.findById(id)));
        return ResponseEntity.ok().body(atualizaEmpresa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        fazendaService.delete(id);
        return ResponseEntity.noContent().build();
    }

   @GetMapping(value = "/fazendaporempresa/{id}") // http://localhost:8082/fazenda/fazendaporempresa/1 EndPoint 2
    public ResponseEntity<List<Fazenda>> FindFazendaByEmpresa(@PathVariable Long id) {

        Empresa empresa = empresaService.findById(id);
        return ResponseEntity.ok().body(fazendaService.fazendaPorEmpresa(empresa));
    }

    @GetMapping(value = "/contafazenda/{id}") //http://localhost:8082/fazenda/contafazenda/1 EndPoint 3
    public ResponseEntity<Integer> FindGraoByEmpresa(@PathVariable Long id) {

        Empresa empresa = empresaService.findById(id);
        return ResponseEntity.ok().body(fazendaService.fazendaPorEmpresa(empresa).size());
    }

    @GetMapping(value = "/atirbutosporempresa/{id}") //http://localhost:8082/fazenda/atirbutosporempresa/2 EndPoint 4
    public ResponseEntity<List<FazendaAtributo>> FindFazendaByEmpresa2(@PathVariable Long id) {

        Empresa empresa = empresaService.findById(id);
        List <FazendaAtributo> empresaAtributo = new ArrayList<FazendaAtributo>();
        for (Fazenda fazenda : fazendaService.fazendaPorEmpresa(empresa)) {
            FazendaAtributo fazendaAtributo1 = new FazendaAtributo();
            fazendaAtributo1.setId(fazenda.getId());
            fazendaAtributo1.setNome(fazenda.getNome());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println(fazenda.getDataUltimaColheita());
            LocalDate ultimaColheita1 = LocalDate.parse(fazenda.getDataUltimaColheita(), formatter);
            Grao grao = fazenda.getGrao();
            LocalDate proximaColheita = ultimaColheita1.plusDays(grao.getTempoColheita());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println(proximaColheita);
            String dataColheita = proximaColheita.toString();
            fazendaAtributo1.setDataProximaColheita(dataColheita);
            empresaAtributo.add(fazendaAtributo1);

        }
        return ResponseEntity.ok().body(empresaAtributo);
    }

    @PutMapping(value = "/colheita/{idFazenda}/{quantidade}") // http://localhost:8082/fazenda/colheita/2/20000 EndPint 5
    public ResponseEntity<Fazenda> registracolheita(@PathVariable @Valid Long idFazenda, @PathVariable Double quantidade){

        Fazenda fazenda = fazendaService.findById(idFazenda);
        Double estoqueAtual = fazenda.getEstoqueFazenda();
        fazenda.setEstoqueFazenda(estoqueAtual+quantidade);
        Fazenda atualizaEmpresa = fazendaService.update(idFazenda, fazenda);
        return ResponseEntity.ok().body(atualizaEmpresa);
    }

    @PutMapping(value = "/venda/{idFazenda}/{quantidade}") //http://localhost:8082/fazenda/venda/2/20000 EndPoint 6
    public ResponseEntity<Fazenda> registravenda(@PathVariable @Valid Long idFazenda, @PathVariable Double quantidade){

        Fazenda fazenda = fazendaService.findById(idFazenda);
        Double estoqueAtual = fazenda.getEstoqueFazenda();
        fazenda.setEstoqueFazenda(estoqueAtual-quantidade);
        Fazenda atualizaEmpresa = fazendaService.update(idFazenda, fazenda);
        return ResponseEntity.ok().body(atualizaEmpresa);
    }

    @GetMapping(value = "/contagrao/{id}") //http://localhost:8082/fazenda/contagrao/2 EndPoint 8
    public ResponseEntity<List<EmpresaGrao>> contaGrao(@PathVariable Long id) {

        Empresa empresa = empresaService.findById(id);

        List <EmpresaGrao> empresaGrao = new ArrayList<EmpresaGrao>();

        for (Fazenda fazenda : fazendaService.fazendaPorEmpresa(empresa)) { //Verifica se já existe grao na lista
            Grao grao = fazenda.getGrao();
            EmpresaGrao eg = new EmpresaGrao();
            eg.setNome(grao.getNome());
            eg.setQtde(0.0);
            if(!empresaGrao.contains(eg))    {
                empresaGrao.add(eg);
            }
        }
        for (Fazenda fazenda : fazendaService.fazendaPorEmpresa(empresa)) { //Agrupa a quantidade grãos
            Grao grao = fazenda.getGrao();
            for(int i =0; i<empresaGrao.size(); i++){
                if(empresaGrao.get(i).getNome() == grao.getNome()){
                    empresaGrao.get(i).setQtde(empresaGrao.get(i).getQtde()+fazenda.getEstoqueFazenda());
                }
            }

        }
        return ResponseEntity.ok().body(empresaGrao);
    }


}
