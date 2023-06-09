package es.cenjorapps.pizzacar.core.data.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
@NamedQueries({
	@NamedQuery(name="CategoriaEntity.findAll", query="SELECT c FROM CategoriaEntity c"),
	@NamedQuery(name="CategoriaEntity.findByNombre", query="SELECT c FROM CategoriaEntity c WHERE c.nombre = :nombre"),
	@NamedQuery(name="CategoriaEntity.findByIdAndNombre", query="SELECT c FROM CategoriaEntity c WHERE c.nombre = :nombre AND c.id = :id"),
})
public class CategoriaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    private List<ProductoEntity> productos;
    
    public CategoriaEntity() {
    	
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

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<ProductoEntity> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoEntity> productos) {
		this.productos = productos;
	}
	
}
