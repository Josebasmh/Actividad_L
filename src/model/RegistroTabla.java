package model;

import java.util.Objects;

public class RegistroTabla {

	private int id,anio,capacidad,numero,socios,financiacion,numeroTrabajadores;
	private String nombre,pais,ciudad,calle;
	
	/**
	 * Constructor para registro de aeropuerto privado.
	 * @param i id
	 * @param no nombre
	 * @param p pais
	 * @param ci ciudad
	 * @param ca calle
	 * @param nu número
	 * @param a año
	 * @param c capacidad
	 * @param ns Nº socios
	 */
	public RegistroTabla(int i,String no,String p,String ci,String ca,int nu,int a, int c,int ns) {
		id=i;
		nombre=no;
		pais=p;
		ciudad=ci;
		calle=ca;
		setNumero(nu);
		anio=a;
		capacidad=c;
		socios=ns;
	}
	/**
	 * Constructor para registro de aeropuerto público.
	 * @param i id
	 * @param no nombre
	 * @param p pais
	 * @param ci ciudad
	 * @param ca calle
	 * @param nu número
	 * @param a año
	 * @param c capacidad
	 * @param f financiación
	 * @param t Nº trabajadores
	 */
	public RegistroTabla(int i,String no,String p,String ci,String ca,int nu,int a,int c,int f,int t) {
		id=i;
		nombre=no;
		pais=p;
		ciudad=ci;
		calle=ca;
		setNumero(nu);
		anio=a;
		capacidad=c;
	}
	// Metodos getter y setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getSocios() {
		return socios;
	}
	public void setSocios(int socios) {
		this.socios = socios;
	}
	public int getFinanciacion() {
		return financiacion;
	}
	public void setFinanciacion(int financiacion) {
		this.financiacion = financiacion;
	}
	public int getNumeroTrabajadores() {
		return numeroTrabajadores;
	}
	public void setNumeroTrabajadores(int numeroTrabajadores) {
		this.numeroTrabajadores = numeroTrabajadores;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	// un aeropuerto será el mismo cuando tenga el mismo id.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroTabla other = (RegistroTabla) obj;
		return id == other.id;
	}
}
