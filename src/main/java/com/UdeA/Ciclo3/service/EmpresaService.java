package com.UdeA.Ciclo3.service;

import com.UdeA.Ciclo3.modelos.Empresa;
import com.UdeA.Ciclo3.repo.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//Le decimos a spring que esta clase es de servicio
@Service
public class EmpresaService {
    @Autowired  // se conecta con el repository empresaRepository
    EmpresaRepository empresaRepository; //creamos un objeto tipo empresa para usar los metodos

    //Metodo que reornara la lista de empresa usando heredados del JpaRepository
    public List<Empresa> getEmpresa(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(Empresa -> empresaList.add(Empresa));

        return empresaList;
    }

    //metodo que me trae un objeto de tipo empresa
    public Empresa getEmpresabyId(Integer id) {
        return empresaRepository.findById(id).get();
    }

    //Metodo para guardar o actualizar objetos de tipo empresa
    public boolean saveOrUpdate(Empresa empresa)  {
        Empresa emp = empresaRepository.save(empresa);
        if (empresaRepository.findById(emp.getId()) != null) {
            return true;
        }
        return false;
    }

    //metodo delete pendiente

}
