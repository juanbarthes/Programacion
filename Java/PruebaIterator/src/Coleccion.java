import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

public class Coleccion {
	private Vector<Persona> gente;
	
	public Coleccion() {
		gente= new Vector<Persona>();
	}

	public Vector<Persona> getGente() {
		return gente;
	}

	public void setGente(Vector<Persona> gente) {
		this.gente = gente;
	}
	public String toString() {
		Iterator<Persona> it= gente.iterator();
		String s="";
		while (it.hasNext()) {
			s+=it.next().toString();
		}
		return s;
	}
	public void add(Persona p) {
		gente.add(p);
	}
	
	
	public void ordenarNombre(Comparator<Persona> c1){
		Collections.sort(gente, c1);
	}
}
