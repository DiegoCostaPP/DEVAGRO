package com.example.DevinAgro.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.EmptyInterceptor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 60)
    private String nome;
    @Column(nullable = false, length = 60)
    private String sobreNome;
    @CPF
    @Pattern(regexp = "([0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2})")
    @Size(max = 14, min = 14)
    @Column(nullable = false, length = 60)
    private String cpf;
    @Column(nullable = false, length = 60)
    private String endereco;
    @Pattern(regexp = "\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{7}$")
    @Column(nullable = false, length = 60)
    private String telefone;
    @Column(nullable = false, length = 60)
    private String sexo;
    @Pattern(regexp = "[0-9]{2}\\/.?[0-9]{2}\\/.?[0-9]{4}")
    @Size(max = 10, min = 10)
    @Column(nullable = false, length = 60)
    private String dataNascimento;
    @Pattern(regexp = "[0-9]{2}\\/.?[0-9]{2}\\/.?[0-9]{4}")
    @Size(max = 10, min = 10)
    @Column(nullable = false, length = 60)
    private String dataAdmicao;


    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
