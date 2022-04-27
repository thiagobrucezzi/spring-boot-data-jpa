package com.bolsadeideas.springboot.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.springboot.app.models.dao.ICausaDao;
import com.bolsadeideas.springboot.app.models.entity.Causa;
import com.bolsadeideas.springboot.app.models.entity.Informacion;

@Service
public class CausaServiceImp implements ICausaService {
	
	@Autowired
	private ICausaDao causaDao;
	
	
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
	public List<Informacion> listaInformaciones(Causa causa) {
		Causa c=causaDao.findById(causa.getId()).get();
		List<Informacion> historial=new ArrayList<>();
		historial=causa.getInformacion();
		return historial;
	}

	@Override
	public Causa update(Causa causa) {
		Causa c=causaDao.findById(causa.getId()).get();
		c.setEstado(causa.getEstado());
		return causaDao.save(c);
	}
	


}
