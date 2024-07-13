package com.fsm.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Postulation {

	private int id;
	private LocalDate date;
	private String cv;
	private String lm;
	private User demandeur;
	private Offre offre;
	private String status;
	private LocalDate interviewDate;
	private LocalTime interviewTime;
	private String interviewLocation;
	private String interviewOutcome;
	private String candidateStatus;

	// getters and setters
	public LocalDate getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDate interviewDate) {
		this.interviewDate = interviewDate;
	}

	public LocalTime getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(LocalTime interviewTime) {
		this.interviewTime = interviewTime;
	}

	public String getInterviewLocation() {
		return interviewLocation;
	}

	public void setInterviewLocation(String interviewLocation) {
		this.interviewLocation = interviewLocation;
	}

	public String getInterviewOutcome() {
		return interviewOutcome;
	}

	public void setInterviewOutcome(String interviewOutcome) {
		this.interviewOutcome = interviewOutcome;
	}

	public String getCandidateStatus() {
		return candidateStatus;
	}

	public void setCandidateStatus(String candidateStatus) {
		this.candidateStatus = candidateStatus;
	}

	public Postulation() {
		this.date = LocalDate.now();
	}

	public Postulation(int id, String cv, String lm, User demandeur, Offre offre) {
		this.id = id;
		this.cv = cv;
		this.lm = lm;
		this.date = LocalDate.now();
		this.demandeur = demandeur;
		this.offre = offre;
		this.status = "pending";
	}

	public Postulation(int id, String cv, String lm, User demandeur, Offre offre, String status) {
		this.id = id;
		this.cv = cv;
		this.lm = lm;
		this.date = LocalDate.now();
		this.demandeur = demandeur;
		this.offre = offre;
		this.status = status;
	}

	public Postulation(String cv, String lm, User demandeur, Offre offre) {
		this.cv = cv;
		this.lm = lm;
		this.date = LocalDate.now();
		this.demandeur = demandeur;
		this.offre = offre;
		this.status = "pending";
	}

	public Postulation(String cv, String lm, User demandeur, Offre offre, String status) {
		this.cv = cv;
		this.lm = lm;
		this.date = LocalDate.now();
		this.demandeur = demandeur;
		this.offre = offre;
		this.status = status;
	}

	// Updated constructor with interview details and outcomes
    public Postulation(int id, String cv, String lm, User demandeur, Offre offre, String status, LocalDate interviewDate, LocalTime interviewTime, String interviewLocation, String interviewOutcome, String candidateStatus) {
        this.id = id;
        this.cv = cv;
        this.lm = lm;
        this.demandeur = demandeur;
        this.offre = offre;
        this.status = status;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
        this.interviewLocation = interviewLocation;
        this.interviewOutcome = interviewOutcome;
        this.candidateStatus = candidateStatus;
    }

	public Postulation(String cv, String lm) {
		super();
		this.cv = cv;
		this.lm = lm;
		this.date = LocalDate.now();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getLm() {
		return lm;
	}

	public void setLm(String lm) {
		this.lm = lm;
	}

	public User getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(User demandeur) {
		this.demandeur = demandeur;
	}

	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	@Override
	public String toString() {
		return "Postulation [id=" + id + ", date=" + date + ", cv=" + cv + ", lm=" + lm + ", demandeur=" + demandeur
				+ ", offre=" + offre + ", status=" + status + "]";
	}

}
