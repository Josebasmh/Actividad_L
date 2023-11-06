package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.RegistroTabla;

public class AeropuertoDao {

	private ConexionBD conexion;
	
	public ObservableList<RegistroTabla> cargarAeropuertos(){
		ObservableList<RegistroTabla> listaAeropuertos = FXCollections.observableArrayList();
		try {
			conexion=new ConexionBD();
			String consulta= "SELECT aeropuertos.id,nombre,pais,ciudad,calle,numero,anio_inauguracion,capacidad,numero_socios "
					+ "FROM aeropuertos_privados,aeropuertos,direcciones WHERE aeropuertos.id=direcciones.id "
					+ "AND aeropuertos.id=id_aeropuerto";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();   				
			while (rs.next()) {
				int nid=rs.getInt("id");
				String snombre=rs.getString("nombre");
				String spais = rs.getString("pais");
				String sciudad=rs.getString("ciudad");
				String scalle=rs.getString("calle");
				int nnumero=rs.getInt("numero");
				int nanio=rs.getInt("anio_inauguracion");
				int ncapacidad=rs.getInt("capacidad");
				int nsocios=rs.getInt("numero_socios");
				RegistroTabla rt = new RegistroTabla(nid, snombre, spais, sciudad, scalle, nnumero, nanio, ncapacidad, nsocios);
				listaAeropuertos.add(rt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAeropuertos;
	}
}
