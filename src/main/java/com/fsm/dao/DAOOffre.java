package com.fsm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fsm.metier.DefaultServices;
import com.fsm.models.Offre;
import com.fsm.models.TypeOffre;
import com.fsm.models.User;
import com.fsm.util.Utilitaire;


public class DAOOffre implements IDAOOffre{
	
	Offre offre;
	Connection conn;
	ResultSet res;

	public DAOOffre() {
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
	public Offre insert(Offre offre) {
	    PreparedStatement preparedStatement = null;
	    ResultSet generatedKeys = null;
	    Offre o = null;
	    
	    int id = 0;
	    String titre = offre.getTitre();
	    String description = offre.getDescription();
	    User createur = offre.getCreateur();
	    TypeOffre type = offre.getType();

	    try {
	        // Insert student information
	        String query = "INSERT INTO db_recrutement.offre (titre, description, createur_id, type_id) VALUES (?, ?, ?, ?)";
	        preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, titre);
	        preparedStatement.setString(2, description);
	        preparedStatement.setInt(3, createur.getId());
	        preparedStatement.setInt(4, type.getId());

	        // Execute the query and retrieve the generated keys
	        preparedStatement.executeUpdate();
	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys != null && generatedKeys.next()) {
	            id = generatedKeys.getInt(1);
	        }
	        offre.setId(id);
	        o = new Offre(id,titre,description,createur,type);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
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
	    return o;
	}

	@Override
	public Offre update(Offre offre) {
		PreparedStatement preparedStatement = null;
		Offre o = null;
        try {
            // Retrieve the offer from the database using the getOneOffre method
        	o = getOneOffre(offre.getId());

            // If the offer exists, update its details
            if (offre != null) {
                String query = "UPDATE db_recrutement.offre SET titre=?, description=?, type_id=? WHERE id=?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, offre.getTitre());
                preparedStatement.setString(2, offre.getDescription());
                preparedStatement.setInt(3, offre.getType().getId());
                preparedStatement.setInt(4, offre.getId());

                preparedStatement.executeUpdate();

                // Update the existing student object with the new details
                o.setTitre(offre.getTitre());
                o.setDescription(offre.getDescription());
                o.setType(offre.getType());

                // Return the updated student object
                System.out.println(o);
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
		return o;
	}

	@Override
	public void delete(Offre offre) {
	  String query = "DELETE FROM db_recrutement.offre WHERE id = ?";
	    try {
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, offre.getId());
	        int rowsAffected = pstmt.executeUpdate();
	        System.out.println(rowsAffected + " row(s) deleted.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public Offre getOneOffre(int id) {
		Offre offre = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //sélectionner les étudiants de la filière spécifiée
            String query = "SELECT * FROM db_recrutement.offre WHERE id=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            //Exécuter la requête
            resultSet = preparedStatement.executeQuery();
            //Parcourir les résultats et ajouter les étudiants à la liste
            while (resultSet.next()) {
                String titre = resultSet.getString("titre");
                String description = resultSet.getString("description");
                int createur_id = resultSet.getInt("createur_id");
                int type_id = resultSet.getInt("type_id");

                User createur = DefaultServices.getInstance().getUserById(createur_id);
                System.out.println(createur);
                TypeOffre type = DefaultServices.getInstance().getOneTypeOffre(type_id);
                System.out.println(type);
                
                offre = new Offre(id, titre, description, createur, type);
                System.out.println(offre);
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer le statement et le resultSet dans un bloc finally pour s'assurer 
        	//qu'ils sont tous fermés, même en cas d'exception
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
        return offre;
	}

	@Override
	public List<Offre> getAllOffres() {
		// TODO Auto-generated method stub
		List<Offre> offres = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //sélectionner les étudiants de la filière spécifiée
            String query = "SELECT * FROM db_recrutement.offre";
            preparedStatement = conn.prepareStatement(query);
            //Exécuter la requête
            resultSet = preparedStatement.executeQuery();
            //Parcourir les résultats et ajouter les étudiants à la liste
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titre = resultSet.getString("titre");
                String description = resultSet.getString("description");
                int createur_id = resultSet.getInt("createur_id");
                int type_id = resultSet.getInt("type_id");

                User createur = DefaultServices.getInstance().getUserById(createur_id);
                System.out.println(createur);
                TypeOffre type = DefaultServices.getInstance().getOneTypeOffre(type_id);
                System.out.println(type);
                
                Offre offre = new Offre(id, titre, description, createur, type);
                offres.add(offre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer le statement et le resultSet dans un bloc finally pour s'assurer 
        	//qu'ils sont tous fermés, même en cas d'exception
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
        return offres;
	}

	@Override
	public List<Offre> getOffresOf(User user) {
		List<Offre> offres = new ArrayList<>();
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    try {
	        // Prepare the SQL query to fetch offers for a specific user
	        String query = "SELECT * FROM db_recrutement.offre WHERE createur_id = ?";
	        preparedStatement = conn.prepareStatement(query);
	        preparedStatement.setInt(1, user.getId());
	        
	        // Execute the query
	        resultSet = preparedStatement.executeQuery();
	        
	        // Iterate through the result set and create Offre objects
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String titre = resultSet.getString("titre");
	            String description = resultSet.getString("description");
	            int createur_id = resultSet.getInt("createur_id");
	            int type_id = resultSet.getInt("type_id");

	            // Retrieve the User and TypeOffre objects using DefaultServices
	            User createur = DefaultServices.getInstance().getUserById(createur_id);
	            TypeOffre type = DefaultServices.getInstance().getOneTypeOffre(type_id);
	            
	            // Create a new Offre object and add it to the list
	            Offre offre = new Offre(id, titre, description, createur, type);
	            offres.add(offre);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Ensure the result set and statement are closed to avoid resource leaks
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
	    
	    return offres;
	}

}
