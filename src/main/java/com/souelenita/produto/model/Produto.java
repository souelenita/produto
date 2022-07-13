package com.souelenita.produto.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Data
@Entity
public class Produto {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private float preco;
    private int quantidade;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime dataCriacao;


}
