package com.fsm.models;

public class Qualification {
	private int id;
    private String description;


    public Qualification(int id, String description) {
		this.id = id;
		this.description = description;
	}

    
	public Qualification(String description) {
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
		return "Qualification [id=" + id + ", description=" + description + "]";
	}

}
