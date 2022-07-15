package it.data;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = "jdbc:postgresql://localhost:5432/bancadb";
    public static final String USER = "postgres";
    public static final String PASS = "epicode";



    public static Connection getConnection(){
        Connection con = null;
        
        try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            con = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Connessione effettuata correttamente!");
        } catch (SQLException e) {
        	System.out.println("Connessione non effettuata!");
            e.printStackTrace();
        }
        return con;
    }
}

