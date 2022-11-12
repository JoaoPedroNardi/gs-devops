package br.com.fiap.moverakiapi.controller.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.moverakiapi.model.Transporte;
import br.com.fiap.moverakiapi.service.TransporteService;

@Controller
@RequestMapping("/transporte")
public class TransporteWebController {

    @Autowired
    TransporteService service;

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("/transporte/index")
        .addObject("transportes", service.listAll());
    }

    @GetMapping("novo")
    public String form(Transporte transporte){
        return "/transporte/form";
    }

    @PostMapping
    public String create(@Valid Transporte transporte, BindingResult binding, RedirectAttributes redirect){
        if (binding.hasErrors())
            return "/transporte/form";

        String alerta = (transporte.getId() == null)?"Sucesso! Transporte cadastrado":"Trasporte editado com sucesso";
        service.save(transporte);
        redirect.addFlashAttribute("alerta", alerta);

        return "redirect:/transporte";
    }
    
    @GetMapping("delete/{id}")
     public String delete(@PathVariable Long id, RedirectAttributes redirect){
        
         service.deleteById(id);
         redirect.addFlashAttribute("alerta", "Transporte apagado com sucesso!");

         return "redirect:/transporte";
     }

     @GetMapping("{id}")
     public ModelAndView edit(@PathVariable Long id){
        Transporte transporte = service.getById(id).get();

         return new ModelAndView("/transporte/form").addObject("transporte", transporte);
     }
    
}
