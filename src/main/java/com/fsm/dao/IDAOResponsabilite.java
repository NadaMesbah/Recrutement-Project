package com.fsm.dao;

import java.util.List;

import com.fsm.models.Responsabilite;

public interface IDAOResponsabilite {
	
	Responsabilite insert(String desc);
	Responsabilite update(Responsabilite r);
	void delete(Responsabilite r);
	Responsabilite getOneResponsabilite(int id);
	int getResponsabiliteId(String desc);
	List<Responsabilite> getAllResponsabilites();
	List<Responsabilite> getResponsabilitesOfOffer(int id);

}
