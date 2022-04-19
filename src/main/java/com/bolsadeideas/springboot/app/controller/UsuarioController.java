package com.bolsadeideas.springboot.app.controller;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.service.IUsuarioService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@RequestMapping(value={"/listar"}, method=RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue = "0") int page, Model model, 
			Authentication authentication, 
			HttpServletRequest request) {
		
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		
		if(auth!=null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(auth.getName()));
		}
		
		if(hasRole("ROLE_ADMIN")) {
			logger.info("Hola ".concat(auth.getName()).concat(" tienes acceso!"));
		}else {
			logger.info("Hola ".concat(auth.getName()).concat(" no tienes acceso!"));
		}
		
		SecurityContextHolderAwareRequestWrapper securityContext= new SecurityContextHolderAwareRequestWrapper(request, "ROLE_"); 
		
		if(securityContext.isUserInRole("ADMIN")) {
			logger.info("Usando SecurityContextAware.... Hola ".concat(auth.getName()).concat(" tienes acceso!"));
		}else {
			logger.info("Usando SecurityContextAware....Hola ".concat(auth.getName()).concat(" no tienes acceso!"));

		}

		
		Pageable pageRequest= PageRequest.of(page, 5);
		Page<Usuario> usuarios=usuarioService.findAll(pageRequest);
		
				
		PageRender<Usuario> pageRender=new PageRender<>("/listar", usuarios);
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("page", pageRender);
		return "listar";
	}
	
	//Crear un usuario 
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Formulario de usuario");
		return "form";
	}

	//Editar usuario	
	@Secured("ROLE_ADMIN")	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;

		if (id > 0) {
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El ID del usuario no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del usuario no puede ser cero!");
			return "redirect:/listar";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Editar usuario");
		return "form";
	}


	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de usuario");
			return "form";
		}
		String mensaje= (usuario.getId() != null)?"Editar usuario":"Guardar usuario";
        String mensaje2 = (usuario.getId() != null)?"Editar usuario":"Crear usuario";
		 if(usuarioService.save2(usuario)){  
	            status.setComplete();
	            String mensajeFlash2 = "Operacion realizada satisfactoriamente!";
	            flash.addFlashAttribute("success",mensajeFlash2);
	            return "redirect:/listar";
	        }else{ 
	        	  model.addAttribute("mensaje",mensaje);
	              model.addAttribute("titulo", mensaje2);
	            if(usuario.getId()!=null){ 
	               try {
	            	   usuarioService.save(usuario);
	                   String mensajeFlash3 = (usuario.getId() != null)?"El usuario se ha modificado con éxito!":"Se ha agregado con éxito al usuario!";
	                   flash.addFlashAttribute("success",mensajeFlash3);
	                   return "redirect:/listar";

	               }catch (Exception e){
	                   model.addAttribute("danger","Error: el email ya se encuentra en uso");
	                   return "form";
	               }
	            }else{
	                model.addAttribute("danger","Error: el email ya se encuentra en uso");
	                return "form";
	            }
	        }
		/*
		usuarioService.save(usuario);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";*/
	}
	
	
	
	//Eliminar usuario
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			usuarioService.delete(id);
			flash.addFlashAttribute("success", "Usuario eliminado con éxito!");
		}
		return "redirect:/listar";
	}
	
	
	private boolean hasRole(String role) {
		SecurityContext context= SecurityContextHolder.getContext();
		if(context==null) {
			return false;
		}
		
		Authentication auth = context.getAuthentication();
		if(auth==null){
			return false;
		}
		
		Collection<? extends GrantedAuthority> authorities=auth.getAuthorities();
		
		return authorities.contains(new SimpleGrantedAuthority(role));
		
		/*for(GrantedAuthority authority: authorities) {
			if(role.equals(authority.getAuthority())) {
				logger.info("Hola ".concat(auth.getName()).concat(" tu rol es ".concat(authority.getAuthority())));
				return true;
			}
		}
		return false;*/
	}
	
}


	
