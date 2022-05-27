package br.com.saks.interesseservice.repository;

import br.com.saks.interesseservice.model.Interesse;
import br.com.saks.interesseservice.model.InteresseIdentity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresseRepository extends JpaRepository<Interesse, InteresseIdentity> {
    public List<Interesse> findByInteresseIdentityIdCliente(Long idCliente);
    public List<Interesse> findByInteresseIdentityIdImovel(Long idImovel);
}
