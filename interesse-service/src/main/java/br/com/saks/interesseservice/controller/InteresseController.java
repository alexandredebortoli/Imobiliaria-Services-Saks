package br.com.saks.interesseservice.controller;

import br.com.saks.interesseservice.model.Interesse;
import br.com.saks.interesseservice.model.InteresseIdentity;
import br.com.saks.interesseservice.repository.InteresseRepository;
import br.com.saks.interesseservice.service.ClienteService;
import br.com.saks.interesseservice.service.ImovelService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interesses")
public class InteresseController {
    
    @Autowired
    private InteresseRepository interesseRepository;
    
    @Autowired
    private ImovelService imovelService;
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public List<Interesse> listarTodos() {
        return interesseRepository.findAll();
    }
    
    @GetMapping(value="/{idImovel}")
    public Interesse listarPeloId(@PathVariable Long idImovel) {
        return interesseRepository.findByInteresseIdentityIdImovel(idImovel).get(0);
    }
    
    @GetMapping(value = "/{idImovel}/{idCliente}")
    public Optional<Interesse> findByImovelCliente(@PathVariable Long idImovel, @PathVariable Long idCliente) {
        final InteresseIdentity identity = new InteresseIdentity(idImovel, idCliente);
        identity.setIdImovel(idImovel);
        identity.setIdCliente(idCliente);
        return interesseRepository.findById(identity);
    }
    
    @GetMapping(value = "/imovel/{idImovel}")
    public List<Interesse> listarPeloIdImovel(@PathVariable Long idImovel) {
        List<Interesse> interesses = interesseRepository.findByInteresseIdentityIdImovel(idImovel);
        for(Interesse interesse : interesses) {
            interesse.setCliente(clienteService.listarPeloId(interesse.getInteresseIdentity().getIdCliente()));
        }
        return interesses;
    }
    
    @GetMapping(value = "/cliente/{idCliente}")
    public List<Interesse> listarPeloIdCliente(@PathVariable Long idCliente) {
        List<Interesse> interesses = interesseRepository.findByInteresseIdentityIdCliente(idCliente);
        for(Interesse interesse : interesses) {
            interesse.setImovel(imovelService.listarPeloId(interesse.getInteresseIdentity().getIdImovel()));
        }
        return interesses;
    }
    
    @PostMapping
    public Interesse adicionar(@RequestBody Interesse interesse) {
        return interesseRepository.save(interesse);
    }
    
    @DeleteMapping(value="/{idImovel}/{idCliente}")
    public ResponseEntity deletar(@PathVariable Long idImovel, @PathVariable Long idCliente) {
        final InteresseIdentity identity= new InteresseIdentity(idImovel, idCliente);
        return interesseRepository.findById(identity)
                .map(record-> {
                    interesseRepository.deleteById(identity);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
