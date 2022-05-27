package br.com.saks.imovelservice.controller;

import br.com.saks.imovelservice.model.Imovel;
import br.com.saks.imovelservice.repository.ImovelRepository;
import br.com.saks.imovelservice.service.InteresseService;
import br.com.saks.imovelservice.service.TipoImovelService;
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
@RequestMapping("/imoveis")
public class ImovelController {
    @Autowired
    private ImovelRepository imovelRepository;
    
    @Autowired
    private TipoImovelService tipoImovelService;
    
    @Autowired
    private InteresseService interesseService;
    
    @GetMapping
    public List<Imovel> listarTodos() {
        return imovelRepository.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Optional<Imovel> listarPeloId(@PathVariable Long id) {
        return imovelRepository.findById(id);
    }
    
    @GetMapping(value="/interesses/{id}")
    public Imovel listarPeloIdInteresses(@PathVariable Long id) {
        Optional<Imovel> imovelResponse = imovelRepository.findById(id);
        Imovel imovel = imovelResponse.get();
        imovel.setTipoImovel(tipoImovelService.listarPeloId(imovel.getIdTipoImovel()));
        imovel.setInteresses(interesseService.listarPeloIdImovel(imovel.getId()));
        return imovel;
    }
    
    @GetMapping(value = "/tipo-imovel/{idTipoImovel}")
    public List<Imovel> listarPeloTipoImovel(@PathVariable Long idTipoImovel) {
        return imovelRepository.findAllByIdTipoImovel(idTipoImovel);
    }
    
    @PostMapping
    public Imovel adicionar(@RequestBody Imovel imovel) {
        return imovelRepository.save(imovel);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Imovel imovel) {
        return imovelRepository.findById(id)
                .map(record -> {
                    record.setStatus(imovel.getStatus());
                    Imovel imovelUpdated = imovelRepository.save(record);
                    return ResponseEntity.ok().body(imovelUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return imovelRepository.findById(id)
                .map(record-> {
                    imovelRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
