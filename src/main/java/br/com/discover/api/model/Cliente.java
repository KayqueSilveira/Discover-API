package br.com.discover.api.model;

import lombok.Data;

@Data
public class Cliente {

    private Long id;

    private String nome;
    private double salario;
    private Cartao cartao;
}
