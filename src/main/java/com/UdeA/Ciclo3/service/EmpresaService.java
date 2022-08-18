package com.UdeA.Ciclo3.service;

import com.UdeA.Ciclo3.modelos.Empresa;
import com.UdeA.Ciclo3.repo.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {
    @Autowired  // se conecta con el repository empresaRepository
    EmpresaRepository empresaRepository;

    public List<Empresa> getEmpresa(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(Empresa -> empresaList.add(Empresa));

        return empresaList;
    }

}
