package clases;

public class Tarea {
	private int id;
	private int costo;
	private String descripcion;
	
	public Tarea(int id, int costo, String descripcion) {
		super();
		this.id = id;
		this.costo = costo;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public int getCosto() {
		return costo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return " Tarea [id=" + id + ", costo=" + costo +"hs"  + ", descripcion=" + descripcion + "] ";
	}
	
	
}
