package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Concurso;
import util.MySqlDBConexion;

public class ConcursoModel {
	public int insertaConcurso(Concurso obj) {
		int salida = -1;
		
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			
		//1 Crear conexión
		conn = MySqlDBConexion.getConexion();
		
		//2 Crear sentencia SQL
		String sql = "INSERT INTO concurso (nombre, fechaInicio, fechaFin, estado) VALUES (?,?,?,?)";
		pstm = conn.prepareStatement(sql);
		pstm.setString(1, obj.getNombre());
		pstm.setDate(2, java.sql.Date.valueOf(obj.getFechaInicio()));
		pstm.setDate(3, java.sql.Date.valueOf(obj.getFechaFin()));
		pstm.setString(4, obj.getEstado());
		
		//3 Ejecutar sentencia SQL 
		salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return salida;
	}
	
	public List<Concurso> listaConcurso(String nombre, LocalDate desde, LocalDate hasta) {
		ArrayList<Concurso> lista = new ArrayList<Concurso>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "SELECT * FROM concurso WHERE "
			        + " nombre LIKE ? AND "
			        + " ( ? ='9999-01-01' or fechaInicio >= ? ) AND "
			        + " ( ? ='9999-01-01' or fechaInicio <= ? ) ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + nombre + "%");
			pstm.setDate(2, java.sql.Date.valueOf(desde));
			pstm.setDate(3, java.sql.Date.valueOf(desde));
			pstm.setDate(4, java.sql.Date.valueOf(hasta));
			pstm.setDate(5, java.sql.Date.valueOf(hasta));
			
			//imprimir el query para verificar que se arma correctamente
			System.out.println("SQL: " + pstm.toString());
			
			//Se ejecuta el query en la base de datos
			rs = pstm.executeQuery();


			while (rs.next()) {
			    Concurso a = new Concurso();

			    a.setIdConcurso(rs.getInt("idConcurso"));
			    a.setNombre(rs.getString("nombre"));
			    a.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
			    a.setFechaFin(rs.getDate("fechaFin").toLocalDate());

			    lista.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}
}
