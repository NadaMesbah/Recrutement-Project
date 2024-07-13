package com.fsm.metier;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fsm.dao.DAOAttente;
import com.fsm.dao.DAOPostulations;
import com.fsm.dao.DAOQualification;
import com.fsm.dao.DAOResponsabilite;
import com.fsm.dao.DAOTypeOffre;
import com.fsm.dao.DAOUser;
import com.fsm.dao.IDAOAttente;
import com.fsm.dao.IDAOOffre;
import com.fsm.dao.IDAOPostulations;
import com.fsm.dao.IDAOQualification;
import com.fsm.dao.IDAOResponsabilite;
import com.fsm.dao.IDAOTypeOffre;
import com.fsm.dao.IDAOUser;
import com.fsm.dao.NewDAOOffre;
import com.fsm.models.Attente;
import com.fsm.models.Offre;
import com.fsm.models.Postulation;
import com.fsm.models.Qualification;
import com.fsm.models.Responsabilite;
import com.fsm.models.TypeOffre;
import com.fsm.models.User;

public class DefaultServices implements Services{
	
	private IDAOUser userDao;
	private IDAOOffre offreDao;
	private IDAOTypeOffre typeOffreDao;
	private IDAOPostulations postulationDao;
	private IDAOQualification qualificationDao;
	private IDAOAttente attenteDao;
	private IDAOResponsabilite responsabiliteDao;
	
	private DefaultServices(IDAOUser userDao, IDAOOffre offreDao, IDAOTypeOffre typeOffreDao,IDAOPostulations postulationDao, IDAOQualification qualificationDao, IDAOResponsabilite responsabiliteDao, IDAOAttente attenteDao ) {
		this.userDao=userDao;
		this.offreDao = offreDao;
		this.typeOffreDao = typeOffreDao;
		this.postulationDao = postulationDao;
		this.qualificationDao = qualificationDao;
		this.attenteDao = attenteDao;
		this.responsabiliteDao = responsabiliteDao;
	}
	public static DefaultServices instance = null;
	 
	public static DefaultServices getInstance() {
		if(instance==null)
			instance=new DefaultServices(new DAOUser(), new NewDAOOffre(), new DAOTypeOffre(), new DAOPostulations(), new DAOQualification(), new DAOResponsabilite(), new DAOAttente());
		return instance;
	}

	//User 
	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		User currentUser = userDao.getOneUser(user.getUsername());
		if (currentUser==null) {
			currentUser =userDao.insert(user);
			return currentUser;
		}
		return null;
	}

	@Override
	public User login(String username, String password) {
		User currentUser = userDao.getOneUser(username);
		if (currentUser!=null) {
			if (currentUser.getPassword().equals(password)) {
				return currentUser;
			}
		}
		return null;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return userDao.getOneUser(username);
	}
	
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}
	

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.getAllUsers();
	}
	
	public List<String> getAllUserEmails() {
        List<User> users = getAllUsers();
        return users.stream().map(User::getEmail).collect(Collectors.toList());
    }
	
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.getUserByEmail(email);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}
	@Override
	public List<User> getAllSubscribedUsers() {
		return userDao.getAllSubscribedUsers();
	}
	
	//Offres 

	@Override
	public Offre addOffre(Offre offre) {
		// TODO Auto-generated method stub
		return offreDao.insert(offre);
	}

	@Override
	public Offre updateOffre(Offre offre) {
		// TODO Auto-generated method stub
		return offreDao.update(offre);
	}

	@Override
	public void deleteOffre(Offre offre) {
		// TODO Auto-generated method stub
		offreDao.delete(offre);
	}

	@Override
	public Offre getOneOffre(int id) {
		// TODO Auto-generated method stub
		return offreDao.getOneOffre(id);
	}

	@Override
	public List<Offre> getAllOffres() {
		// TODO Auto-generated method stub
		return offreDao.getAllOffres();
	}

	@Override
	public List<Offre> getOffresofUser(User user) {
		// TODO Auto-generated method stub
		return offreDao.getOffresOf(user);
	}

	//TypeOffre
	
	@Override
	public TypeOffre addTypeOffre(TypeOffre p) {
		// TODO Auto-generated method stub
		return typeOffreDao.insert(p);
	}

	@Override
	public TypeOffre updateTypeOffre(TypeOffre p) {
		// TODO Auto-generated method stub
		return typeOffreDao.update(p);
	}

	@Override
	public void deleteTypeOffre(TypeOffre p) {
		// TODO Auto-generated method stub
		typeOffreDao.delete(p);
	}

	@Override
	public TypeOffre getOneTypeOffre(int id) {
		// TODO Auto-generated method stub
		return typeOffreDao.getOneTypeOffre(id);
	}

	@Override
	public List<TypeOffre> getAllTypeOffres() {
		// TODO Auto-generated method stub
		return typeOffreDao.getAllTypeOffres();
	}
	
	@Override
	public int getTypeOffreId(String type) {
		// TODO Auto-generated method stub
		return typeOffreDao.getTypeOffreId(type);
	}

	//Postulation 
	
	@Override
	public Postulation addPostulation(Postulation p) {
		// TODO Auto-generated method stub
		return postulationDao.insert(p);
	}

	@Override
	public Postulation updatePostulation(Postulation p) {
		// TODO Auto-generated method stub
		return postulationDao.update(p);
	}

	@Override
	public void deletePostulation(Postulation p) {
		// TODO Auto-generated method stub
		postulationDao.delete(p);
	}

	@Override
	public Postulation getOnePostulation(int id) {
		// TODO Auto-generated method stub
		return postulationDao.getOnePostulation(id);
	}

	@Override
	public List<Postulation> getAllPostulations() {
		// TODO Auto-generated method stub
		return postulationDao.getAllPostulations();
	}

	@Override
	public List<Postulation> getPostulationsofUser(User user) {
		// TODO Auto-generated method stub
		return postulationDao.getPostulationsOf(user);
	}

	@Override
	public List<Postulation> getPostulationsOfOffer(int id) {
		// TODO Auto-generated method stub
		return postulationDao.getPostulationsOfOffer(id);
	}

	@Override
	public Postulation updatePostulationStatus(Postulation p, String status) {
		// TODO Auto-generated method stub
		return postulationDao.updateStatus(p, status);
	}
	
	@Override
	public Postulation scheduleInterview(Postulation postulation, LocalDate interviewDate, LocalTime interviewTime, String interviewLocation) {
		return postulationDao.scheduleInterview(postulation, interviewDate, interviewTime, interviewLocation);
	}

	@Override
	public Responsabilite addResponsabilite(String desc) {
		// TODO Auto-generated method stub
		return responsabiliteDao.insert(desc);
	}

	@Override
	public Responsabilite updateResponsabilite(Responsabilite r) {
		// TODO Auto-generated method stub
		return responsabiliteDao.update(r);
	}

	@Override
	public void deleteResponsabilite(Responsabilite r) {
		// TODO Auto-generated method stub
		responsabiliteDao.delete(r);
	}

	@Override
	public Responsabilite getOneResponsabilite(int id) {
		// TODO Auto-generated method stub
		return responsabiliteDao.getOneResponsabilite(id);
	}

	@Override
	public int getResponsabiliteId(String desc) {
		// TODO Auto-generated method stub
		return responsabiliteDao.getResponsabiliteId(desc);
	}

	@Override
	public List<Responsabilite> getAllResponsabilites() {
		// TODO Auto-generated method stub
		return responsabiliteDao.getAllResponsabilites();
	}

	@Override
	public Attente addAttente(String desc) {
		// TODO Auto-generated method stub
		return attenteDao.insert(desc);
	}

	@Override
	public Attente updateAttente(Attente a) {
		// TODO Auto-generated method stub
		return attenteDao.update(a);
	}

	@Override
	public void deleteAttente(Attente a) {
		// TODO Auto-generated method stub
		attenteDao.delete(a);
	}

	@Override
	public Attente getOneAttente(int id) {
		// TODO Auto-generated method stub
		return attenteDao.getOneAttente(id);
	}

	@Override
	public int getAttenteId(String desc) {
		// TODO Auto-generated method stub
		return attenteDao.getAttenteId(desc);
	}

	@Override
	public List<Attente> getAllAttentes() {
		// TODO Auto-generated method stub
		return attenteDao.getAllAttentes();
	}

	@Override
	public Qualification addQualification(String desc) {
		// TODO Auto-generated method stub
		return qualificationDao.insert(desc);
	}

	@Override
	public Qualification updateQualification(Qualification a) {
		// TODO Auto-generated method stub
		return qualificationDao.update(a);
	}

	@Override
	public void deleteQualification(Qualification a) {
		// TODO Auto-generated method stub
		qualificationDao.delete(a);
	}

	@Override
	public Qualification getOneQualification(int id) {
		// TODO Auto-generated method stub
		return qualificationDao.getOneQualification(id);
	}

	@Override
	public int getQualificationId(String desc) {
		// TODO Auto-generated method stub
		return qualificationDao.getQualificationId(desc);
	}

	@Override
	public List<Qualification> getAllQualifications() {
		// TODO Auto-generated method stub
		return qualificationDao.getAllQualifications();
	}

	@Override
	public List<Responsabilite> getResponsabilitesOfOffer(int id) {
		// TODO Auto-generated method stub
		return responsabiliteDao.getResponsabilitesOfOffer(id);
	}

	@Override
	public List<Attente> getAttentesOfOffer(int id) {
		// TODO Auto-generated method stub
		return attenteDao.getAttentesOfOffer(id);
	}

	@Override
	public List<Qualification> getQualificationsOfOffer(int id) {
		// TODO Auto-generated method stub
		return qualificationDao.getQualificationsOfOffer(id);
	}


}
