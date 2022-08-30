package com.UdeA.Ciclo3.controller;

import com.UdeA.Ciclo3.modelos.Empresa;
import com.UdeA.Ciclo3.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ControllerFull {
@Autowired
    EmpresaService empresaService;

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas() {
        return empresaService.getEmpresa();
    }

    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa emp) {
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @GetMapping(path = "enterprises/{id}")
    public Empresa empresaPorID(@PathVariable("id") Integer id) {
        return this.empresaService.getEmpresaById(id);
    }
    @PatchMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa ) {
        Empresa emp = empresaService.getEmpresaById(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNIT(empresa.getNIT());
        return empresaService.saveOrUpdateEmpresa(emp);
    }

    @DeleteMapping(path="enterprises/{id}")
        public String DeleteEmpresa(@PathVariable("id") Integer id) {
            boolean respuesta = this.empresaService.deleteEmpresa(id);
            if (respuesta){
                return "Empresa eliminada Correctamente con el id: " + id;
            }
            return "Imposible eliminar empresa con id: " + id;
    }
}
