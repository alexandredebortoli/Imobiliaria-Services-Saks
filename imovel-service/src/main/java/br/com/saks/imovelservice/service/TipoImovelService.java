package br.com.saks.imovelservice.service;

import br.com.saks.imovelservice.model.TipoImovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tipo-imovel-service")
public interface TipoImovelService {
    @GetMapping(value = "/tiposimovel/{idTipoImovel}")
    TipoImovel listarPeloId(@PathVariable("idTipoImovel") Long idTipoImovel);
}
