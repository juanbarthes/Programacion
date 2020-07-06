package ProgramacionIII.tp5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

	
	public static void main(String... args) {
		
		
		CSVReader reader = new CSVReader("./data/familias-1.csv");
		
		ArrayList<Familia> familias = reader.read();//Familias-1 con 20 celdas
		
		/*for (Familia familia: familias)
			System.out.println(familia);*/



		CSVReader reader2 = new CSVReader("./data/familias-2.csv");
		
		ArrayList<Familia> familias2 = reader2.read();//Familias-2 con 60 celdas
		
		/*for (Familia familia: familias2)
			System.out.println(familia);*/
		
		int cupoDiario = 30;
		ArrayList<LinkedList<Familia>> calendario1 = new ArrayList<LinkedList<Familia>>();//Listas de Familias para cada dia (familias-1)
		for (int i = 0; i < 10; i++)
			calendario1.add(new LinkedList<Familia>());
		ArrayList<LinkedList<Familia>> calendario2 = new ArrayList<LinkedList<Familia>>();//Listas de Familias para cada dia (familias-2)
		for (int i = 0; i < 10; i++)
			calendario2.add(new LinkedList<Familia>());
		System.out.println("Dataset-1:");
		acomodarFamilias(calendario1, familias, cupoDiario);
		System.out.println();
		System.out.println("Dataset-2:");
		acomodarFamilias(calendario1, familias2, cupoDiario);
	}

	private static void acomodarFamilias(ArrayList<LinkedList<Familia>> calendario, ArrayList<Familia> familias, int cupoDiario) {
		//Genera todas las estructuras y variables necesarias, luego llama al metodo recursivo.
		AtomicInteger estados = new AtomicInteger(0); //Guarda la cantidad total de estados generados por el algoritmo de BackTracking.
		AtomicInteger bonoSolucion = new AtomicInteger(0); //Guarda la suma de todos los bonos para la mejor solucion.
		int bonoActual = 0; //Lleva la suma de todos los bonos de la solucion parcial.
		int idActual = 0; //Debido a que los datasets vienen ordenados por id de familia, esta coincide con su posicion en el arreglo, por lo que la uso de indice.
		int[] dias = new int[10];//Dias de exibicion con la capacidad ocupada hasta el momento.
		for (int i = 0; i < 10; i++)
			dias[i] = 0;
		ArrayList<LinkedList<Familia>> calendarioActual = new ArrayList<LinkedList<Familia>>();
		for(int i = 0; i < calendario.size(); i++)
			calendarioActual.add(new LinkedList<Familia>());
		//SE PUEDE LLAMAR AL METODO "acomodar" O "acomodarConPoda", AMBOS UTILIZAN LOS MISMOS PARAMETROS.
		acomodarConPoda(calendario, calendarioActual,  dias, familias, cupoDiario, estados, bonoSolucion, bonoActual, idActual);
		System.out.println("La cantidad de estados generados fue: " + estados.get());
		System.out.println("el bono solucion es: " + bonoSolucion.get());
		System.out.println("Calendario:");
		for (LinkedList<Familia> dia : calendario) {			
			System.out.println(dia);
		}
		}

	private static void acomodar(ArrayList<LinkedList<Familia>> calendario, ArrayList<LinkedList<Familia>> calendarioActual, int[] dias, ArrayList<Familia> familias, int cupo, AtomicInteger estados, AtomicInteger bonoSolucion, int bonoActual, int idActual) {
		//Para el segundo Dataset tarda muchisimo tiempo en encontrar una solucion, aun asi puede chequearse en pocos segundos la solucion para el primer Dataset.
		estados.incrementAndGet();
		if(idActual >= familias.size()) { //Si no hay mas familias para acomodar debe ser una solucion completa.
			if ((bonoSolucion.get() == 0)||(bonoActual < bonoSolucion.get())) { //Si el bono total de la nueva solucion es menor, debe reemplazar a la solucion anterior.
				bonoSolucion.set(bonoActual);
				calendario.removeAll(calendario);
				calendario.addAll(calendarioActual);
			}
		}
		else
			for(int i = 0; i< 3; i++) { //Para este ejercicio cada familia selecciono 3 dias de preferencia.
				Familia familia = familias.get(idActual);
				int d = familia.preferenciaEn(i);	
				if(dias[d-1] + familia.miembros() <= cupo) {
					dias[d-1] += familia.miembros();
					calendarioActual.get(d-1).add(familia);
					familia.asignarDia(d);
					acomodar(calendario, calendarioActual, dias, familias, cupo, estados, bonoSolucion, bonoActual+familia.getBono(i), idActual+1);
				}
				if(familia.diaAsignado() != 0) {
					familia.asignarDia(0);
					calendarioActual.get(d-1).remove(familia);
					dias[d-1] -= familia.miembros(); 
			}
		
	}
	}
	
	private static void acomodarConPoda(ArrayList<LinkedList<Familia>> calendario, ArrayList<LinkedList<Familia>> calendarioActual, int[] dias, ArrayList<Familia> familias, int cupo, AtomicInteger estados, AtomicInteger bonoSolucion, int bonoActual, int idActual) {
		//Funciona igual que "acomodar" pero agrega una poda para generar mucho menos estados, se recomienda llamar a este metodo para poder chequear la solucion del segundo Dataset.
		estados.incrementAndGet();
		if(idActual >= familias.size()) { //Si no hay mas familias para acomodar debe ser una solucion completa.
			if ((bonoSolucion.get() == 0)||(bonoActual < bonoSolucion.get())) { //Si el bono total de la nueva solucion es menor, debe reemplazar a la solucion anterior.
				bonoSolucion.set(bonoActual);
				calendario.removeAll(calendario);
				copiarColeccion(calendario, calendarioActual);
			}
		}
		else
			for(int i = 0; i< 3; i++) { //Para este ejercicio cada familia selecciono 3 dias de preferencia.
				Familia familia = familias.get(idActual);
				int d = familia.preferenciaEn(i);	
				if(dias[d-1] + familia.miembros() <= cupo) {
					if((poda(i, bonoSolucion, bonoActual, familia)||(bonoSolucion.get() == 0))) {
						dias[d-1] += familia.miembros();
						calendarioActual.get(d-1).add(familia);
						familia.asignarDia(d);
						acomodarConPoda(calendario, calendarioActual, dias, familias, cupo, estados, bonoSolucion, bonoActual+familia.getBono(i), idActual+1);
					}
					
				}
				if(familia.diaAsignado() != 0) {
					familia.asignarDia(0);
					calendarioActual.get(d-1).remove(familia);
					dias[d-1] -= familia.miembros(); 
			}
		
	}
	}
	
	private static boolean poda(int dia, AtomicInteger bonoSolucion, int bonoActual, Familia familia) {
		//Si una rama en la que aun no consegui una solucion ya supera el valor total del bono de la mejor solucion hasta el momento, no tiene sentido seguir generando esa rama.
		return ((bonoActual + familia.getBono(dia)) < bonoSolucion.get());
		
	}
	
	private static void copiarColeccion(ArrayList<LinkedList<Familia>> calendario, ArrayList<LinkedList<Familia>> calendarioActual) {
		//Por como Java maneja los punteros, al terminar BackTracking se perdian los datos del calendario, por eso utilizo este metodo para copiar los datos manualmente.
		for(int i = 0; i < calendarioActual.size(); i++) {
			LinkedList<Familia> aux = new LinkedList<Familia>();
			for(int j = 0; j < calendarioActual.get(i).size(); j++) {
				int id = calendarioActual.get(i).get(j).getId();
				int miembros = calendarioActual.get(i).get(j).miembros();
				int[] diasPreferidos = new int[3];
				diasPreferidos[0] = calendarioActual.get(i).get(j).preferenciaEn(0);
				diasPreferidos[1] = calendarioActual.get(i).get(j).preferenciaEn(1);
				diasPreferidos[2] = calendarioActual.get(i).get(j).preferenciaEn(2);
				Familia familia = new Familia(id, miembros, diasPreferidos);
				aux.add(familia);
			}
			calendario.add(aux);
		}
	}
}
