package br.com.saks.administradorservice.controller;

import br.com.saks.administradorservice.model.Administrador;
import br.com.saks.administradorservice.repository.AdministradorRepository;
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
@RequestMapping("/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorRepository administradorRepository;
    
    @GetMapping
    public List<Administrador> listarTodos() {
        return administradorRepository.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Optional<Administrador> listarPeloId(@PathVariable Long id) {
        return administradorRepository.findById(id);
    }
    
    @PostMapping
    public Administrador adicionar(@RequestBody Administrador administrador) {
        administrador.setSenha(encriptografar(administrador.getSenha()));
        return administradorRepository.save(administrador);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Administrador administrador) {
        return administradorRepository.findById(id)
                .map(record -> {
                    record.setNome(administrador.getNome());
                    record.setEmail(administrador.getEmail());
                    record.setSenha(encriptografar(administrador.getSenha()));
                    record.setStatus(administrador.getStatus());
                    Administrador administradorUpdated = administradorRepository.save(record);
                    return ResponseEntity.ok().body(administradorUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return administradorRepository.findById(id)
                .map(record-> {
                    administradorRepository.deleteById(id);
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
