package com.example.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedidos")
public class Pedidos {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="id_usuario")
	private int idUsuario;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="metodo_pago")
	private String metodoPago;
	@Column(name="estado")
	private String estado;
	@Column(name="num_factura")
	private String numFactura;
	@Column(name="total")
	private Double total;
	
	public Pedidos() {
		
	}
	
	public Pedidos(int idUsuario, String metodoPago, String estado, String numFactura, double total) {
		this.idUsuario=idUsuario;
		this.metodoPago=metodoPago;
		this.estado=estado;
		this.numFactura=numFactura;
		this.total=total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	
	
}
