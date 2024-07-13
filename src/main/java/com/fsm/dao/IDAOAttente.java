package com.fsm.dao;

import java.util.List;

import com.fsm.models.Attente;


public interface IDAOAttente {
	Attente insert(String desc);
	Attente update(Attente a);
	void delete(Attente a);
	Attente getOneAttente(int id);
	int getAttenteId(String desc);
	List<Attente> getAllAttentes();
	List<Attente> getAttentesOfOffer(int id);
}
