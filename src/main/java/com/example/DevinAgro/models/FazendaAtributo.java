package com.example.DevinAgro.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FazendaAtributo {

    private Long id;
    private String nome;
    private String dataProximaColheita;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataProximaColheita() {
        return dataProximaColheita;
    }

    public void setDataProximaColheita(String dataProximaColheita) {
        this.dataProximaColheita = dataProximaColheita;
    }
}
