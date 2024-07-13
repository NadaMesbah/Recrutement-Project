package com.fsm.models;

public class Attente {
	private int id;
    private String description;

    public Attente(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public Attente(String description) {
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
		return "Attente [id=" + id + ", description=" + description + "]";
	}
}
