package com.wk.banco.sangue.repository;

import com.wk.banco.sangue.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
