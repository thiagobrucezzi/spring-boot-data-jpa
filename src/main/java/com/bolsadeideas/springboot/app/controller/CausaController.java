package com.bolsadeideas.springboot.app.controller;


import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bolsadeideas.springboot.app.models.entity.Causa;
import com.bolsadeideas.springboot.app.models.entity.Informacion;
import com.bolsadeideas.springboot.app.models.service.ICausaService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("causa")
public class CausaController {
	
    @Autowired
    private ICausaService causaService;
    
 
    @RequestMapping(value={"/causas", "/causas/{param}"}, method=RequestMethod.GET)
    public String listarCausas(@RequestParam(name="page", defaultValue = "0") int page,Model model,
    		HttpServletRequest request, @Param("param") String palabraClave) {
    	
        Pageable pageRequest= PageRequest.of(page, 5);
        if (palabraClave != null) {
        	Page<Causa> causas=causaService.findAll(pageRequest, palabraClave);
        	PageRender<Causa> pageRender=new PageRender<>("/causas", causas);
            model.addAttribute("titulo", "Listado de causas");
            model.addAttribute("causas", causas);
            model.addAttribute("page", pageRender);
            return "/causas";
        }else {
        	Page<Causa> causas=causaService.findAll(pageRequest);
        	PageRender<Causa> pageRender=new PageRender<>("/causas", causas);
            model.addAttribute("titulo", "Listado de causas");
            model.addAttribute("causas", causas);
            model.addAttribute("page", pageRender);
            
            return "/causas";
        }
	
    }
    
    /*@RequestMapping(value={"/buscarCausas"}, method=RequestMethod.GET)
    public String buscar(Model model, @Param("palabraClave") String palabraClave) {
    	List<Causa> causas=causaService.findAll(palabraClave);
    	model.addAttribute("causas", causas);
    	return  "/causas";
    }*/
    
    //Crear una causa
  	@Secured("ROLE_ADMIN")
  	@RequestMapping(value = "/causasForm")
  	public String crearCausa(Map<String, Object> model) {

  		Causa causa = new Causa();
  		model.put("causa", causa);
  		model.put("titulo", "Formulario de causa");
  		return "causasForm";
  	}
  	
  	//Eliminar causa
	@Secured("ROLE_ADMIN")
    @GetMapping("/eliminarCausa/{id}")
    public String eliminarCausa(@PathVariable Long id, RedirectAttributes flash) {
        causaService.delete(id);
        flash.addFlashAttribute("success", "Causa eliminada con éxito!");
        return "redirect:/causas";
    }
	
	//Ver causa
    @GetMapping(value = "/verCausa/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Causa causa = causaService.findOne(id);
		if (causa == null) {
			flash.addFlashAttribute("error", "La causa no existe en la base de datos");
			return "redirect:/causas";
		}
		
		model.put("causa", causa);
		model.put("titulo", "Causa N°: " + causa.getNumExpediente());
		model.put("historial", causaService.listaInformaciones(causa)
				.stream().sorted(Comparator.comparing(Informacion::getFecha).reversed())
				.collect(Collectors.toList()));

		return "verCausa";
	}
    
    //Editar causa	
  	@Secured("ROLE_ADMIN")	
  	@RequestMapping(value = "/causasForm/{id}")
  	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

  		Causa causa = null;

  		if (id > 0) {
  			causa = causaService.findOne(id);
  			if (causa == null) {
  				flash.addFlashAttribute("error", "El ID de la causa no existe en la BBDD!");
  				return "redirect:/causas";
  			}
  		} else {
  			flash.addFlashAttribute("error", "El ID de la causa no puede ser cero!");
  			return "redirect:/causas";
  		}
  		model.put("causa", causa);
  		model.put("titulo", "Editar causa");
  		return "causasForm";
  	}
   
  	
  	@RequestMapping(value = "/causasForm", method = RequestMethod.POST)
	public String guardar(@Valid Causa causa, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de causa");
			return "causasForm";
		}
		
		causaService.save(causa);
		status.setComplete();
		return "redirect:/causas";
	}
  	
  
  	@GetMapping("/cambiarEstado/{id}")
    public String cambiarEstado(@PathVariable("id") Long id, Model model){
            Causa c= causaService.findOne(id);
            if (c.getEstado()) {
            	c.setEstado(false);
            }else {
            	c.setEstado(true);
            }
            causaService.update(c);
            
        return "redirect:/verCausa/" + c.getId();
    }
	
  	

}
