package com.fsm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fsm.models.TypeOffre;
import com.fsm.util.Utilitaire;

public class DAOTypeOffre implements IDAOTypeOffre {

    private Connection conn;

    public DAOTypeOffre() {
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
    public TypeOffre insert(TypeOffre type) {
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        TypeOffre t = null;
        
        int id = 0;
        String libelle = type.getLibelle();

        try {
            String query = "INSERT INTO db_recrutement.typeOffre (libelle) VALUES (?)";
            preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, libelle);

            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys != null && generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            type.setId(id);
            t = new TypeOffre(id, libelle);
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
        return t;
    }

    @Override
    public TypeOffre update(TypeOffre type) {
        PreparedStatement preparedStatement = null;
        TypeOffre t = null;
        try {
            t = getOneTypeOffre(type.getId());

            if (t != null) {
                String query = "UPDATE db_recrutement.typeOffre SET libelle=? WHERE id=?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, type.getLibelle());
                preparedStatement.setInt(2, type.getId());

                preparedStatement.executeUpdate();

                t.setLibelle(type.getLibelle());
                System.out.println(t);
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
        return t;
    }

    @Override
    public void delete(TypeOffre type) {
        String query = "DELETE FROM db_recrutement.typeOffre WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, type.getId());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TypeOffre getOneTypeOffre(int id) {
        TypeOffre typeOffre = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.typeOffre WHERE id=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String libelle = resultSet.getString("libelle");
                typeOffre = new TypeOffre(id, libelle);
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
        return typeOffre;
    }

    @Override
    public List<TypeOffre> getAllTypeOffres() {
        List<TypeOffre> typeOffres = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.typeOffre";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String libelle = resultSet.getString("libelle");
                TypeOffre typeOffre = new TypeOffre(id, libelle);
                typeOffres.add(typeOffre);
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
        return typeOffres;
    }

	@Override
	public int getTypeOffreId(String type) {
		int id = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM db_recrutement.typeOffre WHERE libelle=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, type);
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
}
