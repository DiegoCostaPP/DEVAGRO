package com.example.DevinAgro.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fazenda")
public class Fazenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 60)
    private String nome;
    @Column(nullable = false, length = 60)
    private String endereco;
    @Column(nullable = false, length = 60)
    private Double estoqueFazenda;
    @Column(nullable = false, length = 10)
    private String dataUltimaColheita;
    //LocalDate dataColheita = LocalDate.parse(dataUltimaColheita);

    @OneToOne
    @JoinColumn(name = "grao_id")
    private Grao grao;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
