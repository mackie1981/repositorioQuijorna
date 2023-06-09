package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import es.cenjorapps.pizzacar.core.data.model.CategoriaEntity;
import es.cenjorapps.pizzacar.core.data.model.ProductoEntity;
import es.cenjorapps.pizzacar.core.service.CategoriaService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;
import es.cenjorapps.pizzacar.util.ManagedBeanUtils;

public class CartaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private SessionBean sessionBean;
	private CategoriaService categoriaService;

	private List<CategoriaEntity> listaCategorias;
	private String rutaCarpetaImagenes;
	
	private ProductoEntity productoSeleccionado;
	private int cantidadSeleccionada;
	private int cantidad;
	
	@PostConstruct
	public void initializeCartaBean() {
		try {
			categoriaService = (CategoriaService) ApplicationContextUtils.getBean("categoriaServiceBean");
			listaCategorias = categoriaService.findAll();
			rutaCarpetaImagenes = ManagedBeanUtils.getRutaImagenesProducto();
			cantidadSeleccionada = 0;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error iniciando CartaBean ", ""));
		}
	}
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public List<CategoriaEntity> getListaCategorias() {
		return listaCategorias;
	}


	public void setListaCategorias(List<CategoriaEntity> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public String getRutaCarpetaImagenes() {
		return rutaCarpetaImagenes;
	}

	public void setRutaCarpetaImagenes(String rutaCarpetaImagenes) {
		this.rutaCarpetaImagenes = rutaCarpetaImagenes;
	}
	
	public ProductoEntity getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(ProductoEntity productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public int getCantidadSeleccionada() {
		return cantidadSeleccionada;
	}

	public void setCantidadSeleccionada(int cantidadSeleccionada) {
		this.cantidadSeleccionada = cantidadSeleccionada;
	}
	
	public void addProducto( ) {
		if(cantidad == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes poner la cantidad", ""));
		} else if(cantidad < 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cantidad no puede ser negativa", ""));
		} else {
			if(null != productoSeleccionado) {
				//Lo añadimos y si no existe lo sumamos al contador
				if(!sessionBean.getPedidoCliente().addDetalle(productoSeleccionado, cantidad)) 
					sessionBean.setContadorCarrito(sessionBean.getContadorCarrito() + 1);
				//Recalculamos el total del pedido
				BigDecimal total = sessionBean.getPedidoCliente().getTotal();
				double totalNuevo = productoSeleccionado.getPrecio().doubleValue() * cantidad;
				total = total.add(new BigDecimal(totalNuevo));
				sessionBean.getPedidoCliente().setTotal(total);	
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Añadido " + cantidad + " " + productoSeleccionado.getNombre(), ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El producto viene nulo", ""));
			}
			
		}
		cantidadSeleccionada = 0;
		cantidad = 0;
	}
	
	public void actualizarCantidad( ) {
		cantidad = cantidadSeleccionada;
	}
}
