package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidad.Proveedor;
import util.MySqlDBConexion;

public class ProveedorModel {

	public int insertaProveedor(Proveedor obj) {

		int salida = -1;

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			// 1 Crear conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Crear sentencia SQL
			String sql = "INSERT INTO proveedor(nombre,dni,fechaRegistro,"
					+ "fechaActualizacion,idTipo,idPais,estado) "
					+ "VALUES(?,?,?,?,?,?,?)";

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getDni());

			pstm.setTimestamp(3,
					java.sql.Timestamp.valueOf(obj.getFechaRegistro()));

			pstm.setTimestamp(4,
					java.sql.Timestamp.valueOf(obj.getFechaActualizacion()));

			pstm.setInt(5, obj.getTipo().getIdTipo());

			pstm.setInt(6, obj.getPais().getIdPais());

			pstm.setInt(7, obj.getEstado());

			// 3 Ejecutar sentencia SQL
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

	public boolean existeProveedorPorDNI(String dni) {

		boolean existe = false;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			// 1 Crear conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Crear sentencia SQL
			String sql = "SELECT count(*) FROM proveedor WHERE dni = ?";

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, dni);

			// 3 Ejecutar sentencia SQL
			rs = pstm.executeQuery();

			if (rs.next()) {
				existe = rs.getInt(1) > 0;
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

		return existe;
	}
}