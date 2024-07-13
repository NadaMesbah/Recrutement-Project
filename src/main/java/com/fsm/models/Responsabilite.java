package com.fsm.models;

public class Responsabilite {
	private int id;
    private String description;

    public Responsabilite(int id,String description) {
    	this.id = id;
        this.description = description;
    }
    
    public Responsabilite(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Responsabilite [id=" + id + ", description=" + description + "]";
	}
    
}