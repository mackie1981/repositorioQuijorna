package es.cenjorapps.pizzacar.web.beans;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.UploadedFile;

import es.cenjorapps.pizzacar.core.data.model.CategoriaEntity;
import es.cenjorapps.pizzacar.core.data.model.ProductoEntity;
import es.cenjorapps.pizzacar.core.service.CategoriaService;
import es.cenjorapps.pizzacar.core.service.ProductoService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;
import es.cenjorapps.pizzacar.util.ManagedBeanUtils;

public class AdminProductoBean implements Serializable {
	  
	private static final long serialVersionUID = 1L;
	private long id;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private String imagen;
	private String imagenAnterior;
	private int idCategoria;
	private UploadedFile imagenSeleccionada;
	private String imagenUrl;
	private String destacado;
	
	private ProductoService productoService;
	private CategoriaService categoriaService;
	private List<CategoriaEntity>listaCategorias;
	private List<SelectItem> listaCategoriasCombo = null;
	
	private ProductoEntity productoSeleccionado;
	private boolean esAlta;
	private boolean esVisualizacion;
	private boolean esEdicion;
	
	@PostConstruct
	public void initializeAdminProductoBean() {
    	try {
    		productoService = (ProductoService) ApplicationContextUtils.getBean("productoServiceBean");
    		categoriaService = (CategoriaService) ApplicationContextUtils.getBean("categoriaServiceBean");
			listaCategorias = categoriaService.findAll();
			listaCategoriasCombo = ManagedBeanUtils.crearComboCategorias(listaCategorias, true);
	    	idCategoria = -1;
	    	esAlta = true;
			esVisualizacion = false;
			esEdicion = false;
	    	Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
			Object o = flash.get("objeto");
			Object camposActivados = flash.get("edicion");
			Boolean edicion = null;
			
			if(o instanceof ProductoEntity)
				productoSeleccionado = (ProductoEntity) o;
			if(null != productoSeleccionado) {
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
				id = productoSeleccionado.getId();
				nombre = productoSeleccionado.getNombre();
				if(!StringUtils.isEmpty(productoSeleccionado.getDescripcion()))
					descripcion = productoSeleccionado.getDescripcion();
				precio = productoSeleccionado.getPrecio();
				if(!StringUtils.isEmpty(productoSeleccionado.getImagen())) {
					imagen = productoSeleccionado.getImagen();
					imagenAnterior = imagen;
					imagenUrl = ManagedBeanUtils.getRutaImagenesProducto() + imagen;
				}
				idCategoria = productoSeleccionado.getCategoria().getId();
				destacado = String.valueOf(productoSeleccionado.getDestacado());
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getImagenAnterior() {
		return imagenAnterior;
	}

	public void setImagenAnterior(String imagenAnterior) {
		this.imagenAnterior = imagenAnterior;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
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

	public List<SelectItem> getListaCategoriasCombo() {
		return listaCategoriasCombo;
	}

	public void setListaCategoriasCombo(List<SelectItem> listaCategoriasCombo) {
		this.listaCategoriasCombo = listaCategoriasCombo;
	}

	public ProductoEntity getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(ProductoEntity productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
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
	
	public UploadedFile getImagenSeleccionada() {
		return imagenSeleccionada;
	}

	public void setImagenSeleccionada(UploadedFile imagenSeleccionada) {
		this.imagenSeleccionada = imagenSeleccionada;
	}
	
	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}
	
	public String getDestacado() {
		return destacado;
	}

	public void setDestacado(String destacado) {
		this.destacado = destacado;
	}

	public String guardar() {
		//Validacion de campos
		if(StringUtils.isEmpty(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre es obligatorio", ""));
			return null;
		}
		if(StringUtils.isBlank(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre es obligatorio", ""));
			return null;
		}
		
		if(StringUtils.isEmpty(precio.toString())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El precio es obligatorio", ""));
			return null;
		}
		if(StringUtils.isBlank(precio.toString())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El precio es obligatorio", ""));
			return null;
		}
		
		if(-1 == idCategoria) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes elegir una categoria para el producto", ""));
			return null;
		}
		if(!StringUtils.isEmpty(destacado)) {
			if(!"0".equals(destacado) && !"1".equals(destacado)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Destacado solo admite 0 o 1 como ", ""));
				return null;
			}
		}
		//Si llega hasta aquí los datos son validos, pero debemos comprobar que no exista ni el nombre
		try {
			if(!productoService.esNombreValido(nombre)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este nombre ya existe, debes elegir otro distinto", ""));
				return null;
			}
			
			ProductoEntity p = new ProductoEntity();
			p.setNombre(nombre);
			if(!StringUtils.isEmpty(descripcion)) 
				p.setDescripcion(descripcion);
			if(!StringUtils.isEmpty(destacado)) 
				p.setDestacado(Integer.parseInt(destacado));
			p.setPrecio(precio);
			boolean hayImagen = false;
			if(!StringUtils.isEmpty(imagen) && null != imagenSeleccionada) {
				//Validamos el tamaño y la extension
				if (imagen.toLowerCase().endsWith(".jpg") || imagen.toLowerCase().endsWith(".jpeg") || imagen.toLowerCase().endsWith(".png")) {
					if(imagenSeleccionada.getSize() > 2048000) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La imagen no puede exceder de 2mb", ""));
						return null;
					}
					
					p.setImagen(imagen);
					hayImagen = true;
				} else {
				    // El archivo no tiene una extensión válida
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La imagen debe tener extensión jpg, jpeg o png", ""));
					return null;
				}
				
			}
			p.setCategoria(ManagedBeanUtils.encontrarCategoria(listaCategorias, idCategoria));
			
			
			if(productoService.save(p) && hayImagen) {
				//Guardamos la imagen
				String rutaCarpeta = ManagedBeanUtils.getRutaImagenesProducto();
				File carpetaDestino = new File(rutaCarpeta);
				if(carpetaDestino.exists()) {
					if(null != imagenSeleccionada) {
						try {
							InputStream is = imagenSeleccionada.getInputstream();
			                
			                String rutaFinal = rutaCarpeta + imagen;
			                File archivoGuardar = new File(rutaFinal);
			                if(archivoGuardar.exists())
			                	archivoGuardar.delete();
			                guardarImagen(rutaFinal, is);
			                FacesMessage msg = new FacesMessage(imagen + " guardada con éxito");
			              	FacesContext.getCurrentInstance().addMessage(null, msg);
			            } catch (IOException ex) {
			            	FacesMessage msg = new FacesMessage("Error al guardar el archivo");
		        	      	FacesContext.getCurrentInstance().addMessage(null, msg);
			            	return null;
			            }
					}
				}
			}
			return "admin.xhtml?faces-redirect=true";
		} catch (Exception e1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error guardando producto", ""));
			e1.printStackTrace();
			return null;
		}
    }
	
	public String modificar() {
		//Validacion de campos
		if(StringUtils.isEmpty(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre es obligatorio", ""));
			return null;
		}
		if(StringUtils.isBlank(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre es obligatorio", ""));
			return null;
		}
		
		if(StringUtils.isEmpty(precio.toString())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El precio es obligatorio", ""));
			return null;
		}
		if(StringUtils.isBlank(precio.toString())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El precio es obligatorio", ""));
			return null;
		}
		
		if(-1 == idCategoria) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes elegir una categoria para el producto", ""));
			return null;
		}
		if(!StringUtils.isEmpty(destacado)) {
			if(!"0".equals(destacado) && !"1".equals(destacado)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Destacado solo admite 0 o 1 como ", ""));
				return null;
			}
		}
		//Si llega hasta aquí los datos son validos, pero debemos comprobar que no exista en otro registro distinto al actual ni el username, ni email
		try {
			if(!productoService.esNombreValidoUpdate(nombre, id)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este nombre ya existe, debes elegir otro distinto", ""));
				return null;
			}
			
			//No hay errores, actualizamos
			ProductoEntity p = new ProductoEntity();
			p.setId(id);
			p.setNombre(nombre);
			if(!StringUtils.isEmpty(descripcion)) 
				p.setDescripcion(descripcion);
			if(!StringUtils.isEmpty(destacado)) 
				p.setDestacado(Integer.parseInt(destacado));
			p.setPrecio(precio);
			boolean hayImagen = false;
			if(!StringUtils.isEmpty(imagen) && null != imagenSeleccionada) {
				if (imagen.toLowerCase().endsWith(".jpg") || imagen.toLowerCase().endsWith(".jpeg") || imagen.toLowerCase().endsWith(".png")) {
					if(imagenSeleccionada.getSize() > 2048000) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La imagen no puede exceder de 2mb", ""));
						return null;
					}
					
					//Si la imagen a cambiado la seteamos
					if(!imagen.equals(imagenAnterior)) {
						p.setImagen(imagen);
						hayImagen = true;
					} else {
						p.setImagen(productoSeleccionado.getImagen());
					}
					
				} else {
				    // El archivo no tiene una extensión válida
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La imagen debe tener extensión jpg, jpeg o png", ""));
					return null;
				}
			}
				
			p.setCategoria(ManagedBeanUtils.encontrarCategoria(listaCategorias, idCategoria));
			
			if(productoService.update(p) && hayImagen) {
				//Guardamos la imagen
				String rutaCarpeta = ManagedBeanUtils.getRutaImagenesProducto();
				File carpetaDestino = new File(rutaCarpeta);
				if(carpetaDestino.exists()) {
					if(null != imagenSeleccionada) {
						try {
			                InputStream is = imagenSeleccionada.getInputstream();
			                
			                String rutaAnterior = rutaCarpeta + imagenAnterior;
			                File archivoAnteriror = new File(rutaAnterior);
			                if(archivoAnteriror.exists())
			                	archivoAnteriror.delete();
			                
			                String rutaFinal = rutaCarpeta + imagen;
			                guardarImagen(rutaFinal, is);
			                //Si ya existe el archivo esta funcion lo sobreescribe 
			                //FileUtils.copyInputStreamToFile(is, archivoGuardar);
			                FacesMessage msg = new FacesMessage(imagen + " guardada con éxito");
			              	FacesContext.getCurrentInstance().addMessage(null, msg);
			            } catch (IOException ex) {
			            	FacesMessage msg = new FacesMessage("Error al guardar el archivo");
		        	      	FacesContext.getCurrentInstance().addMessage(null, msg);
			            	return null;
			            }
					}
				}
			}
			return "admin.xhtml?faces-redirect=true";
		} catch (Exception e1) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error modificando producto", ""));
			e1.printStackTrace();
			return null;
		}
	}
	
	public String atras() {
		return "admin.xhtml?faces-redirect=true";
	}
	
	public static void guardarImagen(String outImagePath, InputStream inputStream) {
        // Leer la imagen de entrada desde el archivo
        BufferedImage inputImage;
		try {
			inputImage = ImageIO.read(inputStream);
			// Crear una instancia de BufferedImage con las dimensiones deseadas
	        BufferedImage outputImage = new BufferedImage(800, 600, inputImage.getType());

	        // Redimensionar la imagen
	        Graphics2D graphics2D = outputImage.createGraphics();
	        graphics2D.drawImage(inputImage, 0, 0, 800, 600, null);
	        graphics2D.dispose();

	        // Escribir la imagen redimensionada en el archivo de salida
	        File outputFile = new File(outImagePath);
	        ImageIO.write(outputImage, "jpg", outputFile);
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error guardando la imagen", ""));
			e.printStackTrace();
		}

        
    }
	
	/* POR SI QUIERES MODO AVANZADO EN EL <p:fileUpload>
	public void handleFileUpload(FileUploadEvent event) {
		imagenSeleccionada = event.getFile();
	    imagen = imagenSeleccionada.getFileName();
	    //La cargamos en la vista
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    imagenUrl = externalContext.getRequestContextPath() + "/imagenesProducto/" + imagen;
	}
	*/
}
