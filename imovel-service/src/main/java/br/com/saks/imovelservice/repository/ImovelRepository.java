package br.com.saks.imovelservice.repository;

import br.com.saks.imovelservice.model.Imovel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {
    public List<Imovel> findAllByIdTipoImovel(Long idTipoImovel);
}
