package ProgramacionIII.tp5;

import java.util.Arrays;

/* Una familia, con su cantidad de dias, y una arreglo con el top de 3 dias preferidos */
public class Familia implements Comparable<Familia> {

	private int id;
	private int miembros;
	private int[] diasPreferidos;
	private int[] bonos; //Guarda los bonos ya calculados para cada dia de preferencia.
	private int diaAsignado;	
	
	public Familia(int id, int miembros, int... diasPreferidos) {
		this.id = id;
		this.miembros = miembros;
		this.diasPreferidos = diasPreferidos;
		this.bonos = new int [3];
		for(int i = 0; i < 3; i++) {
			bonos[i] = this.calcularBono(this.preferenciaEn(i));
		}
		this.diaAsignado = 0;
	}

	/* Id de la familia */
	public int getId() {
		return id;
	}


	/* Retorna la cantidad de miembros de la familia. */
	public int miembros() {
		return miembros;
	}

	
	/* Dado un indice entre 0 y 2, retorna el día preferido por la familia para ese indice. */
	public int preferenciaEn(int indice) {
		return this.diasPreferidos[indice];
	}
	
	/* Retorna el día preferido de la familia */
	public int diaPreferido() {
		return preferenciaEn(0);
	}
	
	/* Dado un dia pasado por parametro, indica el orden de ese dia en el top 5 de preferencias.
	Si el dia no forma parte de las preferencias del usuario, se retorna -1. */ 
	public int indiceDePreferencia(int dia) {
		for (int indice = 0; indice < diasPreferidos.length; indice++)
			if (dia == diasPreferidos[indice])
				return indice;
		return -1;
	}
	
	public int getBono(int i) {
		return this.bonos[i];
	}

	@Override
	public String toString() {
		return "Familia [id=" + id + ", miembros=" + miembros + ", diasPreferidos=" + Arrays.toString(diasPreferidos) + "]";
	}

	@Override
	public int compareTo(Familia o) {
		return Integer.compare(this.miembros, o.miembros);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Familia other = (Familia) obj;
		if (!Arrays.equals(diasPreferidos, other.diasPreferidos))
			return false;
		if (id != other.id)
			return false;
		if (miembros != other.miembros)
			return false;
		return true;
	}
	
	public void asignarDia(int dia) {
		diaAsignado = dia;
	}
	public int diaAsignado() {
		return this.diaAsignado;
	}
	
	public int calcularBono(int dia) {//Recibe un dia y calcula el bono compensatorio.
		if(dia == this.diaPreferido())
			return 0;
		return 25+(10*this.miembros())+(5*this.indiceDePreferencia(dia));
	}
}
