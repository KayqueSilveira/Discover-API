package br.com.discover.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "cliente")
@NoArgsConstructor
public class Cliente {

    @Id
    @Column(name="cliente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cpf;
    @NotNull
    private String nome;
    @NotNull
    private double salario;

    @OneToOne(mappedBy = "cliente", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Cartao cartao;

}
