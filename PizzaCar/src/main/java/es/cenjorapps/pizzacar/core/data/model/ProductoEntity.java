package es.cenjorapps.pizzacar.core.data.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "producto")
@NamedQueries({
	@NamedQuery(name="ProductoEntity.findAll", query="SELECT p FROM ProductoEntity p"),
	@NamedQuery(name="ProductoEntity.findAllOrderByNombre", query="SELECT p FROM ProductoEntity p ORDER BY p.nombre"),
	@NamedQuery(name="ProductoEntity.findByNombre", query="SELECT p FROM ProductoEntity p WHERE p.nombre = :nombre"),
	@NamedQuery(name="ProductoEntity.findByIdAndNombre", query="SELECT p FROM ProductoEntity p WHERE p.nombre = :nombre AND p.id != :id"),
	@NamedQuery(name="ProductoEntity.findByCategoria", query="SELECT p FROM ProductoEntity p WHERE p.categoria.id = :idCategoria"),
	@NamedQuery(name="ProductoEntity.findDestacados", query="SELECT p FROM ProductoEntity p WHERE p.destacado = 1"),
})
public class ProductoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "imagen")
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "categoria")
    private CategoriaEntity categoria;
    
    @Column(name="destacado")
    private int destacado;
    
    public ProductoEntity() {

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

	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	public int getDestacado() {
		return destacado;
	}

	public void setDestacado(int destacado) {
		this.destacado = destacado;
	}
	
}

