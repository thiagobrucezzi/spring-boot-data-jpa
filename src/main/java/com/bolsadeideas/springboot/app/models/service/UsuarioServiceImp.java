package com.bolsadeideas.springboot.app.models.service;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Usuario;

@Service
public class UsuarioServiceImp  implements IUsuarioService, UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao; 
	
	private Logger logger= LoggerFactory.getLogger(UsuarioServiceImp.class);
	
	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findOne(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario= usuarioDao.findByEmail(email);
		
		if(usuario==null) {
			logger.error("Error: no existe el usuario '" + email + "'");
			throw new UsernameNotFoundException("Username "+ email + " no existe en el sistema!");
		}
		
		/*List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(rol-> new SimpleGrantedAuthority(rol.getAuthority().toString()))
				.collect(Collectors.toList());
			*/
		
		List<GrantedAuthority> authorities= new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(usuario.getRoles()));
		
		if(authorities.isEmpty()) {
			logger.error("Error: usuario '" + email + "' no tiene roles asignados!");
			throw new UsernameNotFoundException("Error: usuario '" + email + "' no tiene roles asignados!");
		}
		
		/*for(Role role: usuario.getRoles()) {
			logger.info("Role: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		if(authorities.isEmpty()) {
			logger.error("Error: usuario '" + email + "' no tiene roles asignados!");
			throw new UsernameNotFoundException("Error: usuario '" + email + "' no tiene roles asignados!");
		}
		*/
		return new User(email, usuario.getPassword(), true, true, true, true, authorities);
	}

	@Override
	public boolean save2(Usuario usuario) {
		if (usuarioDao.findByEmail(usuario.getEmail())==null){
            if(usuario.getId()!=null) {
            	usuario.setPassword(usuario.getPassword());
                usuarioDao.save(usuario);
                return true;
            }
            usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
            usuarioDao.save(usuario);
            return true;
        }else{
            return false;
        }
	}


}
