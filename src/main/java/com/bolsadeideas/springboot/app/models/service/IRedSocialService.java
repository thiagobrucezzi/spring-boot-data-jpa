package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.entity.RedSocial;

public interface IRedSocialService {
	
	public RedSocial create(RedSocial redSocial);
	
	public RedSocial getRedSocial(Long id);
	
	public void delete(Long id);
	
	public void save(RedSocial red);
}
