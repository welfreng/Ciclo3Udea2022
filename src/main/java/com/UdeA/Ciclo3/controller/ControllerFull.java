package com.UdeA.Ciclo3.controller;

import com.UdeA.Ciclo3.modelos.Empleado;
import com.UdeA.Ciclo3.modelos.Empresa;
import com.UdeA.Ciclo3.modelos.MovimientoDinero;
import com.UdeA.Ciclo3.service.EmpleadoService;
import com.UdeA.Ciclo3.service.EmpresaService;
import com.UdeA.Ciclo3.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ControllerFull {
    @Autowired
    EmpresaService empresaService;
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    MovimientosService movimientosService;
    //Empresas
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

    //Empleado
    @GetMapping("/empleados")  //ver json de todos los empleados
    public List<Empleado> verEmpleados() {
        return empleadoService.getEmpleado();
    }

    @PostMapping("/empleados")  //guardar empleados
    public Optional<Empleado> guardarEmpleados(@RequestBody Empleado empl) {

        return Optional.ofNullable(this.empleadoService.saveOrUpdateEmpleado(empl));
    }
    @GetMapping(path = "/empleados/{id}")
    public Optional<Empleado> empleadoPorID(@PathVariable("id") Integer id){
    return this.empleadoService.getEmpleadoById(id);

    }

    @GetMapping("/enterprises/{id}/empleados")  //consultar empleados por empresa
    public ArrayList<Empleado> EmpleadoPorEmpresa(@PathVariable("id") Integer id){
        return this.empleadoService.obtenerPorEmpresa(id);
    }

    @PatchMapping("/empleados/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody Empleado empleado){
        Empleado empl=empleadoService.getEmpleadoById(id).get();
        empl.setNombre(empleado.getNombre());
        empl.setCorreo(empleado.getCorreo());
        empl.setEmpresa(empleado.getEmpresa());
        empl.setRol(empleado.getRol());
        return empleadoService.saveOrUpdateEmpleado(empl);
    }

    @DeleteMapping("/empleados/{id}") //Metodo para eliminar empleados por id
    public String DeleteEmpleado(@PathVariable("id") Integer id){
        boolean respuesta=empleadoService.deleteEmpleado(id); //eliminamos usando el servicio de nuestro service
        if (respuesta){ //si la respuesta booleana es true, si se eliminò
            return "Se pudo eliminar correctamente el empleado con id "+id;
        }//Si la respuesta booleana es false, no se eliminó
        return "No se puedo eliminar correctamente el empleado con id "+id;
    }

    //MOVIMIENTOS

    @GetMapping("/movimientos") //Consultar todos los movimientos
    public List<MovimientoDinero> verMovimientos(){
        return movimientosService.getMovimientos();
    }

    @PostMapping("/movimientos")
    public MovimientoDinero guardarMovimiento(@RequestBody MovimientoDinero movimiento){
        return movimientosService.saveOrUpdateMovimiento(movimiento);
    }

    @GetMapping("/movimientos/{id}") //Consultar movimiento por id
    public MovimientoDinero movimientoPorId(@PathVariable("id") Integer id){
        return movimientosService.getMovimientoById(id);
    }

    @PatchMapping("/movimientos/{id}")//Editar o actualizar un movimiento
    public MovimientoDinero actualizarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimiento){
        MovimientoDinero mov=movimientosService.getMovimientoById(id);
        mov.setConcepto(movimiento.getConcepto());
        mov.setMonto(movimiento.getMonto());
        mov.setUsuario(movimiento.getUsuario());
        return movimientosService.saveOrUpdateMovimiento(mov);
    }

    @DeleteMapping("/movimientos/{id}")
    public String deleteMovimiento(@PathVariable("id") Integer id){
        boolean respuesta= movimientosService.deleteMovimiento(id);
        if (respuesta){
            return "Se elimino correctamente el movimiento con id " +id;
        }
        return "No se pudo eliminar el movimiento con id "+id;
    }

    @GetMapping("/empleados/{id}/movimientos") //Consultar movimientos por id del empleado
    public ArrayList<MovimientoDinero> movimientosPorEmpleado(@PathVariable("id") Integer id){
        return movimientosService.obtenerPorEmpleado(id);
    }

    @GetMapping("/enterprises/{id}/movimientos") //Consultar movimientos que pertenecen a una empresa por el id de la empresa
    public ArrayList<MovimientoDinero> movimientosPorEmpresa(@PathVariable("id") Integer id){
        return movimientosService.obtenerPorEmpresa(id);
    }

}
