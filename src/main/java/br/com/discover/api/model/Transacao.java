package br.com.discover.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime hora;
    @NotNull
    private double valor;
    @NotNull
    private String lugar;
    private String nomeCliente;

    @ManyToOne
    @JoinColumn(name="cartao_id", referencedColumnName = "cartao_id")
    @JsonBackReference
    private Cartao cartao;

    public Transacao(){
        super();
    }
}
