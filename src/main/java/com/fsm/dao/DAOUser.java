package com.fsm.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fsm.models.User;
import com.fsm.util.Utilitaire;


public class DAOUser implements IDAOUser{
	
	User user;
	Connection conn;
	ResultSet res;

	public DAOUser() {
		try {
			conn = Utilitaire.getInstance().getConnection();
	        if (conn == null) {
	            throw new SQLException("La connexion à la base de données n'a pas pu être établie.");
	        }
	    	} catch (Exception e) {
	        e.printStackTrace();
	    	}	
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //sélectionner les étudiants de la filière spécifiée
            String query = "SELECT * FROM db_recrutement.user";
            preparedStatement = conn.prepareStatement(query);
            //Exécuter la requête
            resultSet = preparedStatement.executeQuery();
            //Parcourir les résultats et ajouter les étudiants à la liste
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");
                boolean isSubscribed = resultSet.getBoolean("isSubscribed");
                // filiere est déjà passé en paramètre
                User user = new User(id, nom, prenom, username, password, role, email, isSubscribed);
                System.out.println(user);
                users.add(user);
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
        return users;
	}
	

	public User insert(User user) {
	    PreparedStatement preparedStatement = null;
	    ResultSet generatedKeys = null;
	    User u = null;
	    
	    int id = 0;
	    String nom = user.getNom();
	    String prenom = user.getPrenom();
	    String username = user.getUsername();
	    String email = user.getEmail();
	    String role = "candidat";
	    String password = user.getPassword();

	    try {
	        // Insert student information
	        String query = "INSERT INTO db_recrutement.user (nom, prenom, username, email, role, password) VALUES (?, ?, ?, ?, ?, ?)";
	        preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, nom);
	        preparedStatement.setString(2, prenom);
	        preparedStatement.setString(3, username);
	        preparedStatement.setString(4, email);
	        preparedStatement.setString(5, role);
	        preparedStatement.setString(6, password);

	        // Execute the query and retrieve the generated keys
	        preparedStatement.executeUpdate();
	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys != null && generatedKeys.next()) {
	            id = generatedKeys.getInt(1);
	        }
	        user.setId(id);
	        u = new User(id, nom, prenom, username, password, role, email);
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
	    return u;
	}

	public void delete(String username) {
	    String query = "DELETE FROM db_recrutement.user WHERE username = ?";
	    try {
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, username);
	        int rowsAffected = pstmt.executeUpdate();
	        System.out.println(rowsAffected + " row(s) deleted.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public User getOneUser(String username) {
		User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //sélectionner les étudiants de la filière spécifiée
            String query = "SELECT * FROM db_recrutement.user WHERE username=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            //Exécuter la requête
            resultSet = preparedStatement.executeQuery();
            //Parcourir les résultats et ajouter les étudiants à la liste
            while (resultSet.next()) {
            	int id = resultSet.getInt("id");
            	String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");
                // filiere est déjà passé en paramètre
                user = new User(id, nom, prenom, username, password, role, email);
                System.out.println(user);
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
        return user;
	}

	@Override
	public User getUserById(int id) {
		User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //sélectionner les étudiants de la filière spécifiée
            String query = "SELECT * FROM db_recrutement.user WHERE id=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            //Exécuter la requête
            resultSet = preparedStatement.executeQuery();
            //Parcourir les résultats et ajouter les étudiants à la liste
            while (resultSet.next()) {
            	String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");
                // filiere est déjà passé en paramètre
                user = new User(id, nom, prenom, username, password, role, email);
                System.out.println(user);
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
        return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //sélectionner les étudiants de la filière spécifiée
            String query = "SELECT * FROM db_recrutement.user WHERE email=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,email);
            //Exécuter la requête
            resultSet = preparedStatement.executeQuery();
            //Parcourir les résultats et ajouter les étudiants à la liste
            while (resultSet.next()) {
            	int id = resultSet.getInt("id");
            	String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String username = resultSet.getString("username");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");
                boolean isSubscribed = resultSet.getBoolean("isSubscribed");
                // filiere est déjà passé en paramètre
                user = new User(id, nom, prenom, username, password, role, email, isSubscribed);
                System.out.println(user);
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
        return user;
	}

	@Override
	public void update(User user) {
        PreparedStatement preparedStatement = null;
        System.out.println(user);
        try {
            String query = "UPDATE db_recrutement.user SET isSubscribed = 1 WHERE id = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            //Exécuter la requête
            preparedStatement.executeUpdate();           
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
	   }

	@Override
	public List<User> getAllSubscribedUsers() {
		List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
        	String query = "SELECT * FROM db_recrutement.user WHERE isSubscribed = 1";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            //Parcourir les résultats et ajouter les étudiants à la liste
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");
                boolean isSubscribed = resultSet.getBoolean("isSubscribed");
                // filiere est déjà passé en paramètre
                User user = new User(id, nom, prenom, username, password, role, email, isSubscribed);
                System.out.println(user);
                users.add(user);
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
        return users;

	}

		
	

//	@Override
//	public User delete(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User getOneUser(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}

