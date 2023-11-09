package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Avion;
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
			while(rs.next()) {id=rs.getInt(1)+1;}
			
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
			int num_socio=rt.getSocios();
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
			pstmt.executeUpdate();
			pstmt.executeUpdate(consultaAeropuertos);
			if (bPrivado) {
				pstmt.executeUpdate(consultaPrivado);
			}else {
				pstmt.executeUpdate(consultaPublica);
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void borrarRegistro(RegistroTabla r,boolean bPrivado) {
		String consultaIdDireccion = "SELECT id_direccion FROM aeropuertos WHERE id = " + r.getId() + ";";
		String consultaAeropuerto = "DELETE FROM aeropuertos WHERE id = " + r.getId() + ";";
		String consultaPri = "DELETE FROM aeropuertos_privados WHERE id_aeropuerto = " + r.getId() + ";";
		String consultaPub = "DELETE FROM aeropuertos_publicos WHERE id_aeropuerto = " + r.getId() + ";";
		
		PreparedStatement pstmt;
		int id=-1;
		try {
			pstmt = conexion.getConexion().prepareStatement(consultaIdDireccion);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				id=rs.getInt("id_direccion");
			}
			rs.close();
			if(bPrivado) {				
				pstmt.executeUpdate(consultaPri);
			}else {
				pstmt.executeUpdate(consultaPub);
			}
			
			pstmt.executeUpdate(consultaAeropuerto);
			String consultaDireccion = "DELETE FROM direcciones WHERE id = " + id + ";";
			pstmt.executeUpdate(consultaDireccion);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modificarRegistro(RegistroTabla rt,boolean privado) {		
		int id=-1;
		String consultaAeropuerto="UPDATE aeropuertos SET nombre = '"+rt.getNombre()+"',anio_inauguracion = "+rt.getAnio()+",capacidad = "+rt.getCapacidad()+
				" WHERE id = "+rt.getId()+";";
		String consultaPri = "UPDATE aeropuertos_privados SET numero_socios = "+rt.getSocios()+" WHERE id_aeropuerto = "+rt.getId();
		String consultaPub= "UPDATE aeropuertos_publicos SET financiacion = "+rt.getFinanciacion()+",num_trabajadores = "+rt.getNum_trabajadores()+
				" WHERE id_aeropuerto = "+rt.getId()+";";
		String consultaIdDirecciones="SELECT id_direccion from aeropuertos where id = "+rt.getId()+";";
		String consultaDirecciones="UPDATE direcciones SET pais = '"+rt.getPais()+"',ciudad = '"+rt.getCiudad()+"',calle = '"+rt.getCalle()+"',numero = "+
		rt.getNumero()+" WHERE id = "+id+";";
		
		PreparedStatement pstmt;
		try {
			conexion = new ConexionBD();
			pstmt = conexion.getConexion().prepareStatement(consultaIdDirecciones);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				id=rs.getInt("id_direccion");
			}
			rs.close();
			if(privado) {
				pstmt.executeUpdate(consultaPri);
			}else {
				pstmt.executeUpdate(consultaPub);
			}
			
			pstmt.executeUpdate(consultaAeropuerto);
			pstmt.executeUpdate(consultaDirecciones);
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	public ObservableList<Avion>cargarAviones(int ida){
		ObservableList<Avion>listaAvion= FXCollections.observableArrayList();
		String consulta = "SELECT * FROM aviones WHERE id_aeropuerto = "+ida;
		try {
			conexion = new ConexionBD();
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {				
				int id = rs.getInt("id");
				String modelo = rs.getString("modelo");
				Integer num_asiento = rs.getInt(3);
				Integer velocidad = rs.getInt("velocidad_maxima");
				Boolean activado = rs.getBoolean("activado");
				Integer id_aeropuerto = rs.getInt("id_aeropuerto");
				listaAvion.add(new Avion(id, modelo, num_asiento, velocidad, id_aeropuerto, activado));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAvion;
	}
}
