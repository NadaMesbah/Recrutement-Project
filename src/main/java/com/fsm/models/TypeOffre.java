package com.fsm.models;

public class TypeOffre {

	private int id;
	private String libelle;
	
	
	public TypeOffre(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	public TypeOffre(String libelle) {
		super();
		this.libelle = libelle;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "TypeOffre [id=" + id + ", libelle=" + libelle + "]";
	}
	
	//ajouter attribut des offres pour retourner une set des offres appartenant a chaque type
	
	
}
