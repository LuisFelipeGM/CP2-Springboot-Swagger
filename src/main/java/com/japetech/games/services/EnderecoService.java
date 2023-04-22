package com.japetech.games.services;

import com.japetech.games.models.EnderecoModel;
import com.japetech.games.repositories.EnderecoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService extends MyService<EnderecoModel, Long>{

    private final EnderecoRepository enderecoRepository;

    EnderecoService(JpaRepository<EnderecoModel, Long> repository, EnderecoRepository enderecoRepository){
        super(repository);
        this.enderecoRepository = enderecoRepository;
    }

    public List<EnderecoModel> findByestadoContainingIgnoreCase(String estado){
        return ((EnderecoRepository) repository).findByestadoContainingIgnoreCase(estado);
    }
}
