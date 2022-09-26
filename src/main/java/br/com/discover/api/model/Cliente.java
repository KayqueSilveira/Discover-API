package br.com.discover.api.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Cliente {

    private Long id;

    private String nome;
    private double salario;
    private Cartao cartao;
}
