package com.fsm.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fsm.metier.DefaultServices;
import com.fsm.models.Attente;
import com.fsm.models.Offre;
import com.fsm.models.Qualification;
import com.fsm.models.Responsabilite;
import com.fsm.models.TypeOffre;
import com.fsm.models.User;
import com.fsm.util.Utilitaire;

public class NewDAOOffre implements IDAOOffre{

	Offre offre;
	Connection conn;
	ResultSet res;

	public NewDAOOffre() {
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
	            // Insert offer
	            String query = "INSERT INTO db_recrutement.offre (titre, description, createur_id, type_id) VALUES (?, ?, ?, ?)";
	            preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	            preparedStatement.setString(1, titre);
	            preparedStatement.setString(2, description);
	            preparedStatement.setInt(3, createur.getId());
	            preparedStatement.setInt(4, type.getId());
	            preparedStatement.executeUpdate();

	            // Get generated keys
	            generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys != null && generatedKeys.next()) {
	                id = generatedKeys.getInt(1);
	            }
	            offre.setId(id);

	            insertResponsabilites(offre.getResponsabilites());

	            insertQualifications(offre.getQualifications());

	            insertAttentes(offre.getAttentes());

	            insertOffreResponsabilites(id, offre.getResponsabilites());

	            insertOffreQualifications(id, offre.getQualifications());

	            insertOffreAttentes(id, offre.getAttentes());

	            o = new Offre(id, titre, description, createur, type, offre.getResponsabilites(), offre.getQualifications(), offre.getAttentes());
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

	    private void insertResponsabilites(List<Responsabilite> responsabilites) throws SQLException {
	        String query = "INSERT INTO db_recrutement.responsabilite (description) VALUES (?)";
	        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	            for (Responsabilite responsabilite : responsabilites) {
	                ps.setString(1, responsabilite.getDescription());
	                ps.executeUpdate();
	                try (ResultSet rs = ps.getGeneratedKeys()) {
	                    if (rs.next()) {
	                        responsabilite.setId(rs.getInt(1));
	                    }
	                }
	            }
	        }
	    }

	    private void insertQualifications(List<Qualification> qualifications) throws SQLException {
	        String query = "INSERT INTO db_recrutement.qualification (description) VALUES (?)";
	        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	            for (Qualification qualification : qualifications) {
	                ps.setString(1, qualification.getDescription());
	                ps.executeUpdate();
	                try (ResultSet rs = ps.getGeneratedKeys()) {
	                    if (rs.next()) {
	                        qualification.setId(rs.getInt(1));
	                    }
	                }
	            }
	        }
	    }

	    private void insertAttentes(List<Attente> attentes) throws SQLException {
	        String query = "INSERT INTO db_recrutement.attente (description) VALUES (?)";
	        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	            for (Attente attente : attentes) {
	                ps.setString(1, attente.getDescription());
	                ps.executeUpdate();
	                try (ResultSet rs = ps.getGeneratedKeys()) {
	                    if (rs.next()) {
	                        attente.setId(rs.getInt(1));
	                    }
	                }
	            }
	        }
	    }

	    private void insertOffreResponsabilites(int offreId, List<Responsabilite> responsabilites) throws SQLException {
	        String query = "INSERT INTO db_recrutement.offre_responsabilite (offre_id, responsabilite_id) VALUES (?, ?)";
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            for (Responsabilite responsabilite : responsabilites) {
	                ps.setInt(1, offreId);
	                ps.setInt(2, responsabilite.getId());
	                ps.executeUpdate();
	            }
	        }
	    }

	    private void insertOffreQualifications(int offreId, List<Qualification> qualifications) throws SQLException {
	        String query = "INSERT INTO db_recrutement.offre_qualification (offre_id, qualification_id) VALUES (?, ?)";
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            for (Qualification qualification : qualifications) {
	                ps.setInt(1, offreId);
	                ps.setInt(2, qualification.getId());
	                ps.executeUpdate();
	            }
	        }
	    }

	    private void insertOffreAttentes(int offreId, List<Attente> attentes) throws SQLException {
	        String query = "INSERT INTO db_recrutement.offre_attente (offre_id, attente_id) VALUES (?, ?)";
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            for (Attente attente : attentes) {
	                ps.setInt(1, offreId);
	                ps.setInt(2, attente.getId());
	                ps.executeUpdate();
	            }
	        }
	    }

	    @Override
	    public Offre update(Offre offre) {
	        PreparedStatement preparedStatement = null;
	        Offre o = null;
	        try {
	            o = getOneOffre(offre.getId());

	            if (o != null) {
	                String query = "UPDATE db_recrutement.offre SET titre=?, description=?, type_id=? WHERE id=?";
	                preparedStatement = conn.prepareStatement(query);
	                preparedStatement.setString(1, offre.getTitre());
	                preparedStatement.setString(2, offre.getDescription());
	                preparedStatement.setInt(3, offre.getType().getId());
	                preparedStatement.setInt(4, offre.getId());
	                preparedStatement.executeUpdate();

	                o.setTitre(offre.getTitre());
	                o.setDescription(offre.getDescription());
	                o.setType(offre.getType());

	                updateOffreResponsabilites(offre.getId(), offre.getResponsabilites());
	                updateOffreQualifications(offre.getId(), offre.getQualifications());
	                updateOffreAttentes(offre.getId(), offre.getAttentes());
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

	    private void updateOffreResponsabilites(int offreId, List<Responsabilite> responsabilites) throws SQLException {
	        if (responsabilites == null) {
	            responsabilites = new ArrayList<>();
	        }
	        String deleteQuery = "DELETE FROM db_recrutement.offre_responsabilite WHERE offre_id=?";
	        try (PreparedStatement ps = conn.prepareStatement(deleteQuery)) {
	            ps.setInt(1, offreId);
	            ps.executeUpdate();
	        }
	        insertOffreResponsabilites(offreId, responsabilites);
	    }

	    private void updateOffreQualifications(int offreId, List<Qualification> qualifications) throws SQLException {
	        String deleteQuery = "DELETE FROM db_recrutement.offre_qualification WHERE offre_id=?";
	        try (PreparedStatement ps = conn.prepareStatement(deleteQuery)) {
	            ps.setInt(1, offreId);
	            ps.executeUpdate();
	        }
	        insertOffreQualifications(offreId, qualifications);
	    }

	    private void updateOffreAttentes(int offreId, List<Attente> attentes) throws SQLException {
	        String deleteQuery = "DELETE FROM db_recrutement.offre_attente WHERE offre_id=?";
	        try (PreparedStatement ps = conn.prepareStatement(deleteQuery)) {
	            ps.setInt(1, offreId);
	            ps.executeUpdate();
	        }
	        insertOffreAttentes(offreId, attentes);
	    }

	    

	@Override
	public void delete(Offre offre) {
	    try {
	        deleteOffreRelations(offre.getId());
	        
	        String query = "DELETE FROM db_recrutement.offre WHERE id = ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	            pstmt.setInt(1, offre.getId());
	            int rowsAffected = pstmt.executeUpdate();
	            System.out.println(rowsAffected + " row(s) deleted.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private void deleteOffreRelations(int offreId) throws SQLException {
	    String[] queries = {
	        "DELETE FROM db_recrutement.offre_responsabilite WHERE offre_id=?",
	        "DELETE FROM db_recrutement.offre_qualification WHERE offre_id=?",
	        "DELETE FROM db_recrutement.offre_attente WHERE offre_id=?"
	    };
	    for (String query : queries) {
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setInt(1, offreId);
	            ps.executeUpdate();
	        }
	    }
	}


	@Override
	public Offre getOneOffre(int id) {
	    Offre offre = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    try {
	        String query = "SELECT * FROM db_recrutement.offre WHERE id=?";
	        preparedStatement = conn.prepareStatement(query);
	        preparedStatement.setInt(1, id);
	        resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            String titre = resultSet.getString("titre");
	            String description = resultSet.getString("description");
	            int createur_id = resultSet.getInt("createur_id");
	            int type_id = resultSet.getInt("type_id");

	            User createur = DefaultServices.getInstance().getUserById(createur_id);
	            TypeOffre type = DefaultServices.getInstance().getOneTypeOffre(type_id);

	            List<Responsabilite> responsabilites = getResponsabilitesByOffreId(id);
	            List<Qualification> qualifications = getQualificationsByOffreId(id);
	            List<Attente> attentes = getAttentesByOffreId(id);

	            offre = new Offre(id, titre, description, createur, type, responsabilites,qualifications,attentes);
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
	    return offre;
	}


	private List<Responsabilite> getResponsabilitesByOffreId(int offreId) throws SQLException {
	    List<Responsabilite> responsabilites = new ArrayList<>();
	    String query = "SELECT r.* FROM responsabilite r " +
                "JOIN offre_responsabilite orr ON r.id = orr.responsabilite_id " +
                "WHERE orr.offre_id = ?";	    
	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, offreId);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                responsabilites.add(new Responsabilite(rs.getInt("id"), rs.getString("description")));
	            }
	        }
	    }
	    return responsabilites;
	}

	private List<Qualification> getQualificationsByOffreId(int offreId) throws SQLException {
	    List<Qualification> qualifications = new ArrayList<>();
	    String query = "SELECT q.id, q.description FROM db_recrutement.qualification q INNER JOIN db_recrutement.offre_qualification oq ON q.id = oq.qualification_id WHERE oq.offre_id = ?";
	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, offreId);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                qualifications.add(new Qualification(rs.getInt("id"), rs.getString("description")));
	            }
	        }
	    }
	    return qualifications;
	}

	private List<Attente> getAttentesByOffreId(int offreId) throws SQLException {
	    List<Attente> attentes = new ArrayList<>();
	    String query = "SELECT a.id, a.description FROM db_recrutement.attente a INNER JOIN db_recrutement.offre_attente oa ON a.id = oa.attente_id WHERE oa.offre_id = ?";
	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, offreId);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                attentes.add(new Attente(rs.getInt("id"), rs.getString("description")));
	            }
	        }
	    }
	    return attentes;
	}


	@Override
	public List<Offre> getAllOffres() {
	    List<Offre> offres = new ArrayList<>();
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    try {
	        String query = "SELECT * FROM db_recrutement.offre";
	        preparedStatement = conn.prepareStatement(query);
	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String titre = resultSet.getString("titre");
	            String description = resultSet.getString("description");
	            int createur_id = resultSet.getInt("createur_id");
	            int type_id = resultSet.getInt("type_id");

	            User createur = DefaultServices.getInstance().getUserById(createur_id);
	            TypeOffre type = DefaultServices.getInstance().getOneTypeOffre(type_id);

	            List<Responsabilite> responsabilites = getResponsabilitesByOffreId(id);
	            List<Qualification> qualifications = getQualificationsByOffreId(id);
	            List<Attente> attentes = getAttentesByOffreId(id);
	          
	            Offre offre = new Offre(id, titre, description, createur, type, responsabilites, qualifications, attentes);
	            offres.add(offre);
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
		        resultSet = preparedStatement.executeQuery();
		        
		        while (resultSet.next()) {
		        	int id = resultSet.getInt("id");
		            String titre = resultSet.getString("titre");
		            String description = resultSet.getString("description");
		            int createur_id = resultSet.getInt("createur_id");
		            int type_id = resultSet.getInt("type_id");
		            

		            User createur = DefaultServices.getInstance().getUserById(createur_id);
		            TypeOffre type = DefaultServices.getInstance().getOneTypeOffre(type_id);

		            List<Responsabilite> responsabilites = getResponsabilitesByOffreId(id);
		            List<Qualification> qualifications = getQualificationsByOffreId(id);
		            List<Attente> attentes = getAttentesByOffreId(id);
		            
		            Offre offre = new Offre(id, titre, description, createur, type, responsabilites, qualifications, attentes);
		            offres.add(offre);
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
		    return offres;
	}

}
