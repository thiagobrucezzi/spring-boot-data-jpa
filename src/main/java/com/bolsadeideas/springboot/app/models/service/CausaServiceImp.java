package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.ICausaDao;
import com.bolsadeideas.springboot.app.models.dao.ILlamadaTelefonicaDao;
import com.bolsadeideas.springboot.app.models.dao.IMovimientoBancarioDao;
import com.bolsadeideas.springboot.app.models.dao.IRedSocialDao;
import com.bolsadeideas.springboot.app.models.entity.Causa;
import com.bolsadeideas.springboot.app.models.entity.LlamadaTelefonica;
import com.bolsadeideas.springboot.app.models.entity.MovimientoBancario;
import com.bolsadeideas.springboot.app.models.entity.RedSocial;
@Service
public class CausaServiceImp implements ICausaService {
	
	@Autowired
	private ICausaDao causaDao;
	
	@Autowired
	private ILlamadaTelefonicaDao llamadaDao;
	
	@Autowired
	private IMovimientoBancarioDao movimientoBancarioDao;
	
	@Autowired
	private IRedSocialDao redSocialDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Causa> findAllByDesc() {
		return causaDao.findAllByDesc();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Causa> findAll(Pageable pageable) {
		return causaDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Causa causa) {
		causaDao.save(causa);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Causa findOne(Long id) {
		return causaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		causaDao.deleteById(id);
		
	}

	@Override
	public boolean save2(Causa causa) {
		if (causaDao.findById(causa.getId())==null){
			 if(causa.getId()!=null) {
	            	causa.setNumExpediente(causa.getNumExpediente());
	                causaDao.save(causa);
	                return true;
	            }
         	causa.setNumExpediente(causa.getNumExpediente());
			causaDao.save(causa);
            return true;
        }else{
            return false;
        }
	}

	@Override
	public Causa save3(Causa causa) {
		return causaDao.save(causa);
	}

	

	@Override
	public Page<Causa> findAll(Pageable pageable, String palabraClave) {
		return causaDao.findAll(pageable,palabraClave);
	}

	@Override
	public List<Causa> listAll(String palabraClave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarLlamada(LlamadaTelefonica llamada) {
		llamadaDao.save(llamada);
		
	}

	@Override
	public void guardarMov(MovimientoBancario movimiento) {
		movimientoBancarioDao.save(movimiento);
		
	}

	@Override
	public void guardarRed(RedSocial red) {
		redSocialDao.save(red);
		
	}



	


}
