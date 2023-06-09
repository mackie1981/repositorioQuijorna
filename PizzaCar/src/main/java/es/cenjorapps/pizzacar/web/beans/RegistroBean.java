package es.cenjorapps.pizzacar.web.beans;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;

import es.cenjorapps.pizzacar.core.data.model.RolEntity;
import es.cenjorapps.pizzacar.core.data.model.UsuarioEntity;
import es.cenjorapps.pizzacar.core.service.EmailService;
import es.cenjorapps.pizzacar.core.service.RolService;
import es.cenjorapps.pizzacar.core.service.UsuarioService;
import es.cenjorapps.pizzacar.util.ApplicationContextUtils;
import es.cenjorapps.pizzacar.util.ManagedBeanUtils;
import es.cenjorapps.pizzacar.util.Utilidades;


public class RegistroBean implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String password2;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String telefono;
	private String direccion;
	private String ciudad;
	private String codPostal;
	private String provincia;
	private int idRol;
	
	
	private UsuarioService usuarioService;
	private RolService rolService;
	private EmailService emailService;
	private List<RolEntity>listaRoles;
	private List<SelectItem> listaRolesCombo = null;
	
    @PostConstruct
	public void initializeRegistroBean() {
    	try {
    		usuarioService = (UsuarioService) ApplicationContextUtils.getBean("usuarioServiceBean");
        	rolService = (RolService) ApplicationContextUtils.getBean("rolServiceBean");
        	emailService = (EmailService) ApplicationContextUtils.getBean("emailServiceBean");
			listaRoles = rolService.findAll();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error iniciando RegistroBean", ""));
			e.printStackTrace();
		}
    	listaRolesCombo = ManagedBeanUtils.crearComboRoles(listaRoles, true);
    	idRol = 2;
    } 
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getIdRol() {
		return idRol;
	}
	
	public String getCodPostal() {
		return codPostal;
	}
	
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	public RolService getRolService() {
		return rolService;
	}

	public void setRolService(RolService rolService) {
		this.rolService = rolService;
	}
	
	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public List<RolEntity> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<RolEntity> listaRoles) {
		this.listaRoles = listaRoles;
	}
	
	public List<SelectItem> getListaRolesCombo() {
		return listaRolesCombo;
	}

	public void setListaRolesCombo(List<SelectItem> listaRolesCombo) {
		this.listaRolesCombo = listaRolesCombo;
	}

	public String guardar() {
		Pattern pattern = null;
		Matcher matcher = null;
		boolean esValido = false;
		//Validacion de campos
		if(StringUtils.isEmpty(username)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario es obligatorio", ""));
			return null;
		}
		if(StringUtils.isBlank(username)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario es obligatorio", ""));
			return null;
		}
		if(StringUtils.isEmpty(password)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña es obligatoria", ""));
			return null;
		}
		if(StringUtils.isBlank(password)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña es obligatoria", ""));
			return null;
		}
		if(StringUtils.isEmpty(password2)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La segunda contraseña es obligatoria", ""));
			return null;
		}
		if(StringUtils.isBlank(password2)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La segunda contraseña es obligatoria", ""));
			return null;
		}
		pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
		matcher = pattern.matcher(password);
		esValido = matcher.matches();
		
		if(!esValido) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña debe tener entre 8 y 20 caracteres, al menos una mayúscula y una minúscula y un símbolo", ""));
			return null;
		}
		if(!password.equals(password2)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas debe ser iguales", ""));
			return null;
		}
		if(StringUtils.isEmpty(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es obligatorio", ""));
			return null;
		}
		if(StringUtils.isBlank(nombre)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre es obligatorio", ""));
			return null;
		}
		if(StringUtils.isEmpty(apellido1)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El apellido 1 es obligatorio", ""));
			return null;
		}
		if(StringUtils.isBlank(apellido1)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El apellido 1 es obligatorio", ""));
			return null;
		}
		if(StringUtils.isEmpty(email)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email es obligatorio", ""));
			return null;
		}
		if(StringUtils.isBlank(email)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email es obligatorio", ""));
			return null;
		}
		if(!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email no está bien formado", ""));
			return null;
		}
		if(StringUtils.isEmpty(telefono)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El teléfono es obligatorio", ""));
			return null;
		}
		if(StringUtils.isBlank(telefono)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El teléfono es obligatorio", ""));
			return null;
		}
		if(!Pattern.matches("(\\+34|0034|34)?[6789]\\d{8}", telefono)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El teléfono no está bien formado", ""));
			return null;
		}
		if(StringUtils.isEmpty(direccion)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La dirección es obligatoria", ""));
			return null;
		}
		if(StringUtils.isBlank(direccion)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La dirección es obligatoria", ""));
			return null;
		}
		if(StringUtils.isEmpty(ciudad)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La ciudad es obligatoria", ""));
			return null;
		}
		if(StringUtils.isBlank(ciudad)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La ciudad es obligatoria", ""));
			return null;
		}
		if(StringUtils.isEmpty(codPostal)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El código postal es obligatoria", ""));
			return null;
		}
		if(StringUtils.isBlank(codPostal)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El código postal es obligatoria", ""));
			return null;
		}
		if(!Pattern.matches("^(0[1-9]|[1-4]\\d|5[0-2])\\d{3}$", codPostal)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El código postal no está bien formado", ""));
			return null;
		}
		if(StringUtils.isEmpty(provincia)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La provincia es obligatoria", ""));
			return null;
		}
		if(StringUtils.isBlank(provincia)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La provincia es obligatoria", ""));
			return null;
		}
		if(-1 == idRol) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes elegir un rol para el usuario", ""));
			return null;
		}
		//Si llega hasa aquí los datos son validos, pero debemos comprobar que no exista ni el username, ni email
		try {
			if(!usuarioService.esUsernameValido(username)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este usuario ya existe, debes elegir otro distinto", ""));
				return null;
			}
			if(usuarioService.findByEmail(email)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este email ya esta registrado", ""));
				return null;
			}
			//No hay errores , creamos el usuario, encriptando la contraseña y lo insertamos
			UsuarioEntity u = new UsuarioEntity();
			u.setUsername(username);
			System.out.println("Pass original: " + password);
			u.setPass(usuarioService.getPasswordEncoder().encode(password));
			System.out.println("Pass hash: " + u.getPass());
			System.out.println("Coinciden: " + usuarioService.getPasswordEncoder().matches(password, u.getPass()));
			u.setNombre(nombre);
			u.setApellido1(apellido1);
			if(!StringUtils.isEmpty(apellido2)) 
				u.setApellido2(apellido2);
			u.setEmail(email);
			u.setTelefono(telefono);
			u.setDireccion(direccion);
			u.setPoblacion(ciudad);
			u.setProvincia(provincia);
			u.setCodPostal(codPostal);
			u.setRol(ManagedBeanUtils.encontrarRol(listaRoles, idRol));
			
			// Generar token de confirmación
	        String confirmationToken = Utilidades.generateConfirmationToken();
			u.setToken(confirmationToken);
			usuarioService.save(u);
			//Enviamos el email de confirmacion
			String confirmationLink = "http://localhost:8080/PizzaCar/confirmar.xhtml?token=" + confirmationToken + "&email=" + email;
	        emailService.sendConfirmationEmail(email, confirmationLink);
			
			return "login.xhtml?faces-redirect=true";
		} catch (Exception e1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error registrando tu nuevo usuario", ""));
			e1.printStackTrace();
			return null;
			
		}
    }
	
    public String registro() {
    	return "registro.xhtml?faces-redirect=true";
    }
    
}
