package com.example.vaga.repository;

import com.example.vaga.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findByNameEquals(String name);

    Pessoa findByCpfEquals(String CPF);
}
