package br.com.saks.tipoimovelservice.controller;

import br.com.saks.tipoimovelservice.model.TipoImovel;
import br.com.saks.tipoimovelservice.repository.TipoImovelRepository;
import br.com.saks.tipoimovelservice.service.ImovelService;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tiposimovel")
public class TipoImovelController {
    @Autowired
    private TipoImovelRepository tipoImovelRepository;
    
    @Autowired
    private ImovelService imovelService;
    
    @GetMapping
    public List<TipoImovel> listarTodos() {
        return tipoImovelRepository.findAll();
    }
    
    @GetMapping(value ="/{id}")
    public TipoImovel listarPeloId(@PathVariable Long id) {
        Optional<TipoImovel> tipoImovelResponse = tipoImovelRepository.findById(id);
        TipoImovel tipoImovel = tipoImovelResponse.get();
        tipoImovel.setImoveis(imovelService.listarPeloTipoImovel(id));
        return tipoImovel;
    }
    
    @PostMapping
    public TipoImovel adicionar(@RequestBody TipoImovel tipoImovel) {
        return tipoImovelRepository.save(tipoImovel);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody TipoImovel tipoImovel) {
        return tipoImovelRepository.findById(id)
                .map(record -> {
                    record.setNome(tipoImovel.getNome());
                    TipoImovel tipoImovelUpdated = tipoImovelRepository.save(record);
                    return ResponseEntity.ok().body(tipoImovelUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return tipoImovelRepository.findById(id)
                .map(record-> {
                    tipoImovelRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    
    public String encriptografar(String senha) {
        String senhaHash = "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            senhaHash = hash.toString(16);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return senhaHash;
    }
}
