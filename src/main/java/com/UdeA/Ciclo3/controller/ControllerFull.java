package com.UdeA.Ciclo3.controller;

import com.UdeA.Ciclo3.modelos.Empresa;
import com.UdeA.Ciclo3.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerFull {
    @Autowired  //el cable que se conecta con la clase deseada
   EmpresaService empresaService;

    //Get
    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresas(Model model) {
        List<Empresa> listEmpresas = empresaService.getEmpresa();
        model.addAttribute("emplist", listEmpresas);

        return "verEmpresas";  //aqui devolvemos a la pagina pero debemos ingresarla al template para cuando creemos el html
    }

    //Post
    @GetMapping("/AgregarEmpresas") //los servicios empiezan por mayuscula los template no
    public String nuevaEmpresas(Model model) {
        Empresa emp = new Empresa();
        model.addAttribute("emp", emp);
        return "agregarEmpresas";// esto es un template
    }

    @PostMapping("/GuardarEmpresas")
    public String guardarEmpresas(Empresa emp, RedirectAttributes redirectAttributes){
        if(empresaService.saveOrUpdateEmpresa(emp) == true){
            return "redirect:/VerEmpresas";
            }
        return "redirect:/agregarEmpresas";
    }

}
