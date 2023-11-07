package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.RegistroTabla;

public class AeropuertoDao {

	private ConexionBD conexion;
	
	/**
	 * Carga los datos de la tabla dependiendo si elegimos aeropuertos publicos o privados.
	 * @param bPrivado
	 * @return ObservableList<RegistroTabla>
	 */
	public ObservableList<RegistroTabla> cargarAeropuertos(boolean bPrivado){
		ObservableList<RegistroTabla> listaAeropuertos = FXCollections.observableArrayList();
		try {
			conexion=new ConexionBD();
			String consulta = "";
			if (bPrivado) {
				consulta= "SELECT aeropuertos.id,nombre,pais,ciudad,calle,numero,anio_inauguracion,capacidad,numero_socios "
						+ "FROM aeropuertos_privados,aeropuertos,direcciones "
						+ "WHERE aeropuertos.id=direcciones.id "
						+ "AND aeropuertos.id=aeropuertos_privados.id_aeropuerto;";	
			}else {
				consulta= "SELECT aeropuertos.id,nombre,pais,ciudad,calle,numero,anio_inauguracion,capacidad,financiacion,num_trabajadores "
						+ "FROM aeropuertos_publicos,aeropuertos,direcciones "
						+ "WHERE aeropuertos.id=direcciones.id "
						+ "AND aeropuertos.id=aeropuertos_publicos.id_aeropuerto;";
			}
			
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
				RegistroTabla rt; 
				if(bPrivado) {
					Integer nsocios=rs.getInt("numero_socios");
					rt = new RegistroTabla(nid, snombre, spais, sciudad, scalle, nnumero, nanio, ncapacidad, nsocios);
				}else {
					Integer nfinanciacion=rs.getInt("financiacion");
					Integer ntrabajadores=rs.getInt("num_trabajadores");
					rt = new RegistroTabla(nid, snombre, spais, sciudad, scalle, nnumero, nanio, ncapacidad, nfinanciacion,ntrabajadores);
				}
				listaAeropuertos.add(rt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAeropuertos;
	}
}
