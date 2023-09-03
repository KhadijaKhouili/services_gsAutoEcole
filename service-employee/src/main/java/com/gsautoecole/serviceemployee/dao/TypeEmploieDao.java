package com.gsautoecole.serviceemployee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsautoecole.serviceemployee.Entities.TypeEmploie;


public interface TypeEmploieDao extends JpaRepository<TypeEmploie, Long>{
	
	public TypeEmploie findByTpemploie(String tpemploie);
}
