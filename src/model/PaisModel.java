package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Pais;
import util.MySqlDBConexion;

public class PaisModel {

	public List<Pais> ListaTodos() {

		List<Pais> lista = new ArrayList<Pais>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			// 1 Crear conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Crear sentencia SQL
			String sql = "SELECT * FROM pais";
			pstm = conn.prepareStatement(sql);

			// 3 Ejecutar sentencia
			rs = pstm.executeQuery();

			while (rs.next()) {

				Pais p = new Pais();

				p.setIdPais(rs.getInt("idPais"));
				p.setIso(rs.getString("iso"));
				p.setNombre(rs.getString("nombre"));

				lista.add(p);
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