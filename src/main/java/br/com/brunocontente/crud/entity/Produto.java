package br.com.brunocontente.crud.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    @SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

}
