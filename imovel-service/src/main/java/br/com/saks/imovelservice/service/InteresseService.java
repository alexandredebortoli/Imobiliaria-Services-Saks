package br.com.saks.imovelservice.service;

import br.com.saks.imovelservice.model.Interesse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "interesse-service")
public interface InteresseService {
    @GetMapping(value = "/interesses/imovel/{idImovel}")
    List<Interesse> listarPeloIdImovel(@PathVariable("idImovel") Long idImovel);
}
