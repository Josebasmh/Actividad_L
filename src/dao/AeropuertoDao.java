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
	public int generarID(String tabla) {
		int id=-1;
		try {
			conexion=new ConexionBD();
			String consulta = "SELECT COUNT(*) FROM "+tabla+";";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs.getInt(0));
			System.out.println(rs.getInt(1));
			id=rs.getInt(1)+1;
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
		
	}
	public void aniadirRegistro(RegistroTabla rt,boolean bPrivado) {
		//Datos tabla direccion
		int idDirecciones = generarID("direcciones");
		String pais=rt.getPais();
		String ciudad=rt.getCiudad();
		String calle=rt.getCalle();
		Integer numero=rt.getNumero();
		
		//Datos tabla aeropuerto
		int id=rt.getId();
		String nombre=rt.getNombre();
		Integer anio=rt.getAnio();
		Integer capacidad=rt.getCapacidad();
		
		//Datos tabla privado/publico
		String consultaPrivado="";
		String consultaPublica="";
		if (bPrivado) {
			Integer num_socio=rt.getFinanciacion();
			consultaPrivado="INSERT INTO aeropuertos_privados values("+id+","+num_socio+");";
		}else {			
			Integer financiacion=rt.getFinanciacion();
			Integer trabajadores=rt.getNum_trabajadores();
			consultaPublica="INSERT INTO aeropuertos_publicos values("+id+","+financiacion+","+trabajadores+")";
		}
				
		String consultaDirecciones="INSERT INTO direcciones values("+idDirecciones+",'"+pais+"','"+ciudad+"','"+calle+"',"+numero+");";
		String consultaAeropuertos="INSERT INTO aeropuertos values("+id+",'"+nombre+"',"+anio+","+capacidad+","+idDirecciones+",NULL);";
		PreparedStatement pstmt;
		try {
			pstmt = conexion.getConexion().prepareStatement(consultaDirecciones);
			ResultSet rs = pstmt.executeQuery();
			pstmt = conexion.getConexion().prepareStatement(consultaAeropuertos);
			rs = pstmt.executeQuery();
			if(bPrivado) {
				pstmt = conexion.getConexion().prepareStatement(consultaPrivado);
				rs = pstmt.executeQuery();
			}else {
				pstmt = conexion.getConexion().prepareStatement(consultaPublica);
				rs = pstmt.executeQuery();
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
