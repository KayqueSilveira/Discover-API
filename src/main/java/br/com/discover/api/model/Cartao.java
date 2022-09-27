package br.com.discover.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "cartao")
@NoArgsConstructor
public class Cartao {

    @Id
    @Column(name="cartao_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;
    @NotNull
    private double limite;
    @NotNull
    private int senha;
    private double fatura;
    @NotNull
    private int numeroCartao;

    @OneToOne
    @JoinColumn(name="cliente_id", referencedColumnName = "cliente_id")
    @JsonManagedReference
    @NotNull
    private Cliente cliente;

    @OneToMany(mappedBy = "cartao")
    @JsonManagedReference
    private List<Transacao> transacao;



}
