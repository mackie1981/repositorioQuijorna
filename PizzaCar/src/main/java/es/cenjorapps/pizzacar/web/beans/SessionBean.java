package es.cenjorapps.pizzacar.web.beans;

import java.io.IOException;
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

import org.springframework.security.core.context.SecurityContextHolder;

import es.cenjorapps.pizzacar.core.data.model.DetallePedidoEntity;
import es.cenjorapps.pizzacar.core.data.model.EstadoEntity;
import es.cenjorapps.pizzacar.core.data.model.PedidoEntity;
import es.cenjorapps.pizzacar.core.data.model.ProductoEntity;
import es.cenjorapps.pizzacar.core.data.model.UsuarioEntity;
import es.cenjorapps.pizzacar.core.service.EstadoService;
import es.cenjorapps.pizzacar.core.service.PedidoService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;

public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private AperturaBean aperturaBean;
	private String usuario;
	private String rol;
	private UsuarioEntity usuarioActivo;
	private PedidoEntity pedidoCliente;
	private PedidoService pedidoService;
	private EstadoService estadoService;
	private int contadorCarrito;
	
	
	@PostConstruct
	public void initializeSessionBean() {
		pedidoService = (PedidoService) ApplicationContextUtils.getBean("pedidoServiceBean");
		estadoService = (EstadoService) ApplicationContextUtils.getBean("estadoServiceBean");
		usuario = "Invitado";
		rol = "ROLE_INVITADO";
		//Iniciamos el carrito
		contadorCarrito = 0;
		pedidoCliente = new PedidoEntity();
		List<DetallePedidoEntity> productosPedido = new ArrayList<DetallePedidoEntity>();
		pedidoCliente.setTotal(new BigDecimal(0));
		pedidoCliente.setDetalles(productosPedido);
	}
	
	public AperturaBean getAperturaBean() {
		return aperturaBean;
	}

	public void setAperturaBean(AperturaBean aperturaBean) {
		this.aperturaBean = aperturaBean;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public UsuarioEntity getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(UsuarioEntity usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}

	public PedidoEntity getPedidoCliente() {
		return pedidoCliente;
	}

	public void setPedidoCliente(PedidoEntity pedidoCliente) {
		this.pedidoCliente = pedidoCliente;
	}
	
	public PedidoService getPedidoService() {
		return pedidoService;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	public EstadoService getEstadoService() {
		return estadoService;
	}

	public void setEstadoService(EstadoService estadoService) {
		this.estadoService = estadoService;
	}

	public int getContadorCarrito() {
		return contadorCarrito;
	}

	public void setContadorCarrito(int contadorCarrito) {
		this.contadorCarrito = contadorCarrito;
	}

	public boolean esAdmin() {
		if(rol.equals("ROLE_ADMIN"))
			return true;
		return false;
	}
	
	public boolean esEmpleado() {
		if(rol.equals("ROLE_EMPLEADO") || rol.equals("ROLE_ADMIN"))
			return true;
		return false;
	}
	
	public boolean esRegistrado() {
		if(rol.equals("ROLE_REGIS"))
			return true;
		return false;
	}
	
	public void salir(boolean confirmado) {
		if (confirmado) {             	
			this.usuario = "Invitado";
			this.rol = "ROLE_INVITADO";
			this.contadorCarrito = 0;
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			SecurityContextHolder.getContext().setAuthentication(null);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
          	FacesMessage msg = new FacesMessage("Cerrar sesión cancelado");
        	FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void resetearCompleto() {
		this.usuario = "Invitado";
		this.rol = "ROLE_INVITADO";
		this.contadorCarrito = 0;
		this.pedidoCliente = null;
	}
	
	public void sumar(ProductoEntity producto) {
		
		for(DetallePedidoEntity detalle : pedidoCliente.getDetalles()) {
			if(detalle.getProducto().getId() == producto.getId()) {
				detalle.setCantidad(detalle.getCantidad() + 1);
				break;
			}
		}
		//Recalculamos total pedido
		BigDecimal total = pedidoCliente.getTotal();
		total = total.add(producto.getPrecio());
		pedidoCliente.setTotal(total);
	}
	
	public void restar(ProductoEntity producto) {
		for(DetallePedidoEntity detalle : pedidoCliente.getDetalles()) {
			if(detalle.getProducto().getId() == producto.getId()) {
				if(detalle.getCantidad() == 1) {
					//Eliminar el producto
					pedidoCliente.getDetalles().remove(detalle);
					//actualizar contador carrito
					this.setContadorCarrito(this.getContadorCarrito() - 1);
				} else {
					detalle.setCantidad(detalle.getCantidad() -  1);
				}
				break;
			}
		}
		//Recalculamos total pedido
		BigDecimal total = pedidoCliente.getTotal();
		total = total.subtract(producto.getPrecio());
		pedidoCliente.setTotal(total);
	}
	
	public void pedir() {
		//Controlar este habilitado el poder hacer pedidos (es decir que este abierto el local)
		if(!aperturaBean.isLocalAbierto()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nuestro local está cerrado, intentalo más tarde", ""));
		} else if(null == usuarioActivo) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes loguearte primero para pedir, el contenido del carro no se borrará", ""));
		} else if("ROLE_INVITADO".equals(usuarioActivo.getRol().getNombre())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes loguearte primero para pedir, el contenido del carro no se borrará", ""));
		} else if(pedidoCliente.getTotal().compareTo(new BigDecimal(15)) == -1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El pedido mínimo son 15€, no es posible realizar el pedido. Continua añadiendo más productos al carro", ""));
		} else {
			try {
				EstadoEntity estado = estadoService.findByNombre("REGISTRADO");
				pedidoCliente.setEstado(estado);
				LocalDate fechaActual = LocalDate.now();
				Date fechaSql = Date.valueOf(fechaActual);
				pedidoCliente.setFechaPedido(fechaSql);
				LocalTime horaActual = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
				Time horaSql = Time.valueOf(horaActual);
				pedidoCliente.setHoraPedido(horaSql);
				//Guardamos pedido
				pedidoService.save(pedidoCliente);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido realizado correctamente, en breve lo tendrás en la puerta de casa", ""));
				contadorCarrito = 0;
				pedidoCliente = new PedidoEntity();
				pedidoCliente.setUsuario(usuarioActivo);
				List<DetallePedidoEntity> productosPedido = new ArrayList<DetallePedidoEntity>();
				pedidoCliente.setTotal(new BigDecimal(0));
				pedidoCliente.setDetalles(productosPedido);
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error guardando pedido", ""));
				e.printStackTrace();
			}
		}
	}
	
	public void abrirPedidos( ) {
		if(!aperturaBean.isLocalAbierto())
			aperturaBean.setLocalAbierto(true);
	}
	
	public void cerrarPedidos( ) {
		if(aperturaBean.isLocalAbierto())
			aperturaBean.setLocalAbierto(false);
	}
}
