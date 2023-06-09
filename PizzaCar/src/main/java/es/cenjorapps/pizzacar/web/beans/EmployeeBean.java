package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import es.cenjorapps.pizzacar.core.data.model.EstadoEntity;
import es.cenjorapps.pizzacar.core.data.model.PedidoEntity;
import es.cenjorapps.pizzacar.core.service.EstadoService;
import es.cenjorapps.pizzacar.core.service.PedidoService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;

public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PedidoService pedidoService;
	private EstadoService estadodoService;
	private PedidoEntity proximoPedido;
	private boolean hayPedidosSinHacer;
	
	@PostConstruct
	public void initializeEmployeeBean() {
		pedidoService = (PedidoService) ApplicationContextUtils.getBean("pedidoServiceBean");
		estadodoService = (EstadoService) ApplicationContextUtils.getBean("estadoServiceBean");
		hayPedidosSinHacer = false;
	}

	public PedidoService getPedidoService() {
		return pedidoService;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	public EstadoService getEstadodoService() {
		return estadodoService;
	}

	public void setEstadodoService(EstadoService estadodoService) {
		this.estadodoService = estadodoService;
	}

	public PedidoEntity getProximoPedido() {
		return proximoPedido;
	}

	public void setProximoPedido(PedidoEntity proximoPedido) {
		this.proximoPedido = proximoPedido;
	}

	public boolean isHayPedidosSinHacer() {
		return hayPedidosSinHacer;
	}

	public void setHayPedidosSinHacer(boolean hayPedidosSinHacer) {
		this.hayPedidosSinHacer = hayPedidosSinHacer;
	}
	
	public void buscarProximoPedido() {
		if(hayPedidosSinHacer)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aun no has acabado el pedido que tienes", ""));
		else {
			try {
				proximoPedido = pedidoService.findNextPedido();
				if(null != proximoPedido)
					hayPedidosSinHacer = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void preparar() {
		//Volver a mirar que no se nos haya adelantado otro trabajadr y ya haya cogido ese pedido y lo este haciendo
		PedidoEntity pedido;
		try {
			pedido = pedidoService.findById(proximoPedido.getId());
			if(!pedido.getEstado().getNombre().equals("REGISTRADO")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Este pedido ya está siendo atendido, busca otro nuevo", ""));
				proximoPedido = null;
				hayPedidosSinHacer = false;
			} else {
				//Lo pasasmos a estado EN PREPARACION
				EstadoEntity estado = estadodoService.findByNombre("EN PREPARACION");
				proximoPedido.setEstado(estado);
				pedidoService.update(proximoPedido);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido en preparación", ""));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error cambiando estado pedido a EN PREPARACION", ""));
			e.printStackTrace();
		}
		 
	}
	
	public void terminar() {
		PedidoEntity pedido;
		try {
			pedido = pedidoService.findById(proximoPedido.getId());
			if(pedido.getEstado().getNombre().equals("TERMINADO") || pedido.getEstado().getNombre().equals("REGISTRADO")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Este pedido está en estado " + pedido.getEstado().getNombre(), ""));
				proximoPedido = null;
				hayPedidosSinHacer = false;
			} else {
				//Lo pasasmos a estado TERMINADO
				EstadoEntity estado = estadodoService.findByNombre("TERMINADO");
				proximoPedido.setEstado(estado);
				pedidoService.update(proximoPedido);
				proximoPedido = null;
				hayPedidosSinHacer = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido terminado", ""));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error cambiando estado pedido a TERMINADO", ""));
			e.printStackTrace();
		}
	}
}
