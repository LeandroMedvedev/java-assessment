package org.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Usuario implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String senha;
}
