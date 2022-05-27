package br.com.saks.interesseservice.model;

import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Interesse {
    @EmbeddedId
    private InteresseIdentity interesseIdentity;
    
    @Transient
    Imovel imovel;
    
    @Transient
    Cliente cliente;
    
    
}
