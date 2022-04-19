package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.RedSocial;

public interface IRedSocialDao extends PagingAndSortingRepository<RedSocial, Long> {

}
