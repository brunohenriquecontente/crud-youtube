package br.com.brunocontente.crud.entity;

import br.com.brunocontente.crud.dto.ProdutoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Singular;

@Entity
@Table
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;


    public ProdutoDTO toDTO(){
        return new ProdutoDTO(
                this.id,
                this.nome,
                this.descricao);
    }
}
