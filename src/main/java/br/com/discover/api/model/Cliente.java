package br.com.discover.api.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "cliente")
@NoArgsConstructor
public class Cliente implements Serializable {

    private static final long serialVersionUID = -4008205208027610432L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cpf;
    @NotNull
    private String nome;
    @NotNull
    private double salario;

}
