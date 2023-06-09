package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import es.cenjorapps.pizzacar.core.data.model.ProductoEntity;
import es.cenjorapps.pizzacar.core.service.ProductoService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;
import es.cenjorapps.pizzacar.util.ManagedBeanUtils;

public class HomeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ProductoService productoService;
	
	private String rutaCarpetaImagenes;
	private List<ProductoEntity> productosDestacados;
	
	@PostConstruct
	public void initializeHomeBean() {
		productoService = (ProductoService) ApplicationContextUtils.getBean("productoServiceBean");
		productosDestacados = productoService.findDestacados();
		rutaCarpetaImagenes = ManagedBeanUtils.getRutaImagenesProducto();
	}
	
	public String getRutaCarpetaImagenes() {
		return rutaCarpetaImagenes;
	}

	public void setRutaCarpetaImagenes(String rutaCarpetaImagenes) {
		this.rutaCarpetaImagenes = rutaCarpetaImagenes;
	}
	
	public List<ProductoEntity> getProductosDestacados() {
		return productosDestacados;
	}

	public void setProductosDestacados(List<ProductoEntity> productosDestacados) {
		this.productosDestacados = productosDestacados;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}
	
}
