package es.cenjorapps.pizzacar.web.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import es.cenjorapps.pizzacar.core.data.model.CategoriaEntity;
import es.cenjorapps.pizzacar.core.data.model.PedidoEntity;
import es.cenjorapps.pizzacar.core.data.model.ProductoEntity;
import es.cenjorapps.pizzacar.core.data.model.UsuarioEntity;
import es.cenjorapps.pizzacar.core.service.CategoriaService;
import es.cenjorapps.pizzacar.core.service.PedidoService;
import es.cenjorapps.pizzacar.core.service.ProductoService;
import es.cenjorapps.pizzacar.core.service.UsuarioService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;
import es.cenjorapps.pizzacar.util.ManagedBeanUtils;

public class AdminBean implements Serializable {
	  
	private static final long serialVersionUID = 1L;
	private int seccionSeleccionada;
	private List<SelectItem> listaSecciones;
	private List<UsuarioEntity> listaUsuarios;
	private List<ProductoEntity> listaProductos;
	private List<PedidoEntity> listaPedidos;
	private List<CategoriaEntity> listaCategorias;
	
	private UsuarioService usuarioService;
	private ProductoService productoService;
	private PedidoService pedidoService;
	private CategoriaService categoriaService;
	
	private UsuarioEntity usuarioSeleccionado;
	private ProductoEntity productoSeleccionado;
	private PedidoEntity pedidoSeleccionado;
	private CategoriaEntity categoriaSeleccionado;
	
	@PostConstruct
	public void initializeAdminBean() {
		llenarListaCategorias();
		usuarioService = (UsuarioService) ApplicationContextUtils.getBean("usuarioServiceBean");
		pedidoService = (PedidoService) ApplicationContextUtils.getBean("pedidoServiceBean");
		productoService = (ProductoService) ApplicationContextUtils.getBean("productoServiceBean");
		categoriaService = (CategoriaService) ApplicationContextUtils.getBean("categoriaServiceBean");
	}
	
	public int getSeccionSeleccionada() {
		return seccionSeleccionada;
	}

	public void setSeccionSeleccionada(int seccionSeleccionada) {
		this.seccionSeleccionada = seccionSeleccionada;
	}
	
	public List<SelectItem> getListaSecciones() {
		return listaSecciones;
	}

	public void setListaSecciones(List<SelectItem> listaSecciones) {
		this.listaSecciones = listaSecciones;
	}
	
	public List<UsuarioEntity> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UsuarioEntity> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	public List<ProductoEntity> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<ProductoEntity> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<PedidoEntity> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<PedidoEntity> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public List<CategoriaEntity> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<CategoriaEntity> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
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

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	public UsuarioEntity getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(UsuarioEntity usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public ProductoEntity getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(ProductoEntity productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}
	
	public PedidoEntity getPedidoSeleccionado() {
		return pedidoSeleccionado;
	}

	public void setPedidoSeleccionado(PedidoEntity pedidoSeleccionado) {
		this.pedidoSeleccionado = pedidoSeleccionado;
	}
	
	public CategoriaEntity getCategoriaSeleccionado() {
		return categoriaSeleccionado;
	}

	public void setCategoriaSeleccionado(CategoriaEntity categoriaSeleccionado) {
		this.categoriaSeleccionado = categoriaSeleccionado;
	}

	private void llenarListaCategorias() {
		listaSecciones = new ArrayList<SelectItem>();
		listaSecciones.add(new SelectItem(-1, "Seleccione"));
    	listaSecciones.add(new SelectItem(1, "Usuarios"));
    	listaSecciones.add(new SelectItem(2, "Productos"));
    	listaSecciones.add(new SelectItem(3, "Pedidos"));
    	listaSecciones.add(new SelectItem(4, "Categorias"));
	}
	
	public String buscarSecciones() {
		switch (seccionSeleccionada) {
			case 1:
				try {
					listaUsuarios = usuarioService.findAll();
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error buscando usuarios", ""));
				}
				return "";
			case 2:
				try {
					listaProductos = productoService.findAll();
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error buscando productos", ""));
				}
				return "";
			case 3:
				try {
					listaPedidos = pedidoService.findAll();
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error buscando pedidos", ""));
				}
				return "";
			case 4:
				try {
					listaCategorias = categoriaService.findAll();
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error buscando categorias", ""));
				}
				return "";
			default:
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Seccion seleccionada no válida ", ""));
				return null;
		}
	}
	
	public String nuevoUsuario () {
		return "usuario.xhtml?faces-redirect=true";
	}
	
	public String nuevoProducto () {
		return "producto.xhtml?faces-redirect=true";
	}
	
	public String nuevoPedido () {
		return "pedido.xhtml?faces-redirect=true";
	}
	
	public String nuevaCategoria () {
		return "categoria.xhtml?faces-redirect=true";
	}
	
	public void ver(int categoriaSeleccionada) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.getFlash().put("edicion", false);
			switch (categoriaSeleccionada) {
				case 1:
					externalContext.getFlash().put("objeto", usuarioSeleccionado);
				    externalContext.redirect("usuario.xhtml?faces-redirect=true");
				    break;
				case 2:
					externalContext.getFlash().put("objeto", productoSeleccionado);
					externalContext.redirect("producto.xhtml?faces-redirect=true");
					break;
				case 3:
					externalContext.getFlash().put("objeto", pedidoSeleccionado);
					externalContext.redirect("pedido.xhtml?faces-redirect=true");
				    break;
				case 4:
					externalContext.getFlash().put("objeto", categoriaSeleccionado);
					externalContext.redirect("categoria.xhtml?faces-redirect=true");
				    break;
				default:
					break;
			}
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de IO", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al abrir la visualización", ""));
		}
	}
	
	public void editar(int categoriaSeleccionada) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.getFlash().put("edicion", true);
			switch (categoriaSeleccionada) {
				case 1:
					externalContext.getFlash().put("objeto", usuarioSeleccionado);
				    externalContext.redirect("usuario.xhtml?faces-redirect=true");
				    break;
				case 2:
					externalContext.getFlash().put("objeto", productoSeleccionado);
					externalContext.redirect("producto.xhtml?faces-redirect=true");
					break;
				case 3:
					externalContext.getFlash().put("objeto", pedidoSeleccionado);
					externalContext.redirect("pedido.xhtml?faces-redirect=true");
				    break;
				case 4:
					externalContext.getFlash().put("objeto", categoriaSeleccionado);
					externalContext.redirect("categoria.xhtml?faces-redirect=true");
				    break;
				default:
					break;
			}
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de IO", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al abrir la edición", ""));
		}
		
	}
	
	public void eliminar(int seleccion) {
		try {
			switch (seleccion) {
				case 1:
					usuarioService.delete(usuarioSeleccionado);
					break;
				case 2:
					productoService.delete(productoSeleccionado);
				    ManagedBeanUtils.eliminarImagen(productoSeleccionado);
					break;
				case 3:
					pedidoService.delete(pedidoSeleccionado);
				    break;
				case 4:
					categoriaService.delete(categoriaSeleccionado);
					break;
				default:
					break;
			}
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("admin.xhtml?faces-redirect=true");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar", ""));
		}
	}

}
