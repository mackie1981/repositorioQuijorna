package es.cenjorapps.pizzacar.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import es.cenjorapps.pizzacar.core.data.model.CategoriaEntity;
import es.cenjorapps.pizzacar.core.data.model.EstadoEntity;
import es.cenjorapps.pizzacar.core.data.model.ProductoEntity;
import es.cenjorapps.pizzacar.core.data.model.RolEntity;
import es.cenjorapps.pizzacar.core.data.model.UsuarioEntity;
import es.cenjorapps.pizzacar.core.service.UsuarioService;


public class ManagedBeanUtils {
	
	public static List<SelectItem> crearComboUsuarios(List<UsuarioEntity> usuarios, boolean opcionExtra) {
		List<SelectItem> listaUsuarios = new ArrayList<SelectItem>();
		
		if(opcionExtra) {
			listaUsuarios.add(new SelectItem(-1, "Seleccione usuario"));
		}
		
		for (UsuarioEntity usuario : usuarios) {
			listaUsuarios.add(new SelectItem(usuario.getId(), usuario.getUsername()));
		}
		return listaUsuarios;
	}
	
	public static List<SelectItem> crearComboEstados(List<EstadoEntity> estados, boolean opcionExtra) {
		List<SelectItem> listaEstados = new ArrayList<SelectItem>();
		
		if(opcionExtra) {
			listaEstados.add(new SelectItem(-1, "Seleccione estado"));
		}
		
		for (EstadoEntity estado : estados) {
			listaEstados.add(new SelectItem(estado.getId(), estado.getNombre()));
		}
		return listaEstados;
	}

	public static List<SelectItem> crearComboRoles(List<RolEntity> roles, boolean opcionExtra) {
		List<SelectItem> listaRoles = new ArrayList<SelectItem>();
		if(opcionExtra) {
			listaRoles.add(new SelectItem(-1, "Seleccione rol"));
		}
		for (RolEntity rol : roles) {
			listaRoles.add(new SelectItem(rol.getId(), rol.getNombre()));
		}
		return listaRoles;
	}
	
	public static List<SelectItem> crearComboCategorias(List<CategoriaEntity> categorias, boolean opcionExtra) {
		List<SelectItem> listaCategorias = new ArrayList<SelectItem>();
		if(opcionExtra) {
			listaCategorias.add(new SelectItem(-1, "Seleccione categoría"));
		}
		for (CategoriaEntity cat : categorias) {
			listaCategorias.add(new SelectItem(cat.getId(), cat.getNombre()));
		}
		return listaCategorias;
	}
	
	public static List<SelectItem> crearComboProductos(List<ProductoEntity> listaProductos, boolean opcionExtra) {
		List<SelectItem> listaCategorias = new ArrayList<SelectItem>();
		if(opcionExtra) {
			listaCategorias.add(new SelectItem(-1, "Seleccione producto"));
		}
		for (ProductoEntity pro : listaProductos) {
			listaCategorias.add(new SelectItem(pro.getId(), pro.getNombre()));
		}
		return listaCategorias;
	}
	
	public static UsuarioEntity encontrarUsuario(List<UsuarioEntity> usuarios, long idUsuario) {
		for (UsuarioEntity usu: usuarios) {
			if (usu.getId() == idUsuario) {
				return usu;
			}
		}
		return null;
	}
	
	public static EstadoEntity encontrarEstado(List<EstadoEntity> estados, long idEstado) {
		for (EstadoEntity est: estados) {
			if (est.getId() == idEstado) {
				return est;
			}
		}
		return null;
	}

	public static RolEntity encontrarRol(List<RolEntity> roles, long idRol) {
		for (RolEntity rol: roles) {
			if (rol.getId() == idRol) {
				return rol;
			}
		}
		return null;
	}

	public static CategoriaEntity encontrarCategoria(List<CategoriaEntity> categorias, int idCategoria) {
		for (CategoriaEntity cat: categorias) {
			if (cat.getId() == idCategoria) {
				return cat;
			}
		}
		return null;
	}
	
	public static ProductoEntity encontrarProducto(List<ProductoEntity> productos, long idProducto) {
		for (ProductoEntity pro: productos) {
			if (pro.getId() == idProducto) {
				return pro;
			}
		}
		return null;
	}
	
	public static String getUrlServidor() {
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String url = req.getServerName().toLowerCase();
		return url;
	}
	
	public static String getRutaImagenesProducto() {
		String catalina = System.getProperty("catalina.base");
		String rutaCarpeta;
		
        String url = ManagedBeanUtils.getUrlServidor();
		if(url.indexOf("localhost") > -1 || url.indexOf("192.168.0.10") > -1) {
			rutaCarpeta = "/imagenesProducto/";
		} else {
			rutaCarpeta = catalina + "/imagenesProducto/";
		}
		return rutaCarpeta;
	}

	public static void eliminarImagen(ProductoEntity productoSeleccionado) {
		String rutaImagen = getRutaImagenesProducto() + productoSeleccionado.getImagen();
		File archivo = new File(rutaImagen);
        if (archivo.exists()) {
            archivo.delete();      
        } 
	}
	
	public static boolean validarToken(UsuarioService usuarioService) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        String email = externalContext.getRequestParameterMap().get("email");
        String token = externalContext.getRequestParameterMap().get("token");
        
        UsuarioEntity u;
		try {
			u = usuarioService.findByEmailAndToken(email, token);
			if(null != u) {
	        	//Hacemos el update
	        	u.setConfirmado(1);
				usuarioService.update(u);
	        	return true;
	        }
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
        
		return false;
	}
	
	public static UsuarioEntity findUsuarioByEmailAndToken(UsuarioService usuarioService) throws Exception {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        String email = externalContext.getRequestParameterMap().get("email");
        String token = externalContext.getRequestParameterMap().get("token");
        
        UsuarioEntity u;
		try {
			u = usuarioService.findByEmailAndToken(email, token);
			if(null != u) {
	        	return u;
	        }
		} catch (Exception e) {
			throw e;
		}
        
		return null;
	}

}
