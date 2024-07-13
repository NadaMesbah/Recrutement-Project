package com.fsm.dao;

import java.util.List;

import com.fsm.models.Qualification;


public interface IDAOQualification {
	
	Qualification insert(String desc);
	Qualification update(Qualification q);
	void delete(Qualification q);
	Qualification getOneQualification(int id);
	int getQualificationId(String desc);
	List<Qualification> getAllQualifications();
	List<Qualification> getQualificationsOfOffer(int id);
}
