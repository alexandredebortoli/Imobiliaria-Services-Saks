package br.com.saks.tipoimovelservice.repository;

import br.com.saks.tipoimovelservice.model.TipoImovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoImovelRepository extends JpaRepository<TipoImovel, Long> {
    
}
