package com.bolsadeideas.springboot.app.controller;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Causa;
import com.bolsadeideas.springboot.app.models.entity.Informacion;
import com.bolsadeideas.springboot.app.models.entity.LlamadaTelefonica;
import com.bolsadeideas.springboot.app.models.entity.MovimientoBancario;
import com.bolsadeideas.springboot.app.models.entity.RedSocial;
import com.bolsadeideas.springboot.app.models.service.ICausaService;
import com.bolsadeideas.springboot.app.models.service.ILlamadaTelefonicaService;
import com.bolsadeideas.springboot.app.models.service.IMovimientoBancarioService;
import com.bolsadeideas.springboot.app.models.service.IRedSocialService;


@Controller
@RequestMapping("/informacion")
@SessionAttributes("informacion")
public class InformacionController {
	
	@Autowired
	private ICausaService causaService;

	@Autowired
	private ILlamadaTelefonicaService llamadaTelefonicaService;
	
	@Autowired
	private IMovimientoBancarioService movimientoBancarioService;

	@Autowired
	private IRedSocialService redSocialService;
	
	/* MÉTODOS DE LLAMADAS */
	@GetMapping("/llamadasForm/{causaid}")
	public String crearLlamada(@PathVariable(value = "causaid") Long causaId, Map<String, Object> model,
			RedirectAttributes flash) {

		Causa causa = causaService.findOne(causaId);
		if (causa == null) {
			flash.addFlashAttribute("error", "La causa no existe en la base de datos");
			return "redirect:/causas";
		}
		LlamadaTelefonica llamada = new LlamadaTelefonica();
		llamada.setCausa(causa);

		model.put("informacion", llamada);
		model.put("titulo", "Agregar llamada telefónica");
		model.put("causa", causa);

		return "informacion/llamadasForm" ;
	}

	/*@RequestMapping(value = "/llamadasForm", method = RequestMethod.POST)
	public String guardarLlamada(@Valid @ModelAttribute LlamadaTelefonica informacion, BindingResult result, Model model,
			@ModelAttribute("") Causa causa, RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de llamada telefónica");
			return "informacion/llamadasForm"  ;
		}
			
		
		informacion.setCausa(causa);
		llamadaTelefonicaService.create(informacion);
		return "redirect:/verCausas/" + informacion.getCausa().getId();
	}*/
	
	@RequestMapping(value = "/llamadasForm", method = RequestMethod.POST)
	public String guardarLlamada(@Valid @ModelAttribute("informacion") LlamadaTelefonica informacion, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de llamada telefónica");
			return "informacion/llamadasForm"  ;
		}
			
		llamadaTelefonicaService.create(informacion);
		return "redirect:/verCausa/" + informacion.getCausa().getId();
	}
	@GetMapping(value = "/verLlamada/{id}")
	public String verLlamada(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		LlamadaTelefonica l = llamadaTelefonicaService.getLlamadaTelefonica(id);
		if (l == null) {
			flash.addFlashAttribute("error", "La causa no existe en la base de datos");
			return "redirect:/causas";
		}
		
		model.put("informacion", l);
		model.put("titulo", "Llamada: ");
		

		return "informacion/verLlamada";
	}
	
	
	@GetMapping("/deleteLlam/{id}")
    public String llamadaDelete(@PathVariable("id") Long id){
            Long idDireccion=llamadaTelefonicaService.getLlamadaTelefonica(id).getCausa().getId();
            llamadaTelefonicaService.delete(id);
        return "redirect:/verCausa/"+idDireccion;
    }
	

	
	/* MÉTODOS DE MOVIMIENTO */
	@GetMapping("/movBancarioForm/{causaid}")
	public String crearMovimiento(@PathVariable(value = "causaid") Long causaId, Map<String, Object> model,
			RedirectAttributes flash) {

		Causa causa = causaService.findOne(causaId);
		if (causa == null) {
			flash.addFlashAttribute("error", "La causa no existe en la base de datos");
			return "redirect:/causas";
		}
		MovimientoBancario mov = new MovimientoBancario();
		mov.setCausa(causa);

		model.put("informacion", mov);
		model.put("titulo", "Agregar movimiento bancario");
		model.put("causa", causa);

		return "informacion/movBancarioForm" ;
	}
	
	@RequestMapping(value = "/movBancarioForm", method = RequestMethod.POST)
	public String guardarMovimiento(@Valid @ModelAttribute("informacion") MovimientoBancario informacion, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de movimiento bancario");
			return "informacion/movBancarioForm"  ;
		}
			
		movimientoBancarioService.create(informacion);
		return "redirect:/verCausa/" + informacion.getCausa().getId();
	}


	@GetMapping("/deleteMov/{id}")
    public String movimientoDelete(@PathVariable("id") Long id){
            Long idDireccion=movimientoBancarioService.getMovimientoBancario(id).getCausa().getId();
            movimientoBancarioService.delete(id);
        return "redirect:/verCausa/"+idDireccion;
    }
	
	@GetMapping(value = "/verMovimiento/{id}")
	public String verMovimiento(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		MovimientoBancario m = movimientoBancarioService.getMovimientoBancario(id);
		if (m == null) {
			flash.addFlashAttribute("error", "La causa no existe en la base de datos");
			return "redirect:/causas";
		}
		
		model.put("informacion", m);
		model.put("titulo", "Movimiento bancario: ");
		

		return "informacion/verMovimiento";
	}
	
	


	/* MÉTODOS DE RED SOCIAL */
	@GetMapping("/redSocialForm/{causaid}")
	public String crearRed(@PathVariable(value = "causaid") Long causaId, Map<String, Object> model,
			RedirectAttributes flash) {

		Causa causa = causaService.findOne(causaId);
		if (causa == null) {
			flash.addFlashAttribute("error", "La causa no existe en la base de datos");
			return "redirect:/causas";
		}
		RedSocial red = new RedSocial();
		red.setCausa(causa);

		model.put("informacion", red);
		model.put("titulo", "Agregar movimiento bancario");
		model.put("causa", causa);

		return "informacion/redSocialForm" ;
	}
	
	@RequestMapping(value = "/redSocialForm", method = RequestMethod.POST)
	public String guardarRed(@Valid @ModelAttribute("informacion") RedSocial informacion, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de red social");
			return "informacion/redSocialForm"  ;
		}
			
		redSocialService.create(informacion);
		return "redirect:/verCausa/" + informacion.getCausa().getId();
	}
	
	@GetMapping("/deleteRed/{id}")
    public String redDelete(@PathVariable("id") Long id){
            Long idDireccion=redSocialService.getRedSocial(id).getCausa().getId();
            redSocialService.delete(id);
        return "redirect:/verCausa/"+idDireccion;
    }
	
	@GetMapping(value = "/verRedSocial/{id}")
	public String verRed(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		RedSocial r = redSocialService.getRedSocial(id);
		if (r == null) {
			flash.addFlashAttribute("error", "La causa no existe en la base de datos");
			return "redirect:/causas";
		}
		
		model.put("informacion", r);
		model.put("titulo", "Red social: ");
		

		return "informacion/verRedSocial";
	}
	

}
