package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Tipo;
import util.MySqlDBConexion;

public class TipoModel {

	public List<Tipo> ListaTodos() {

		List<Tipo> lista = new ArrayList<Tipo>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			// 1 Crear conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Crear sentencia SQL
			String sql = "SELECT * FROM tipo";
			pstm = conn.prepareStatement(sql);

			// 3 Ejecutar sentencia
			rs = pstm.executeQuery();

			while (rs.next()) {

				Tipo t = new Tipo();

				t.setIdTipo(rs.getInt("idTipo"));
				t.setDescripcion(rs.getString("descripcion"));

				lista.add(t);
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