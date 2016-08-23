package ar.edu.unq.unidad1.wop.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ar.edu.unq.unidad1.wop.dao.PersonajeDAO;
import ar.edu.unq.unidad1.wop.modelo.Personaje;

/**
 * Una implementacion de {@link PersonajeDAO} que persiste
 * en una base de datos relacional utilizando JDBC
 * 
 * @author Claudio Fernandez
 */
public class JDBCPersonajeDAO implements PersonajeDAO {

	public JDBCPersonajeDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void guardar(Personaje personaje) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/ejemplo_jdbc?user=root&password=root");
			PreparedStatement st = connection.prepareStatement("INSERT INTO personaje "
					+ "(nombre, pesoMaximo, xp, vida) "
					+ "VALUES (?, ?, ?, ?)");
			st.setString(1, personaje.getNombre());
			st.setInt(2, personaje.getPesoMaximo());
			st.setInt(3, personaje.getXp());
			st.setInt(4, personaje.getVida());

			st.execute();
			st.getUpdateCount();
			
			st.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("No se puede guardar " + personaje.getNombre(), e);
		}
	}

	@Override
	public Personaje recuperar(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}




	//
	//return DriverManager.getConnection("jdbc:mysql://localhost:3307/epers_ejemplo_jdbc?user=root&password=root");


}
