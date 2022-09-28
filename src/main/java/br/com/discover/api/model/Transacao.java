package br.com.discover.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @JsonBackReference
    private Cartao cartao;


}
