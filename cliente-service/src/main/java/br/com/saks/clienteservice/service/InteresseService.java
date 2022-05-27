package br.com.saks.clienteservice.service;

import br.com.saks.clienteservice.model.Interesse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "interesse-service")
public interface InteresseService {
    @GetMapping(value = "/interesses/cliente/{idCliente}")
    List<Interesse> listarPeloIdCliente(@PathVariable("idCliente") Long idCliente);
}
