package es.cenjorapps.pizzacar.core.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "usuario")
@NamedQueries({
	@NamedQuery(name="UsuarioEntity.findAll", query="SELECT u FROM UsuarioEntity u"),
	@NamedQuery(name="UsuarioEntity.findAllOrderByUsername", query="SELECT u FROM UsuarioEntity u ORDER BY u.username"),
	@NamedQuery(name="UsuarioEntity.findByUsername", query="SELECT u FROM UsuarioEntity u WHERE u.username = :usuario"),
	@NamedQuery(name="UsuarioEntity.findByEmail", query="SELECT u FROM UsuarioEntity u WHERE u.email = :email"),
	@NamedQuery(name="UsuarioEntity.findByIdAndEmail", query="SELECT u FROM UsuarioEntity u WHERE u.email = :email AND u.id != :id"),
	@NamedQuery(name="UsuarioEntity.findByToken", query="SELECT u FROM UsuarioEntity u WHERE u.token = :token"),
	@NamedQuery(name="UsuarioEntity.findByIdAndUsername", query="SELECT u FROM UsuarioEntity u WHERE u.username = :usuario AND u.id != :id"),
	@NamedQuery(name="UsuarioEntity.findByUsernameAndPass", query="SELECT u FROM UsuarioEntity u WHERE u.username = :usuario AND u.pass = :passsword "
			+ "and u.confirmado = 1"),
	@NamedQuery(name="UsuarioEntity.findByEmailAndToken", query="SELECT u FROM UsuarioEntity u WHERE u.email = :email AND u.token = :token"),
	
})
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "pass")
    private String pass;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "poblacion")
    private String poblacion;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "cod_postal")
    private String codPostal;
    
    @ManyToOne
    @JoinColumn(name = "rol")
    private RolEntity rol;
    
    @Column(name = "confirmado")
    private int confirmado;
    
    @Column(name = "token_confimacion")
    private String token;
    
    public UsuarioEntity() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public RolEntity getRol() {
		return rol;
	}

	public void setRol(RolEntity rol) {
		this.rol = rol;
	}

	public int getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(int confirmado) {
		this.confirmado = confirmado;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
