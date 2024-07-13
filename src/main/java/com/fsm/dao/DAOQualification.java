package com.fsm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fsm.models.Offre;
import com.fsm.models.Qualification;
import com.fsm.util.Utilitaire;

public class DAOQualification implements IDAOQualification{
	
	Offre offre;
	Connection conn;
	ResultSet res;

	public DAOQualification() {
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
	public Qualification insert(String desc) {
		PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        Qualification qualification = null;
        int id = 0;

        try {
            String query = "INSERT INTO db_recrutement.qualification (description) VALUES (?)";
            preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, desc);

            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys != null && generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            qualification = new Qualification(id, desc);
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
        return qualification;
	}

	@Override
	public Qualification update(Qualification q) {
		PreparedStatement preparedStatement = null;
		Qualification qualification = null;
        try {
        	qualification = getOneQualification(q.getId());

            if (qualification != null) {
                String query = "UPDATE db_recrutement.qualification SET description=? WHERE id=?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, q.getDescription());
                preparedStatement.setInt(2, q.getId());

                preparedStatement.executeUpdate();

                qualification.setDescription(q.getDescription());
                System.out.println(qualification);
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
        return qualification;
	}

	@Override
	public void delete(Qualification q) {
		String query = "DELETE FROM db_recrutement.qualification WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, q.getId());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Qualification getOneQualification(int id) {
		Qualification qualification = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.qualification WHERE id = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String description = resultSet.getString("description");
                qualification = new Qualification(id, description);
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
        return qualification;
	}

	@Override
	public int getQualificationId(String desc) {
		int id = 0;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    try {
	        String query = "SELECT * FROM db_recrutement.qualification WHERE description = ?";
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
	public List<Qualification> getAllQualifications() {
		List<Qualification> qualifications = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.qualification";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                Qualification qualification = new Qualification(id, description);
                qualifications.add(qualification);
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
        return qualifications;
	}

	@Override
	public List<Qualification> getQualificationsOfOffer(int id) {
		List<Qualification> qualifications = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {       	
            String query = "SELECT q.* FROM qualification q " +
                           "JOIN offre_qualification oq ON q.id = oq.qualification_id " +
                           "WHERE oq.offre_id = ?";
        	
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
          
            while (resultSet.next()) {
            	Qualification q = new Qualification(resultSet.getInt("id"), resultSet.getString("description"));
                qualifications.add(q);
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
        return qualifications;
	}

}
