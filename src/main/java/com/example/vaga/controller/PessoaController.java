package com.example.vaga.controller;

import com.example.vaga.model.Pessoa;
import com.example.vaga.model.dto.PessoaDTO;
import com.example.vaga.service.PessoaService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin()
@RestController
@RequestMapping("/pessoaws")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;
    private final MapperFacade mapper;

    public PessoaController(MapperFacade mapper) {
        this.mapper = mapper;
    }


    @GetMapping(value = "/search/find-all-page", produces = "application/json")
    public Page<PessoaDTO> findAll(Pessoa pessoa, Pageable pageable) {
        return pessoaService.findByExample(pessoa, pageable);
    }

    @GetMapping()
    public List<PessoaDTO> findAll(Pageable pageable) {
        return pessoaService.findAll(pageable);
    }

    @PutMapping(value = "/salvar-by-id/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaDTO> updateSalvarLancamento(@Valid @RequestBody PessoaDTO job, @PathVariable Long id) {
        return pessoaService.atualizar(id, job).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> criarJob(@Valid @RequestBody PessoaDTO job) {
        return pessoaService.criarJob(job).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/{id}")
    public PessoaDTO findById(@PathVariable Long id) {
        return mapper.map(pessoaService.findById(id).orElse(null), PessoaDTO.class);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        return pessoaService.delete(id).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }
}
