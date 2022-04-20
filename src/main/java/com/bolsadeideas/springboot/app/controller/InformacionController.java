package com.bolsadeideas.springboot.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Causa;
import com.bolsadeideas.springboot.app.models.entity.LlamadaTelefonica;
import com.bolsadeideas.springboot.app.models.entity.MovimientoBancario;
import com.bolsadeideas.springboot.app.models.entity.RedSocial;
import com.bolsadeideas.springboot.app.models.service.ICausaService;
import com.bolsadeideas.springboot.app.models.service.ILlamadaTelefonicaService;
import com.bolsadeideas.springboot.app.models.service.IMovimientoBancarioService;

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
		causa.addInformacion(llamada);

		model.put("informacion", llamada);
		model.put("titulo", "Agregar llamada telefónica");

		return "informacion/llamadasForm" ;
	}

	@RequestMapping(value = "/llamadasForm", method = RequestMethod.POST)
	public String guardar(LlamadaTelefonica informacion, Model model, SessionStatus status) {
		

		llamadaTelefonicaService.save(informacion);
		status.setComplete();
		return "redirect:/verCausas";
	}
 	
	
	/*
	
	@RequestMapping(value = "/llamadasForm/{causaid}", method = RequestMethod.POST)
	public String guardar( @Valid LlamadaTelefonica llamada, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de llamada telefónica");
			return "informacion/llamadasForm"  ;
		}
		llamadaTelefonicaService.create(llamada);
		return "redirect:/verCausas" + llamada.getCausa().getId();
	}*/

	/* MÉTODOS DE MOVIMIENTO */
	@GetMapping("/movBancarioForm/{causaid}")
	public String crearMovimiento(@PathVariable(value = "causaid") Long causaId, Map<String, Object> model,
			RedirectAttributes flash) {

		Causa causa = causaService.findOne(causaId);
		if (causa == null) {
			flash.addFlashAttribute("error", "La causa no existe en la base de datos");
			return "redirect:/causas";
		}
		MovimientoBancario movimiento = new MovimientoBancario();
		movimiento.setCausa(causa);

		model.put("informacion", movimiento);
		model.put("titulo", "Agregar movimiento bancario");

		return "informacion/movBancarioForm";
	}

	/* MÉTODOS DE RED SOCIAL */
	@GetMapping("/redSocialForm/{causaid}")
	public String crearRedSocial(@PathVariable(value = "causaid") Long causaId, Map<String, Object> model,
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

		return "informacion/redSocialForm";
	}

}
