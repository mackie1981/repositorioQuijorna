package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.apache.commons.lang3.StringUtils;

import es.cenjorapps.pizzacar.core.data.model.CategoriaEntity;
import es.cenjorapps.pizzacar.core.service.CategoriaService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;

public class AdminCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String descripcion;

	private CategoriaService categoriaService;
	
	private CategoriaEntity categoriaSeleccionada;
	private boolean esAlta;
	private boolean esVisualizacion;
	private boolean esEdicion;

	@PostConstruct
	public void initializeAdminCategoriaBean() {
		categoriaService = (CategoriaService) ApplicationContextUtils.getBean("categoriaServiceBean");
		esAlta = true;
    	esVisualizacion = false;
    	esEdicion = false;
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		Object o = flash.get("objeto");
		Object camposActivados = flash.get("edicion");
		Boolean edicion = null;
		if (o instanceof CategoriaEntity)
			categoriaSeleccionada = (CategoriaEntity) o;
		if (null != categoriaSeleccionada) {
			if(camposActivados instanceof Boolean) {
				edicion = (Boolean) camposActivados;
			}
			if(null != edicion) {
				esAlta = false;
				if(edicion) 
					esEdicion = true;
				else
					esVisualizacion = true;
			}
			id = categoriaSeleccionada.getId();
			nombre = categoriaSeleccionada.getNombre();
			descripcion = categoriaSeleccionada.getDescripcion();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public CategoriaEntity getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}

	public void setCategoriaSeleccionada(CategoriaEntity categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
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

	public String guardar() {
		// Validacion de campos
		if (StringUtils.isEmpty(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es obligatorio", ""));
			return null;
		}
		if (StringUtils.isBlank(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es obligatorio", ""));
			return null;
		}
		if (StringUtils.isEmpty(descripcion)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "La descripción es obligatoria", ""));
			return null;
		}
		if (StringUtils.isBlank(descripcion)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "La descripción es obligatoria", ""));
			return null;
		}

		// Si llega hasta aquí los datos son validos, pero debemos comprobar que no
		// exista el nombre
		try {
			if (!categoriaService.esNombreValido(nombre)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Este nombre ya existe, debes elegir otro distinto", ""));
				return null;
			}
			// No hay errores , creamos la categoria y la insertamos
			CategoriaEntity c = new CategoriaEntity();
			c.setNombre(nombre);
			c.setDescripcion(descripcion);
			categoriaService.save(c);
			return "admin.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error guardando categoria", ""));
			e.printStackTrace();
			return null;
		}
	}

	public String modificar() {
		// Validacion de campos
		if (StringUtils.isEmpty(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es obligatorio", ""));
			return null;
		}
		if (StringUtils.isBlank(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es obligatorio", ""));
			return null;
		}
		if (StringUtils.isEmpty(descripcion)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "La descripción es obligatoria", ""));
			return null;
		}
		if (StringUtils.isBlank(descripcion)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "La descripción es obligatoria", ""));
			return null;
		}

		// Si llega hasta aquí los datos son validos, pero debemos comprobar que no
		// exista el nombre
		try {
			if (!categoriaService.esNombreValidoUpdate(nombre, id)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Este nombre ya existe, debes elegir otro distinto", ""));
				return null;
			}
			// No hay errores , creamos la categoria y la insertamos
			CategoriaEntity c = new CategoriaEntity();
			c.setId(id);
			c.setNombre(nombre);
			c.setDescripcion(descripcion);
			categoriaService.update(c);
			return "admin.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error modificando categoria", ""));
			e.printStackTrace();
			return null;
		}
	}
	
	public String atras() {
		return "admin.xhtml?faces-redirect=true";
	}
	
}
