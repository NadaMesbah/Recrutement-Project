package com.fsm.models;

import java.util.ArrayList;
import java.util.List;

public class Offre {
	
	private int id;
	private String titre;
	private String description;
	private User createur;
	private TypeOffre type;
	private List<Responsabilite> responsabilites;
	private List<Qualification> qualifications;
	private List<Attente> attentes;
	

    // Assurez-vous que les getters retournent des listes initialisées
    public List<Responsabilite> getResponsabilites() {
        if (responsabilites == null) {
            responsabilites = new ArrayList<>();
        }
        return responsabilites;
    }

    public List<Qualification> getQualifications() {
        if (qualifications == null) {
            qualifications = new ArrayList<>();
        }
        return qualifications;
    }

    public List<Attente> getAttentes() {
        if (attentes == null) {
            attentes = new ArrayList<>();
        }
        return attentes;
    }

    // Assurez-vous aussi que les setters initialisent les listes si nécessaire
    public void setResponsabilites(List<Responsabilite> responsabilites) {
        if (responsabilites == null) {
            this.responsabilites = new ArrayList<>();
        } else {
            this.responsabilites = responsabilites;
        }
    }

    public void setQualifications(List<Qualification> qualifications) {
        if (qualifications == null) {
            this.qualifications = new ArrayList<>();
        } else {
            this.qualifications = qualifications;
        }
    }

    public void setAttentes(List<Attente> attentes) {
        if (attentes == null) {
            this.attentes = new ArrayList<>();
        } else {
            this.attentes = attentes;
        }
    }
	
	
	public Offre(String titre, String description, User createur, TypeOffre type) {
		super();
		this.titre = titre;
		this.description = description;
		this.createur = createur;
		this.type = type;
	}
	
	public Offre(String titre, String description, User createur, TypeOffre type, List<Responsabilite> responsabilites, List<Qualification> qualifications, List<Attente> attentes) {
        this.titre = titre;
        this.description = description;
        this.createur = createur;
        this.type = type;
        this.responsabilites = (responsabilites != null) ? responsabilites : new ArrayList<>();
        this.qualifications = (qualifications != null) ? qualifications : new ArrayList<>();
        this.attentes = (attentes != null) ? attentes : new ArrayList<>();
    }
	
	public Offre(int id,String titre, String description, User createur, TypeOffre type, List<Responsabilite> responsabilites, List<Qualification> qualifications, List<Attente> attentes) {
        this.id = id;
		this.titre = titre;
        this.description = description;
        this.createur = createur;
        this.type = type;
        this.responsabilites = responsabilites;
        this.qualifications = qualifications;
        this.attentes = attentes;
    }
	
	public Offre(int id, String titre, String description, TypeOffre type, List<Responsabilite> responsabilites, List<Qualification> qualifications, List<Attente> attentes) {
        this.id = id;
		this.titre = titre;
        this.description = description;
        this.type = type;
        this.responsabilites = responsabilites;
        this.qualifications = qualifications;
        this.attentes = attentes;
    }
	
	public Offre(int id, String titre, String description, User createur, TypeOffre type) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.createur = createur;
		this.type = type;
	}
	
	public Offre(int id, String titre, String description, TypeOffre type) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.type = type;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreateur() {
		return createur;
	}

	public void setCreateur(User createur) {
		this.createur = createur;
	}

	public TypeOffre getType() {
		return type;
	}

	public void setType(TypeOffre type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Offre [id=" + id + ", titre=" + titre + ", description=" + description + ", createur=" + createur
				+ ", type=" + type + ", responsabilites=" + responsabilites + ", qualifications=" + qualifications
				+ ", attentes=" + attentes + "]";
	}

}
