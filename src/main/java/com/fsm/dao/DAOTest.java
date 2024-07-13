package com.fsm.dao;

import java.util.ArrayList;
import java.util.List;

import com.fsm.metier.DefaultServices;
import com.fsm.models.*;

public class DAOTest {
	public static DAOPostulations DaoPostulation = new DAOPostulations();
	public static DAOAttente DaoAttente = new DAOAttente();
	public static NewDAOOffre DaoOffre = new NewDAOOffre();
	public static DAOUser DaoUser = new DAOUser();
	//public static List<Filiere> filieres = Dao.listeFiliere();
	
	
	
	public static Postulation p;
	
	public static void main(String[] args) {
//		List<Attente> attentes = new ArrayList<Attente>();
//		attentes = DaoAttente.getAttentesOfOffer(1);
		
		List<Offre> offres = new ArrayList<Offre>();
		offres = DaoOffre.getAllOffres();
		//postulation.setStatus("canceled");
		
		//Postulation updated = DaoPostulation.update(postulation);
//		
////		System.out.println(postulation);
//		Postulation updated = DaoPostulation.updateStatus(postulation, "pending");
		
		//System.out.println(postulation.getStatus());
		
		//System.out.println("------------------------------------");
		
		//System.out.println(updated.getStatus());
		
		System.out.println(offres);
		
//		Offre offre = DaoOffre.getOneOffre(10);
//		User demandeur = DaoUser.getUserById(3);
//		// TODO Auto-generated method stub
//		p = new Postulation("cv_nada.pdf", "lm_nada.pdf", demandeur, offre);
//		//maintenant je change la ville de l'étudiant pour tester updateEtudiant
//		p.setStatus("accepted");
//		
//		System.out.println(p);
//		//execution de updateEtudiant
//		Dao.updateEtudiant(et);
	}

}
