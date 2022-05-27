package br.com.saks.interesseservice.service;

import br.com.saks.interesseservice.model.Imovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "imovel-service")
public interface ImovelService {
    @GetMapping(value = "/imoveis/{idImovel}")
    Imovel listarPeloId(@PathVariable("idImovel") Long idImovel);
}
