package com.example.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="detalles_pedido")
public class DetallesPedido {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="id_pedido")
	private Integer idPedido;
	@Column(name="id_producto")
	private Integer idProducto;
	@Column(name="precio_unidad")
	private Float precioUnidad;
	@Column(name="unidades")
	private Integer unidades;
	@Column(name="impuesto")
	private Float impuesto;
	@Column(name="total")
	private Double total;
	@Column(name="nombre")
	private String nombre;
	@Column(name="imagen")
	private String imagen;
	@Column(name="cancelar_linea")
	private Integer cancelarLinea;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public DetallesPedido() {
	}

	public DetallesPedido(Integer idPedido, Integer idProducto, Float precioUnidad, Integer unidades, Float impuesto,
			Double total, String nombre, String imagen) {
		this.idPedido = idPedido;
		this.idProducto = idProducto;
		this.precioUnidad = precioUnidad;
		this.unidades = unidades;
		this.impuesto = impuesto;
		this.total = total;
		this.nombre= nombre;
		this.imagen=imagen;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Float getPrecioUnidad() {
		return this.precioUnidad;
	}

	public void setPrecioUnidad(Float precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public Integer getUnidades() {
		return this.unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Float getImpuesto() {
		return this.impuesto;
	}

	public void setImpuesto(Float impuesto) {
		this.impuesto = impuesto;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getCancelarLinea() {
		return cancelarLinea;
	}

	public void setCancelarLinea(Integer cancelarLinea) {
		this.cancelarLinea = cancelarLinea;
	}
	
	
}
