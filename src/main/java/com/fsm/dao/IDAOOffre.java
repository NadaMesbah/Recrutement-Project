package com.fsm.dao;

import java.util.List;

import com.fsm.models.Offre;
import com.fsm.models.User;

public interface IDAOOffre {
	
	Offre insert(Offre offre);
	Offre update(Offre offre);
	void delete(Offre offre);
	Offre getOneOffre(int id);
	List<Offre> getAllOffres();
	List<Offre> getOffresOf(User user);

}
