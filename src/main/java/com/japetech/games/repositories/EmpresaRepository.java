package com.japetech.games.repositories;

import com.japetech.games.models.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaModel, Long> {

    List<EmpresaModel> findBynomeContainingIgnoreCase(String nome);

}
