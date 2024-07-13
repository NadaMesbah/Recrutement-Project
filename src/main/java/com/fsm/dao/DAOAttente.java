package com.fsm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;


import com.fsm.models.Attente;
import com.fsm.models.Offre;

import com.fsm.util.Utilitaire;

public class DAOAttente implements IDAOAttente{
	Offre offre;
	Connection conn;
	ResultSet res;

	public DAOAttente() {
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
	public Attente insert(String desc) {
		PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        Attente attente = null;
        
        int id = 0;

        try {
            String query = "INSERT INTO db_recrutement.attente (description) VALUES (?)";
            preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, desc);

            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys != null && generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            attente = new Attente(id, desc);
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
        return attente;
	}
	@Override
	public Attente update(Attente a) {
		PreparedStatement preparedStatement = null;
		Attente attente = null;
        try {
        	attente = getOneAttente(a.getId());

            if (attente != null) {
                String query = "UPDATE db_recrutement.attente SET description=? WHERE id=?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, a.getDescription());
                preparedStatement.setInt(2, a.getId());

                preparedStatement.executeUpdate();

                attente.setDescription(a.getDescription());
                System.out.println(attente);
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
        return attente;
	}

	@Override
	public void delete(Attente a) {
		String query = "DELETE FROM db_recrutement.attente WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, a.getId());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Attente getOneAttente(int id) {
		Attente attente = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.attente WHERE id = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String description = resultSet.getString("description");
                attente = new Attente(id, description);
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
        return attente;
	}

	@Override
	public int getAttenteId(String desc) {
		int id = 0;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    try {
	        String query = "SELECT * FROM db_recrutement.attente WHERE description = ?";
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
	public List<Attente> getAllAttentes() {
		List<Attente> attentes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.attente";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                Attente attente = new Attente(id, description);
                attentes.add(attente);
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
        return attentes;
	}

	@Override
	public List<Attente> getAttentesOfOffer(int id) {
		List<Attente> attentes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
        	String query = "SELECT a.* FROM attente a " +
                     "JOIN offre_attente oa ON a.id = oa.attente_id " +
                     "WHERE oa.offre_id = ?";
        	
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
          
            while (resultSet.next()) {
                Attente attente = new Attente(resultSet.getInt("id"), resultSet.getString("description"));
                attentes.add(attente);
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
        return attentes;
	}


}
