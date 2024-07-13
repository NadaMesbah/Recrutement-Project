package com.fsm.metier;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fsm.models.Attente;
import com.fsm.models.Offre;
import com.fsm.models.Postulation;
import com.fsm.models.Qualification;
import com.fsm.models.Responsabilite;
import com.fsm.models.TypeOffre;
import com.fsm.models.User;


public interface Services {
	
		//al user 
	
		User register(User user);
		User login(String username, String password);
		User getUser(String username);
		User getUserById(int id);
		List<User> getAllUsers();
		User getUserByEmail(String email);
		void updateUser(User user);
		List<User> getAllSubscribedUsers();
	
	    // al offre

		Offre addOffre(Offre offre);
		Offre updateOffre(Offre offre);
		void deleteOffre(Offre offre);
		Offre getOneOffre(int id);
		List<Offre> getAllOffres();
		List<Offre> getOffresofUser(User user);
		
		// al type offre
		
		TypeOffre addTypeOffre(TypeOffre p);
		TypeOffre updateTypeOffre(TypeOffre p);
		void deleteTypeOffre(TypeOffre p);
		TypeOffre getOneTypeOffre(int id);
		int getTypeOffreId(String type);
		List<TypeOffre> getAllTypeOffres();
		
		// al Responsabilite
		
		Responsabilite addResponsabilite(String desc);
		Responsabilite updateResponsabilite(Responsabilite r);
		void deleteResponsabilite(Responsabilite r);
		Responsabilite getOneResponsabilite(int id);
		int getResponsabiliteId(String desc);
		List<Responsabilite> getAllResponsabilites();
		List<Responsabilite> getResponsabilitesOfOffer(int id);
		
		// al Attente
		Attente addAttente(String desc);
		Attente updateAttente(Attente a);
		void deleteAttente(Attente a);
		Attente getOneAttente(int id);
		int getAttenteId(String desc);
		List<Attente> getAllAttentes();
		List<Attente> getAttentesOfOffer(int id);
		
		
		// al Qualification
		
		Qualification addQualification(String desc);
		Qualification updateQualification(Qualification a);
		void deleteQualification(Qualification a);
		Qualification getOneQualification(int id);
		int getQualificationId(String desc);
		List<Qualification> getAllQualifications();
		List<Qualification> getQualificationsOfOffer(int id);
		
		// al postulation
		
		Postulation addPostulation(Postulation p);
		Postulation updatePostulation(Postulation p);
		Postulation updatePostulationStatus(Postulation p, String status);
		void deletePostulation(Postulation p);
		Postulation getOnePostulation(int id);
		List<Postulation> getAllPostulations();
		List<Postulation> getPostulationsofUser(User user);
		List<Postulation> getPostulationsOfOffer(int id);
		Postulation scheduleInterview(Postulation postulation, LocalDate interviewDate, LocalTime interviewTime, String interviewLocation);
}

