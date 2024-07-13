package com.fsm.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fsm.models.Postulation;
import com.fsm.models.User;

public interface IDAOPostulations {
	
	Postulation insert(Postulation postulation);
	Postulation update(Postulation postulation);
	Postulation updateStatus(Postulation postulation, String status);
	void delete(Postulation postulation);
	Postulation getOnePostulation(int id);
	List<Postulation> getAllPostulations();
	List<Postulation> getPostulationsOf(User user);
	List<Postulation> getPostulationsOfOffer(int id);
	Postulation scheduleInterview(Postulation postulation, LocalDate interviewDate, LocalTime interviewTime, String interviewLocation) ;
}
