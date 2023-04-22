package com.japetech.games.services;

import com.japetech.games.gamesDtos.EmpresaDto;
import com.japetech.games.models.EmpresaModel;
import com.japetech.games.models.EnderecoModel;
import com.japetech.games.repositories.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpresaService extends MyService<EmpresaModel, Long>{

    final EmpresaRepository empresaRepository;

    EmpresaService(JpaRepository<EmpresaModel, Long> repository, EmpresaRepository empresaRepository) {
        super(repository);
        this.empresaRepository = empresaRepository;
    }

    public EmpresaModel save(EmpresaDto empresaDto){

        try {
            EmpresaModel empresa = new EmpresaModel();
            BeanUtils.copyProperties(empresaDto, empresa);

            EnderecoModel endereco = new EnderecoModel();
            BeanUtils.copyProperties(empresaDto.getEndereco(), endereco);

            empresa.setEndereco(endereco);

            return repository.save(empresa);

        } catch (Exception e){
            throw new RuntimeException("Erro ao salvar o nova empresa.");

        }

    }
    public List<EmpresaModel> findBynomeContainingIgnoreCase(String nome){
        return ((EmpresaRepository) repository).findBynomeContainingIgnoreCase(nome);
    }

}
