package com.UdeA.Ciclo3.controller;

import com.UdeA.Ciclo3.modelos.Empresa;
import com.UdeA.Ciclo3.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ControllerFull {
    @Autowired  //el cable que se conecta con la clase deseada
   EmpresaService empresaService;


    @GetMapping({"/", "/verEmpresa"})
    public String viewEmpresas(Model model) {
        List<Empresa> listEmpresas = empresaService.getEmpresa();
        model.addAttribute("emplist", listEmpresas);

        return "verEmpresa";  //aqui devolvemos a la pagina pero debemos ingresarla al template para cuando creemos el html
    }
}
