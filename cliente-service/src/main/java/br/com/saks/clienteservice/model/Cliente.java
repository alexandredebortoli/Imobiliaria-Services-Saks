package br.com.saks.clienteservice.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = true, length = 100)
    private String nome;
    
    @Column(nullable = false, length = 200)
    private String email;
    
    @Column(nullable = false, length = 200)
    private String senha;
    
    @Column(nullable = true, length = 15)
    private String telefone;
    
    @Transient
    List<Interesse> interesses;
}
