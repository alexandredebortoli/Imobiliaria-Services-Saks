package br.com.saks.imovelservice.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Transient
    TipoImovel tipoImovel;
    
    @Column(nullable = false, name = "id_tipo_imovel")
    private Long idTipoImovel;
    
    @Column(nullable = false, length = 100)
    private String titulo;
    
    @Column(nullable = true, length = 500)
    private String descricao;
    
    @Column(name = "data_criacao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCriacao;
    
    @Column(nullable = true)
    private Float valor;
    
    @Column(nullable = false)
    private Integer status;
    
    @Transient
    List<Interesse> interesses;
}
