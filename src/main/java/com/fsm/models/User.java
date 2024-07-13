package com.fsm.models;
import java.io.Serializable;
//import java.util.HashSet;
//import java.util.Set;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String nom;
	private String prenom;
    private String username;
    private String password;
    private String role;
    private String email;
    private boolean isSubscribed; 
    
    public User(int id, String nom, String prenom, String username, String password, String role, String email, boolean isSubscribed) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.isSubscribed = isSubscribed;
    }

    public boolean getIsSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    
	public User(int id, String nom, String prenom, String username, String password, String role, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
	}
	
	public User(String nom, String prenom, String username, String password, String role, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
	}
	
	public User(String nom, String prenom, String username, String password, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", password="
				+ password + ", role=" + role + ", email=" + email + "subscribed=" +isSubscribed+ "]";
	}
    
}
