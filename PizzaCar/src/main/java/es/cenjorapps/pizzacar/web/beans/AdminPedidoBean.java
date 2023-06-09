package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;

import es.cenjorapps.pizzacar.core.data.model.DetallePedidoEntity;
import es.cenjorapps.pizzacar.core.data.model.EstadoEntity;
import es.cenjorapps.pizzacar.core.data.model.PedidoEntity;
import es.cenjorapps.pizzacar.core.data.model.ProductoEntity;
import es.cenjorapps.pizzacar.core.data.model.UsuarioEntity;
import es.cenjorapps.pizzacar.core.service.EstadoService;
import es.cenjorapps.pizzacar.core.service.PedidoService;
import es.cenjorapps.pizzacar.core.service.ProductoService;
import es.cenjorapps.pizzacar.core.service.UsuarioService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;
import es.cenjorapps.pizzacar.util.ManagedBeanUtils;

public class AdminPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private SessionBean sessionBean;
	private long idPedido;

	private long idUsuario;
	private long idEstado;
	private long idProducto;
	private Integer cantidad;

	private Date fechaPedido;
	private Time horaPedido;
	private BigDecimal total;
	private String observaciones;

	private UsuarioService usuarioService;
	private EstadoService estadoService;
	private ProductoService productoService;
	private PedidoService pedidoService;
	private List<UsuarioEntity> listaUsuarios;
	private List<SelectItem> listaUsuariosCombo = null;
	private List<EstadoEntity> listaEstados;
	private List<SelectItem> listaEstadosCombo = null;
	private List<ProductoEntity> listaProductos;
	private List<SelectItem> listaProductosCombo = null;

	private PedidoEntity pedidoAlta;
	private PedidoEntity pedidoSeleccionado;

	private boolean esAlta;
	private boolean esVisualizacion;
	private boolean esEdicion;

	@PostConstruct
	public void initializeAdminPedidoBean() {
		try {
			usuarioService = (UsuarioService) ApplicationContextUtils.getBean("usuarioServiceBean");
			estadoService = (EstadoService) ApplicationContextUtils.getBean("estadoServiceBean");
			productoService = (ProductoService) ApplicationContextUtils.getBean("productoServiceBean");
			pedidoService = (PedidoService) ApplicationContextUtils.getBean("pedidoServiceBean");
			listaUsuarios = usuarioService.findAllOrderByUsername();
			listaEstados = estadoService.findAll();
			listaProductos = productoService.findAllOrderByNombre();
			listaUsuariosCombo = ManagedBeanUtils.crearComboUsuarios(listaUsuarios, true);
			listaEstadosCombo = ManagedBeanUtils.crearComboEstados(listaEstados, true);
			listaProductosCombo = ManagedBeanUtils.crearComboProductos(listaProductos, true);
			esAlta = true;
			esVisualizacion = false;
			esEdicion = false;
			pedidoAlta = new PedidoEntity();
			List<DetallePedidoEntity> productosPedido = new ArrayList<DetallePedidoEntity>();
			pedidoAlta.setTotal(new BigDecimal(0));
			pedidoAlta.setDetalles(productosPedido);
			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
			Object o = flash.get("objeto");
			Object camposActivados = flash.get("edicion");
			Boolean edicion = null;
			if (o instanceof PedidoEntity)
				pedidoSeleccionado = (PedidoEntity) o;
			if (null != pedidoSeleccionado) {
				if (camposActivados instanceof Boolean) {
					edicion = (Boolean) camposActivados;
				}
				if (null != edicion) {
					esAlta = false;
					if (edicion)
						esEdicion = true;
					else
						esVisualizacion = true;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public EstadoService getEstadoService() {
		return estadoService;
	}

	public void setEstadoService(EstadoService estadoService) {
		this.estadoService = estadoService;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public PedidoService getPedidoService() {
		return pedidoService;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	public List<UsuarioEntity> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UsuarioEntity> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<SelectItem> getListaUsuariosCombo() {
		return listaUsuariosCombo;
	}

	public void setListaUsuariosCombo(List<SelectItem> listaUsuariosCombo) {
		this.listaUsuariosCombo = listaUsuariosCombo;
	}

	public List<EstadoEntity> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<EstadoEntity> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public List<SelectItem> getListaEstadosCombo() {
		return listaEstadosCombo;
	}

	public void setListaEstadosCombo(List<SelectItem> listaEstadosCombo) {
		this.listaEstadosCombo = listaEstadosCombo;
	}

	public List<ProductoEntity> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<ProductoEntity> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<SelectItem> getListaProductosCombo() {
		return listaProductosCombo;
	}

	public void setListaProductosCombo(List<SelectItem> listaProductosCombo) {
		this.listaProductosCombo = listaProductosCombo;
	}

	public PedidoEntity getPedidoAlta() {
		return pedidoAlta;
	}

	public void setPedidoAlta(PedidoEntity pedidoAlta) {
		this.pedidoAlta = pedidoAlta;
	}

	public PedidoEntity getPedidoSeleccionado() {
		return pedidoSeleccionado;
	}

	public void setPedidoSeleccionado(PedidoEntity pedidoSeleccionado) {
		this.pedidoSeleccionado = pedidoSeleccionado;
	}

	public boolean isEsAlta() {
		return esAlta;
	}

	public void setEsAlta(boolean esAlta) {
		this.esAlta = esAlta;
	}

	public boolean isEsVisualizacion() {
		return esVisualizacion;
	}

	public void setEsVisualizacion(boolean esVisualizacion) {
		this.esVisualizacion = esVisualizacion;
	}

	public boolean isEsEdicion() {
		return esEdicion;
	}

	public void setEsEdicion(boolean esEdicion) {
		this.esEdicion = esEdicion;
	}

	public void addProductoAlta() {
		if (null == cantidad || cantidad == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes poner la cantidad", ""));
		} else if (cantidad < 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cantidad no puede ser negativa", ""));
		} else {
			if (idProducto != -1) {
				// Lo añadimos y si no existe lo sumamos al contador
				ProductoEntity productoSeleccionado = ManagedBeanUtils.encontrarProducto(listaProductos, idProducto);
				pedidoAlta.addDetalle(productoSeleccionado, cantidad);

				// Recalculamos el total del pedido
				BigDecimal total = pedidoAlta.getTotal();
				double totalNuevo = productoSeleccionado.getPrecio().doubleValue() * cantidad;
				total = total.add(new BigDecimal(totalNuevo));
				pedidoAlta.setTotal(total);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Añadido " + cantidad + " " + productoSeleccionado.getNombre(), ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "No has seleecionado producto", ""));
			}

		}
		idProducto = -1;
		cantidad = 0;
	}

	public void sumarAlta(ProductoEntity producto) {

		for (DetallePedidoEntity detalle : pedidoAlta.getDetalles()) {
			if (detalle.getProducto().getId() == producto.getId()) {
				detalle.setCantidad(detalle.getCantidad() + 1);
				break;
			}
		}
		// Recalculamos total pedido
		BigDecimal total = pedidoAlta.getTotal();
		total = total.add(producto.getPrecio());
		pedidoAlta.setTotal(total);
	}

	public void restarAlta(ProductoEntity producto) {
		for (DetallePedidoEntity detalle : pedidoAlta.getDetalles()) {
			if (detalle.getProducto().getId() == producto.getId()) {
				if (detalle.getCantidad() == 1) {
					// Eliminar el producto
					pedidoAlta.getDetalles().remove(detalle);
				} else {
					detalle.setCantidad(detalle.getCantidad() - 1);
				}
				break;
			}
		}
		// Recalculamos total pedido
		BigDecimal total = pedidoAlta.getTotal();
		total = total.subtract(producto.getPrecio());
		pedidoAlta.setTotal(total);
	}
	
	public void sumar(ProductoEntity producto) {

		for (DetallePedidoEntity detalle : pedidoSeleccionado.getDetalles()) {
			if (detalle.getProducto().getId() == producto.getId()) {
				detalle.setCantidad(detalle.getCantidad() + 1);
				break;
			}
		}
		// Recalculamos total pedido
		BigDecimal total = pedidoSeleccionado.getTotal();
		total = total.add(producto.getPrecio());
		pedidoSeleccionado.setTotal(total);
	}

	public void restar(ProductoEntity producto) {
		for (DetallePedidoEntity detalle : pedidoSeleccionado.getDetalles()) {
			if (detalle.getProducto().getId() == producto.getId()) {
				if (detalle.getCantidad() == 1) {
					// Eliminar el producto
					pedidoSeleccionado.getDetalles().remove(detalle);
				} else {
					detalle.setCantidad(detalle.getCantidad() - 1);
				}
				break;
			}
		}
		// Recalculamos total pedido
		BigDecimal total = pedidoSeleccionado.getTotal();
		total = total.subtract(producto.getPrecio());
		pedidoSeleccionado.setTotal(total);
	}

	public void pedir() {
		// Controlar este habilitado el poder hacer pedidos (es decir que este abierto
		// el local)
		if (!sessionBean.getAperturaBean().isLocalAbierto()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Nuestro local está cerrado, intentalo más tarde", ""));
		} else if ("ROLE_INVITADO".equals(sessionBean.getUsuarioActivo().getRol().getNombre())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Debes loguearte primero para pedir, el contenido del carro no se borrará", ""));
		} else if (pedidoAlta.getTotal().compareTo(new BigDecimal(15)) == -1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"El pedido mínimo son 15€, no es posible realizar el pedido. Continua añadiendo más productos al carro",
					""));
		} else {
			try {
				EstadoEntity estado = ManagedBeanUtils.encontrarEstado(listaEstados, idEstado);
				UsuarioEntity usuario = ManagedBeanUtils.encontrarUsuario(listaUsuarios, idUsuario);
				pedidoAlta.setUsuario(usuario);
				pedidoAlta.setEstado(estado);
				LocalDate fechaActual = LocalDate.now();
				Date fechaSql = Date.valueOf(fechaActual);
				pedidoAlta.setFechaPedido(fechaSql);
				LocalTime horaActual = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
				Time horaSql = Time.valueOf(horaActual);
				pedidoAlta.setHoraPedido(horaSql);
				// Guardamos pedido
				pedidoService.save(pedidoAlta);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Pedido realizado correctamente, en breve lo tendrás en la puerta de casa", ""));
				sessionBean.setContadorCarrito(0);
				pedidoAlta = new PedidoEntity();
				pedidoAlta.setUsuario(ManagedBeanUtils.encontrarUsuario(listaUsuarios, idUsuario));
				List<DetallePedidoEntity> productosPedido = new ArrayList<DetallePedidoEntity>();
				pedidoAlta.setTotal(new BigDecimal(0));
				pedidoAlta.setDetalles(productosPedido);
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error guardando pedido", ""));
				e.printStackTrace();
			}
		}
	}

	public void modificar() {
		// Controlar este habilitado el poder hacer pedidos (es decir que este abierto
		// el local)
		if (!sessionBean.getAperturaBean().isLocalAbierto()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Nuestro local está cerrado, intentalo más tarde", ""));
		} else if ("ROLE_INVITADO".equals(sessionBean.getUsuarioActivo().getRol().getNombre())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Debes loguearte primero para pedir, el contenido del carro no se borrará", ""));
		} else if (pedidoSeleccionado.getTotal().compareTo(new BigDecimal(15)) == -1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"El pedido mínimo son 15€, no es posible realizar el pedido. Continua añadiendo más productos al carro", ""));
		} else {
			try {
				EstadoEntity estado = estadoService.findByNombre("REGISTRADO");
				pedidoSeleccionado.setEstado(estado);
				LocalDate fechaActual = LocalDate.now();
				Date fechaSql = Date.valueOf(fechaActual);
				pedidoSeleccionado.setFechaPedido(fechaSql);
				LocalTime horaActual = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
				Time horaSql = Time.valueOf(horaActual);
				pedidoSeleccionado.setHoraPedido(horaSql);
				// Actualizamos pedido
				pedidoService.update(pedidoSeleccionado);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Pedido realizado correctamente, en breve lo tendrás en la puerta de casa", ""));
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error modificando pedido", ""));
				e.printStackTrace();
			}
		}
	}

	public String atras() {
		return "admin.xhtml?faces-redirect=true";
	}

}
