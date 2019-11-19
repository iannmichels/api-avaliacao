package com.example.vaga.service;

import com.example.vaga.model.Pessoa;
import com.example.vaga.model.dto.PessoaDTO;
import com.example.vaga.repository.PessoaRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@Component
@Transactional(rollbackFor = RuntimeException.class)
public class PessoaService {

    private final PessoaRepository repository;
    private final MapperFacade mapper;

    @Autowired
    public PessoaService(PessoaRepository repository, MapperFacade mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public List<PessoaDTO> findAll(Pageable pageable) {
        return mapper.mapAsList(repository.findAll(pageable), PessoaDTO.class);
    }

    public Page<PessoaDTO> findByExample(Pessoa pessoa, Pageable pageable) {
        ExampleMatcher exp = ExampleMatcher.matching().withIgnoreNullValues();
        Page<Pessoa> retorno = repository.findAll(Example.of(pessoa, exp), pageable);
        List<PessoaDTO> ret = mapper.mapAsList(retorno.getContent(), PessoaDTO.class);
        return new PageImpl<PessoaDTO>(ret, pageable, retorno.getTotalElements());
    }

    public Optional<Pessoa> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<PessoaDTO> atualizar(Long id, PessoaDTO dto) {
        Optional<Pessoa> job = repository.findById(id).map(var -> {
            var = mapper.map(dto, Pessoa.class);
            return var;
        });
        if (job.isPresent()) {
            Pessoa retorno = repository.save(job.get());
            return Optional.ofNullable(mapper.map(job, PessoaDTO.class));
        } else {
            return Optional.empty();
        }
    }


    public Optional<PessoaDTO> criarJob(PessoaDTO jobDTO) {
        Pessoa pessoa = mapper.map(jobDTO, Pessoa.class);
        setaPaiRel(pessoa);
        try {
            return Optional.ofNullable(mapper.map(repository.save(pessoa), PessoaDTO.class));
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Não permitido cadastrar uma pessoa um CPF existente!");
        }

    }

    private void setaPaiRel(Pessoa pessoa) {
        pessoa.getEndereco().setPessoa(pessoa);
        if (!CollectionUtils.isEmpty(pessoa.getEmails())) {
            pessoa.getEmails().forEach(email -> {
                email.setPessoa(pessoa);
            });
        }
        if (!CollectionUtils.isEmpty(pessoa.getTelefones())) {
            pessoa.getTelefones().forEach(tel -> {
                tel.setPessoa(pessoa);
            });
        }
    }

    public Optional<String> delete(Long id) {
        repository.deleteById(id);
        return Optional.ofNullable("sim");
    }
}
