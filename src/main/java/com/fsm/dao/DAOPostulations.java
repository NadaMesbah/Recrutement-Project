package com.fsm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fsm.models.Offre;
import com.fsm.models.Postulation;
import com.fsm.models.User;
import com.fsm.util.Utilitaire;
import com.fsm.metier.DefaultServices;

public class DAOPostulations implements IDAOPostulations {

    private Connection conn;

    public DAOPostulations() {
        try {
            conn = Utilitaire.getInstance().getConnection();
            if (conn == null) {
                throw new SQLException("La connexion à la base de données n'a pas pu être établie.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Postulation insert(Postulation postulation) {
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        Postulation p = null;

        int id = 1;
        String cv = postulation.getCv();
        String lm = postulation.getLm();
        User demandeur = postulation.getDemandeur();
        Offre offre = postulation.getOffre();
        LocalDate localDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        try {
            String query = "INSERT INTO db_recrutement.postulation (cv, lm, date, demandeur_id, offre_id, status, candidate_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cv);
            preparedStatement.setString(2, lm);
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setInt(4, demandeur.getId());
            preparedStatement.setInt(5, offre.getId());
            preparedStatement.setString(6, "pending"); // Default status
            preparedStatement.setString(7, "new"); // Default candidate status

            int rowsAffected = preparedStatement.executeUpdate(); // Check if insertion was successful
            if (rowsAffected > 0) { // If insertion was successful
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys != null && generatedKeys.next()) {
                    id = generatedKeys.getInt(1); // Retrieve the generated ID
                }
            }

            // Set the ID of the postulation
            postulation.setId(id);
            // Create a new Postulation object with the retrieved ID and other attributes
            p = new Postulation(id, cv, lm, demandeur, offre);
            p.setDate(localDate); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return p;
    }

    @Override
    public Postulation update(Postulation postulation) {
        PreparedStatement preparedStatement = null;
        Postulation p = null;
        try {
            p = getOnePostulation(postulation.getId());

            if (p != null) {
                String query = "UPDATE db_recrutement.postulation SET cv=?, lm=?, date=?, demandeur_id=?, offre_id=?, status=?, interview_date=?, interview_time=?, interview_location=?, interview_outcome=?, candidate_status=? WHERE id=?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, postulation.getCv());
                preparedStatement.setString(2, postulation.getLm());
                LocalDate localDate = postulation.getDate(); // Retrieve LocalDate
                java.sql.Date sqlDate = java.sql.Date.valueOf(localDate); // Convert LocalDate to java.sql.Date
                preparedStatement.setDate(3, sqlDate);
                preparedStatement.setInt(4, postulation.getDemandeur().getId());
                preparedStatement.setInt(5, postulation.getOffre().getId());
                preparedStatement.setString(6, postulation.getStatus());
                preparedStatement.setDate(7, postulation.getInterviewDate() != null ? java.sql.Date.valueOf(postulation.getInterviewDate()) : null);
                preparedStatement.setTime(8, postulation.getInterviewTime() != null ? java.sql.Time.valueOf(postulation.getInterviewTime()) : null);
                preparedStatement.setString(9, postulation.getInterviewLocation());
                preparedStatement.setString(10, postulation.getInterviewOutcome());
                preparedStatement.setString(11, postulation.getCandidateStatus());
                preparedStatement.setInt(12, postulation.getId());

                preparedStatement.executeUpdate();

                p.setCv(postulation.getCv());
                p.setLm(postulation.getLm());
                p.setDate(localDate);
                p.setDemandeur(postulation.getDemandeur());
                p.setOffre(postulation.getOffre());
                p.setStatus(postulation.getStatus());
                p.setInterviewDate(postulation.getInterviewDate());
                p.setInterviewTime(postulation.getInterviewTime());
                p.setInterviewLocation(postulation.getInterviewLocation());
                p.setInterviewOutcome(postulation.getInterviewOutcome());
                p.setCandidateStatus(postulation.getCandidateStatus());

                System.out.println(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return p;
    }


    @Override
    public void delete(Postulation postulation) {
        String query = "DELETE FROM db_recrutement.postulation WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, postulation.getId());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Postulation getOnePostulation(int id) {
        Postulation postulation = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.postulation WHERE id=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String cv = resultSet.getString("cv");
                String lm = resultSet.getString("lm");
                LocalDate localDate = resultSet.getDate("date").toLocalDate(); // Convert to LocalDate
                int demandeur_id = resultSet.getInt("demandeur_id");
                int offre_id = resultSet.getInt("offre_id");
                String status = resultSet.getString("status");
                LocalDate interviewDate = resultSet.getDate("interview_date") != null ? resultSet.getDate("interview_date").toLocalDate() : null;
                LocalTime interviewTime = resultSet.getTime("interview_time") != null ? resultSet.getTime("interview_time").toLocalTime() : null;
                String interviewLocation = resultSet.getString("interview_location");
                String interviewOutcome = resultSet.getString("interview_outcome");
                String candidateStatus = resultSet.getString("candidate_status");

                User demandeur = DefaultServices.getInstance().getUserById(demandeur_id);
                Offre offre = DefaultServices.getInstance().getOneOffre(offre_id);

                postulation = new Postulation(id, cv, lm, demandeur, offre, status, interviewDate, interviewTime, interviewLocation, interviewOutcome, candidateStatus);
                postulation.setDate(localDate); // Set LocalDate
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return postulation;
    }

    @Override
    public List<Postulation> getAllPostulations() {
        List<Postulation> postulations = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.postulation";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cv = resultSet.getString("cv");
                String lm = resultSet.getString("lm");
                LocalDate localDate = resultSet.getDate("date").toLocalDate(); // Convert to LocalDate
                int demandeur_id = resultSet.getInt("demandeur_id");
                int offre_id = resultSet.getInt("offre_id");
                String status = resultSet.getString("status");
                LocalDate interviewDate = resultSet.getDate("interview_date") != null ? resultSet.getDate("interview_date").toLocalDate() : null;
                LocalTime interviewTime = resultSet.getTime("interview_time") != null ? resultSet.getTime("interview_time").toLocalTime() : null;
                String interviewLocation = resultSet.getString("interview_location");
                String interviewOutcome = resultSet.getString("interview_outcome");
                String candidateStatus = resultSet.getString("candidate_status");

                User demandeur = DefaultServices.getInstance().getUserById(demandeur_id);
                Offre offre = DefaultServices.getInstance().getOneOffre(offre_id);

                Postulation postulation = new Postulation(id, cv, lm, demandeur, offre, status, interviewDate, interviewTime, interviewLocation, interviewOutcome, candidateStatus);
                postulation.setDate(localDate); // Set LocalDate
                postulations.add(postulation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return postulations;
    }

    // Other methods remain unchanged

    @Override
    public List<Postulation> getPostulationsOf(User user) {
        List<Postulation> postulations = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.postulation WHERE demandeur_id=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cv = resultSet.getString("cv");
                String lm = resultSet.getString("lm");
                LocalDate localDate = resultSet.getDate("date").toLocalDate(); // Convert to LocalDate
                int offre_id = resultSet.getInt("offre_id");
                String status = resultSet.getString("status");
                LocalDate interviewDate = resultSet.getDate("interview_date") != null ? resultSet.getDate("interview_date").toLocalDate() : null;
                LocalTime interviewTime = resultSet.getTime("interview_time") != null ? resultSet.getTime("interview_time").toLocalTime() : null;
                String interviewLocation = resultSet.getString("interview_location");
                String interviewOutcome = resultSet.getString("interview_outcome");
                String candidateStatus = resultSet.getString("candidate_status");

                Offre offre = DefaultServices.getInstance().getOneOffre(offre_id);

                Postulation postulation = new Postulation(id, cv, lm, user, offre, status, interviewDate, interviewTime, interviewLocation, interviewOutcome, candidateStatus);
                postulation.setDate(localDate); // Set LocalDate
                postulations.add(postulation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return postulations;
    }

    @Override
    public List<Postulation> getPostulationsOfOffer(int id) {
        List<Postulation> postulations = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.postulation WHERE offre_id=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int postulationId = resultSet.getInt("id"); // Retrieve the ID from the result set
                String cv = resultSet.getString("cv");
                String lm = resultSet.getString("lm");
                LocalDate localDate = resultSet.getDate("date").toLocalDate(); // Convert to LocalDate
                int demandeur_id = resultSet.getInt("demandeur_id");
                String status = resultSet.getString("status");
                LocalDate interviewDate = resultSet.getDate("interview_date") != null ? resultSet.getDate("interview_date").toLocalDate() : null;
                LocalTime interviewTime = resultSet.getTime("interview_time") != null ? resultSet.getTime("interview_time").toLocalTime() : null;
                String interviewLocation = resultSet.getString("interview_location");
                String interviewOutcome = resultSet.getString("interview_outcome");
                String candidateStatus = resultSet.getString("candidate_status");

                Offre offre = DefaultServices.getInstance().getOneOffre(id);
                User demandeur = DefaultServices.getInstance().getUserById(demandeur_id);

                Postulation postulation = new Postulation(postulationId, cv, lm, demandeur, offre, status, interviewDate, interviewTime, interviewLocation, interviewOutcome, candidateStatus);
                postulation.setDate(localDate); // Set LocalDate
                postulations.add(postulation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return postulations;
    }


	@Override
	public Postulation updateStatus(Postulation postulation,String status) {
		// TODO Auto-generated method stub
		//UPDATE `db_recrutement`.`postulation` SET status = 'accepted' WHERE id = 1;
		PreparedStatement preparedStatement = null;
        Postulation p = null;
        try {
            p = getOnePostulation(postulation.getId());

            if (p != null) {
                String query = "UPDATE db_recrutement.postulation SET status=? WHERE id=?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, postulation.getId());

                preparedStatement.executeUpdate();

                p.setStatus(status);
               
                System.out.println(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return p;
	}
	
	@Override
	public Postulation scheduleInterview(Postulation postulation, LocalDate interviewDate, LocalTime interviewTime, String interviewLocation) {
	    PreparedStatement preparedStatement = null;
	    try {
	        String query = "UPDATE db_recrutement.postulation SET interview_date=?, interview_time=?, interview_location=? WHERE id=?";
	        preparedStatement = conn.prepareStatement(query);
	        preparedStatement.setDate(1, java.sql.Date.valueOf(interviewDate));
	        preparedStatement.setTime(2, java.sql.Time.valueOf(interviewTime));
	        preparedStatement.setString(3, interviewLocation);
	        preparedStatement.setInt(4, postulation.getId());

	        preparedStatement.executeUpdate();

	        postulation.setInterviewDate(interviewDate);
	        postulation.setInterviewTime(interviewTime);
	        postulation.setInterviewLocation(interviewLocation);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return postulation;
	}

	
}
