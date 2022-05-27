package br.com.saks.interesseservice.service;

import br.com.saks.interesseservice.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service")
public interface ClienteService {
    @GetMapping(value = "/clientes/{idCliente}")
    Cliente listarPeloId(@PathVariable("idCliente") Long idCliente);
}
