package br.com.discover.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "cartao")
@NoArgsConstructor
public class Cartao implements Serializable {

    private static final long serialVersionUID = -8259395041807220424L;

    @Id
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

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Cliente cliente;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Transacao> transacao;

}
