package com.fsm.dao;

import java.util.List;

import com.fsm.models.TypeOffre;

public interface IDAOTypeOffre {
	
	TypeOffre insert(TypeOffre type);
	TypeOffre update(TypeOffre type);
	void delete(TypeOffre type);
	TypeOffre getOneTypeOffre(int id);
	int getTypeOffreId(String type);
	List<TypeOffre> getAllTypeOffres();

}
