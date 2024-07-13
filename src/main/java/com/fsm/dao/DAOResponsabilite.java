package com.fsm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fsm.models.Offre;
import com.fsm.models.Responsabilite;
import com.fsm.util.Utilitaire;

public class DAOResponsabilite implements IDAOResponsabilite{
	Offre offre;
	Connection conn;
	ResultSet res;

	public DAOResponsabilite() {
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
	public Responsabilite insert(String desc){
		PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        Responsabilite resp = null;    
        int id = 0;

        try {
            String query = "INSERT INTO db_recrutement.responsabilite (description) VALUES (?)";
            preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, desc);

            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys != null && generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            resp = new Responsabilite(id, desc);
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
        return resp;
	}
	
	@Override
	public Responsabilite update(Responsabilite r) {
		PreparedStatement preparedStatement = null;
		Responsabilite resp = null;
        try {
        	resp = getOneResponsabilite(r.getId());

            if (resp != null) {
                String query = "UPDATE db_recrutement.responsabilite SET description=? WHERE id=?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, r.getDescription());
                preparedStatement.setInt(2, r.getId());

                preparedStatement.executeUpdate();

                resp.setDescription(r.getDescription());
                System.out.println(resp);
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
        return resp;
	}

	@Override
	public void delete(Responsabilite r) {
		String query = "DELETE FROM db_recrutement.responsabilite WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, r.getId());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Responsabilite getOneResponsabilite(int id) {
		Responsabilite resp = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.responsabilite WHERE id = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String description = resultSet.getString("description");
                resp = new Responsabilite(id, description);
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
        return resp;
	}

	@Override
	public int getResponsabiliteId(String desc) {
		int id = 0;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    try {
	        String query = "SELECT * FROM db_recrutement.responsabilite WHERE description = ?";
	        preparedStatement = conn.prepareStatement(query);
	        preparedStatement.setString(1, desc);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            id = resultSet.getInt("id");
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
	    return id;
	}
	
	
	@Override
	public List<Responsabilite> getAllResponsabilites() {
		List<Responsabilite> responsabilites = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.responsabilite";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                Responsabilite resp = new Responsabilite(id, description);
                responsabilites.add(resp);
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
        return responsabilites;
	}

	@Override
	public List<Responsabilite> getResponsabilitesOfOffer(int id) {
		List<Responsabilite> responsabilites = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {       	
        	String query = "SELECT r.* FROM responsabilite r " +
                    "JOIN offre_responsabilite orr ON r.id = orr.responsabilite_id " +
                    "WHERE orr.offre_id = ?";
        	
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
          
            while (resultSet.next()) {
            	Responsabilite q = new Responsabilite(resultSet.getInt("id"), resultSet.getString("description"));
            	responsabilites.add(q);
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
        return responsabilites;
	}


}
