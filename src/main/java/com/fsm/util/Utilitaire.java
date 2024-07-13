package com.fsm.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilitaire{
	//pattern de Singleton pour s'assurer que la meme instance de Connextion 
	//est utilisée et creer une nouvelle si elle n'existe pas
	private static Utilitaire instance=null;
	public static Utilitaire getInstance() throws ClassNotFoundException{
		if(instance==null)
			instance=new Utilitaire();
     return instance;
    }
	//objet conn de type Connection
	private Connection conn;
	
	public Connection getConnection() {
		return conn;
	}
	public Utilitaire() throws ClassNotFoundException{
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_recrutement?user=root&password=Raykod2003");
	    } catch (SQLException e){
	    	e.printStackTrace();
	    }	
    }
}
