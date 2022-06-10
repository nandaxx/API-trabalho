package com.residencia.commerce.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Pedido{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer idPedido;

	@Column(name = "data_pedido")
	@NotNull(message = "O campo está vazio")
	private Date dataPedido;

	@Column(name = "data_entrega")
	@NotNull(message = "O campo está vazio")
	private Date dataEntregaPedido;

	@Column(name = "data_envio")
	@NotNull(message = "O campo está vazio")
	private Date dataEnvioPedido;

	@Column(name = "status")
	@NotNull(message = "O campo está vazio")
	private Boolean statusPedido;

	@OneToMany(targetEntity = ItemPedido.class, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_pedido")
	private List<ItemPedido> itemPedidoList;

	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
	private Cliente cliente;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntregaPedido() {
		return dataEntregaPedido;
	}

	public void setDataEntregaPedido(Date dataEntregaPedido) {
		this.dataEntregaPedido = dataEntregaPedido;
	}

	public Date getDataEnvioPedido() {
		return dataEnvioPedido;
	}

	public void setDataEnvioPedido(Date dataEnvioPedido) {
		this.dataEnvioPedido = dataEnvioPedido;
	}

	public Boolean getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(Boolean statusPedido) {
		this.statusPedido = statusPedido;
	}

	public List<ItemPedido> getItemPedidoList() {
		return itemPedidoList;
	}

	public void setItemPedidoList(List<ItemPedido> itemPedidoList) {
		this.itemPedidoList = itemPedidoList;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
