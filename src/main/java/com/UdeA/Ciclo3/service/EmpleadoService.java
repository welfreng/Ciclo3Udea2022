package com.UdeA.Ciclo3.service;

import com.UdeA.Ciclo3.modelos.Empleado;
import com.UdeA.Ciclo3.repo.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public List<Empleado> getEmpleado(){
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    //metodo buscr¿ar empleado por id
    public Optional<Empleado> getEmpleadoById(Integer id) {
        return empleadoRepository.findById(id);
    }

    //metodo buscar por enpresa
    public ArrayList<Empleado> obtenerPorEmpresa(Integer id) {
        return empleadoRepository.findByEmpresa(id);
    }
    //guardar empleado
    public Empleado saveOrUpdateEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    //metodo eliminar emleado por id
    public boolean deleteEmpleado(Integer id){
        empleadoRepository.deleteById(id);
        if (this.empleadoRepository.findById(id).isPresent()) {
            return false;
        }
        return true;
    }


}
