package it.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ContoCorrenteDAO {
	

	public ContoCorrente getSaldo(Long id, int PIN){
		Connection conn = ConnectionFactory.getConnection();
		Statement st = null;
		ResultSet rs = null;
		ContoCorrente saldo = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM contocorrente WHERE numero = " + id + "AND pin = " + PIN);
			
			rs.next();
			saldo = new ContoCorrente();
			saldo.setNumero(rs.getLong("numero"));
			saldo.setIntestatario(rs.getString("intestatario"));
			saldo.setSaldo(rs.getDouble("saldo"));
			saldo.setPin(rs.getInt("pin"));
			
			System.out.println("Recupero saldo");
			
		}
		catch(SQLException ex) {
			System.out.println("Errore nel recupero saldo");
			ex.printStackTrace();
		}
		
		try {rs.close();}catch(Exception ex) {}
		try {conn.close();}catch(Exception ex) {}
		
		return saldo;
	}
	

	
	public boolean versa(Long id, int PIN, double saldo) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;

		int i = 0;
		
		try {
			
			String q = "UPDATE contocorrente SET saldo = saldo + ? WHERE numero= ? AND pin= ?";
			ps = conn.prepareStatement(q);
			ps.setDouble(1, saldo);
			ps.setLong(2, id);
			ps.setInt(3, PIN);
			i=ps.executeUpdate();


			System.out.println("Versamento avvenuto!");
		}
		catch(SQLException ex) {
			System.out.println("Errore versamento");
			ex.printStackTrace();
		}
		

		try {ps.close();}catch(Exception ex) {}
		try {conn.close();}catch(Exception ex) {}
		
		return (i>0);
	}

	
	public boolean preleva(Long id, int PIN, double saldo) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;

		int i = 0;
		
		try {
			
			String q = "UPDATE contocorrente SET saldo = saldo - ? WHERE numero= ? AND pin= ?";
			ps = conn.prepareStatement(q);
			ps.setDouble(1, saldo);
			ps.setLong(2, id);
			ps.setInt(3, PIN);
			i=ps.executeUpdate();


			System.out.println("Prelievo avvenuto!");
		}
		catch(SQLException ex) {
			System.out.println("Errore prelievo");
			ex.printStackTrace();
		}
		

		try {ps.close();}catch(Exception ex) {}
		try {conn.close();}catch(Exception ex) {}
		
		return (i>0);
	}

	public boolean esiste(Long numero, int PIN) {
        boolean result = false;
        Connection conn = ConnectionFactory.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            String q = "SELECT EXISTS(SELECT * from contocorrente WHERE numero = " + numero + "AND pin = " + PIN +")";
            st = conn.createStatement();
            rs = st.executeQuery(q);
            rs.next();
            result = rs.getBoolean(1);
            System.out.println("Ricerca effettuata");
        } catch (SQLException ex) {
            System.out.println("Ricerca fallita");
            ex.printStackTrace();
        }

        return result;
    }
}
