package clases;

import java.util.Iterator;
import java.util.LinkedList;

public class Vertice<T> {
	private int id;
	private LinkedList<Arco<T>> arcos;
	
	public Vertice(int id) {//CONSTRUCTOR
		super();
		this.id = id;
		arcos = new LinkedList<Arco<T>>();
	}
	
	public int getId() {//DEVUELVE LA ID
		return this.id;
	}
	
	public LinkedList<Arco<T>> getArcos(){//DEVUELVE LA LISTA DE ARCOS. COMPLEJIDAD O(1).
		return arcos;
	}
	
	public void addArco (int destino, T etiqueta) {//CREA UN ARCO ENTRE ESTE VERTICE Y OTRO VERTICE DESTINO. COMPLEJIDAD O(1) YA QUE SIEMPRE AGREGA AL FINAL DE LA LISTA DE ARCOS.
		if (!(this.containsArco(destino))) {
			Arco<T> arco = new Arco<T>(this.getId(), destino, etiqueta);
			this.arcos.add(arco);
		}
	}
	
	public boolean containsArco (int destino) {//COMPRUEBA SI EXISTE UN ARCO ENTRE ESTE VERTICE Y UN VERTICE DESTINO. COMPLEJIDAD O(Cantidad de Arcos) YA QUE DEBE RECORRER TODA LA LISTA EN EL PEOR CASO.
		Arco<T> arc = new Arco<T>(this.getId(), destino, null);
		return (arcos.contains(arc));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Arco<T> getArco(int destino){//DEVUELVE EL ARCO ENTRE ESTE VERTICE Y EL SOLICITADO. COMPLEJIDAD O(Cantidad de Arcos) YA QUE DEBE RECORRER TODA LA LISTA EN EL PEOR CASO.
		Iterator<Arco<T>> it = arcos.iterator();
		while(it.hasNext()) {
			Arco<T> arc = it.next();
			if(arc.getVerticeDestino() == destino)
				return arc;
		}
		return null;
	}
	
	public void borrarArco(int destino) {//BORRA EL ARCO ENTRE ESTE VERTICE Y EL SOLICITADO. COMPLEJIDAD O(Cantidad de Arcos) YA QUE DEBE RECORRER TODA LA LISTA EN EL PEOR CASO.
		Iterator<Arco<T>> it = arcos.iterator();
		while(it.hasNext()) {
			Arco<T> arc = it.next();
			if(arc.getVerticeDestino() == destino)
				arcos.remove(arc);
		}
	}
	
	@Override
	public String toString() {
		return "Vertice [" + id + "]";
	}
	
	
	
}
