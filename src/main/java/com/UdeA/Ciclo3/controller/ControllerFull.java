package com.UdeA.Ciclo3.controller;

import com.UdeA.Ciclo3.modelos.Empresa;
import com.UdeA.Ciclo3.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerFull {
    @Autowired  //el cable que se conecta con la clase deseada
   EmpresaService empresaService;

    //Get
    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresas(Model model, @ModelAttribute("mensaje") String mensaje) {
        List<Empresa> listEmpresas = empresaService.getEmpresa();
        model.addAttribute("emplist", listEmpresas);
        model.addAttribute("mensaje", mensaje);

        return "verEmpresas";  //aqui devolvemos a la pagina pero debemos ingresarla al template para cuando creemos el html
    }


    @GetMapping("/AgregarEmpresas") //los servicios empiezan por mayuscula los template no
    public String nuevaEmpresas(Model model, @ModelAttribute("mensaje") String mensaje) {
        Empresa emp = new Empresa();
        model.addAttribute("emp", emp);
        model.addAttribute("mensaje", mensaje);
        return "agregarEmpresas";// esto es un template
    }
    //Post
    @PostMapping("/GuardarEmpresas")
    public String guardarEmpresas(Empresa emp, RedirectAttributes redirectAttributes){
        if(empresaService.saveOrUpdateEmpresa(emp) == true){
            redirectAttributes.addFlashAttribute("mensaje", "saveOk"); //mensajes de aviso
            return "redirect:/VerEmpresas";
            }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/agregarEmpresas";  //esto es un servicio

    }

    @GetMapping("/EditarEmpresas/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empresa emp = empresaService.getEmpresaById(id);
        model.addAttribute("emp", emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String actualizarEmpresa(@ModelAttribute("emp") Empresa emp, RedirectAttributes redirectAttributes){
        if (empresaService.saveOrUpdateEmpresa(emp)) {
            redirectAttributes.addFlashAttribute("mensaje", "updateOk");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditarEmpresas";
    }
    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa( @PathVariable Integer id, RedirectAttributes redirectAttributes){

            if (empresaService.deleteEmpresa(id)== true){
                redirectAttributes.addFlashAttribute("mensaje", "deleteOk");
                return "redirect:/VerEmpresas";
        }
            redirectAttributes.addFlashAttribute("mensaje", "deleteError");
            return "redirect:/VerEmpresas";

        }



}
