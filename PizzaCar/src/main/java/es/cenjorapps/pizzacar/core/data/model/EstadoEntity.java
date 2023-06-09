package es.cenjorapps.pizzacar.core.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "estado")
@NamedQueries({
	@NamedQuery(name="EstadoEntity.findAll", query="SELECT e FROM EstadoEntity e"),
	@NamedQuery(name="EstadoEntity.findByNombre", query="SELECT e FROM EstadoEntity e WHERE e.nombre = :nombre"),
})
public class EstadoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre")
	private String nombre;
	
	public EstadoEntity() {
		
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
}
