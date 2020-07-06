package classes;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Cosa> objetos = new ArrayList<Cosa>();//candidatos
		//genero objetos
		Cosa elem0 = new Cosa("objeto-0", 5, 10);
		Cosa elem1 = new Cosa("objeto-1", 4, 4);
		Cosa elem2 = new Cosa("objeto-2", 7, 10);
		Cosa elem3 = new Cosa("objeto-3", 6, 11);
		Cosa elem4 = new Cosa("objeto-4", 5, 6);
		Cosa elem5 = new Cosa("objeto-5", 10, 12);
		//los agrego al arreglo
		objetos.add(elem0);
		objetos.add(elem1);
		objetos.add(elem2);
		objetos.add(elem3);
		objetos.add(elem4);
		objetos.add(elem5);
		ArrayList<Float> fracciones = new ArrayList<Float>(objetos.size());//contiene la medida de peso de cada objeto en la mochila
		for(int i = 0; i < objetos.size(); i++)//inicializo
			fracciones.add((float) 0);
		float capacidad = 27;
		mochilaFraccionaria(capacidad, objetos, fracciones);
		System.out.println("el arreglo de fracciones:");
		System.out.println(fracciones);
	}
	
	public static void mochilaFraccionaria(float capacidad, ArrayList<Cosa> objetos, ArrayList<Float> fracciones) {
		int mejor = 0;
		float mayor = 0;
		while ((capacidad > (float)0)) {
			for(int i = 0; i < objetos.size(); i++) {//selecciono el mejor en relacion de valor peso.
				if((objetos.get(i).getPesoValor() > mayor)&&(fracciones.get(i) == 0)) {
					mayor = objetos.get(i).getPesoValor();
					mejor = i;
				}
			}
			if(capacidad - objetos.get(mejor).getPeso() >= 0) {//decido si coloco el elemento entero o una fraccion del mismo
				fracciones.set(mejor, (float) 1);
				capacidad -= objetos.get(mejor).getPeso();
			}
			else {
				float aux = capacidad/objetos.get(mejor).getPeso();
				fracciones.set(mejor, aux);
				capacidad = 0;
			}
			mejor = 0;
			mayor = 0;
			}
		}

}
