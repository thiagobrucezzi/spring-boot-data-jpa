package com.bolsadeideas.springboot.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bolsadeideas.springboot.app.models.dao.IRedSocialDao;
import com.bolsadeideas.springboot.app.models.entity.RedSocial;

@Service
public class RedSocialImp implements IRedSocialService {
	
	
	@Autowired
	private IRedSocialDao redSocialDao;
	
	@Override
	public RedSocial create(RedSocial redSocial) {
		redSocial=redSocialDao.save(redSocial);
		return redSocial;
	}

	@Override
	public RedSocial getRedSocial(Long id) {
		return redSocialDao.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		redSocialDao.deleteById(id);
		
	}

	@Override
	public void save(RedSocial red) {
		redSocialDao.save(red);
		
	}

}
