package es.cenjorapps.pizzacar.core.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
@NamedQueries({
	@NamedQuery(name="PedidoEntity.findAll", query="SELECT p FROM PedidoEntity p"),
	@NamedQuery(name="PedidoEntity.findNextPedido", query="SELECT p FROM PedidoEntity p WHERE p.estado.nombre = 'REGISTRADO' ORDER BY p.fechaPedido, p.horaPedido"),
	@NamedQuery(name="PedidoEntity.findById", query="SELECT p FROM PedidoEntity p WHERE p.id = :id"),
})
public class PedidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado")
    private EstadoEntity estado;

    @Column(name = "fecha_pedido")
    private Date fechaPedido;
    
    @Column(name = "hora_pedido")
    private Time horaPedido;
    
    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "observaciones")
    private String observaciones;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetallePedidoEntity> detalles = new ArrayList<>();
    
    public PedidoEntity() {
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public EstadoEntity getEstado() {
		return estado;
	}

	public void setEstado(EstadoEntity estado) {
		this.estado = estado;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	
	public Time getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(Time horaPedido) {
		this.horaPedido = horaPedido;
	}

	public BigDecimal getTotal() {
		return total.setScale(2, RoundingMode.HALF_UP);
	}

	public void setTotal(BigDecimal total) {
		BigDecimal valorConDosDecimales = total.setScale(2, RoundingMode.HALF_UP);
		this.total = valorConDosDecimales;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<DetallePedidoEntity> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedidoEntity> detalles) {
		this.detalles = detalles;
	}
	
	public boolean addDetalle(ProductoEntity producto, int cantidad) {
		//Si no hay producto igual en la lista lo añadimos, sino sumamos cantidad
		boolean existe = false;
		for(DetallePedidoEntity detalle : detalles) {
			if(detalle.getProducto().getId()==producto.getId()) {
				detalle.setCantidad(detalle.getCantidad() + cantidad);
				existe = true;
				break;
			}
		}
		if(!existe) {
			DetallePedidoEntity det = new DetallePedidoEntity();
			det.setPedido(this);
			det.setProducto(producto);
			det.setCantidad(cantidad);
			detalles.add(det);
		}	
		return existe;
	}
	
}
