package classes;

import java.util.ArrayList;
import java.util.Iterator;

public class Mochila {
	private float capacidad;
	private ArrayList<Cosa> cosas;
	
	public Mochila(float capacidad) {
		super();
		this.capacidad = capacidad;
		this.cosas = new ArrayList<Cosa>();
	}

	public float getCapacidad() {
		return capacidad;
	}

	public ArrayList<Cosa> getCosas() {
		return cosas;
	}
	
	public Iterator<Cosa> iterador(){
		return this.cosas.iterator();
	}
	
	public void addElem(Cosa elem) {
		if(!cosas.contains(elem))
			cosas.add(elem);
	}
	
	public void removeElem(Cosa elem) {
		cosas.remove(elem);
	}
	
	public Cosa getElem(String name) {
		int i = 0;
		while ((i < cosas.size()) && (!cosas.get(i).getName().equals(name))) {
			i++;
		}
		if(i >= cosas.size())
			return null;
		else
			return cosas.get(i);
	}
	
	
	
}
