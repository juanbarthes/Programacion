package Clases;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	
	public static void main(String... args) {
			
		CSVReader reader = new CSVReader("./data/familias.csv");
		
		ArrayList<Familia> familias = reader.read();
		final int cupoMaximo = 340;//capacidad maxima del recinto.
		ArrayList<Integer> dias = new ArrayList<Integer>();//Arreglo para los dias de exibicion, guarda la cantidad de personas que asistiran cada dia.
		for(int i = 0; i < 100; i++) {
			dias.add(0);
		}
		ArrayList<Integer> asignaciones = new ArrayList<Integer>();//Arreglo de dia asignado para cada familia.
		for(int i = 0; i < 5000; i++) {
			asignaciones.add(-1);
		}
		ArrayList<Integer> bonos = new ArrayList<Integer>();//Arreglo para la indemnizacion de cada familia;
		for(int i = 0; i < 5000; i++) {
			bonos.add(0);
		}	
		ArrayList<ArrayList<Integer>> cronograma = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i <= 100; i++) {
			cronograma.add(new ArrayList<Integer>());
		}
		Collections.sort(familias);//Ordeno las familias priorizando las que menos miembros tienen antes de acomodarlas en algun dia.
		acomodarFamilias(dias, cupoMaximo, familias, asignaciones, bonos);
		mejorarSolucion(dias, cupoMaximo, familias, asignaciones, bonos);
		int pagoFinal = 0;
		System.out.println();
		System.out.println("Organizacion de las familias:");
		for(int i = 0; i < asignaciones.size(); i++) {
			System.out.println("Familia " + i);
			System.out.println("Dia Asignado: " + asignaciones.get(i));
			System.out.println("Bono: " + bonos.get(i));
			System.out.println();
		}
		System.out.println();
		for(int i = 0; i<bonos.size(); i++) {
			pagoFinal += bonos.get(i);
		}
		System.out.println("La suma total para el pago de los bonos es: " + pagoFinal);
		System.out.println("Familias sin asignar:");
		int contAux = 0;
		for(int i = 0; i < asignaciones.size(); i++) {
			if(asignaciones.get(i) == -1)
				contAux ++;
		}
		System.out.println(contAux);
	}

	public static void acomodarFamilias(ArrayList<Integer> dias, int cupoMaximo, ArrayList<Familia> familias, ArrayList<Integer> asignaciones, ArrayList<Integer> bonos) {
		for (int i = 0; i < familias.size(); i++) {
			int d = 0;//Para ciclar los dias.
			boolean aceptados = false;
			while ((!aceptados)&&(d < 8)) {//Si todavia no fueron aceptados y no termine de revisar todos sus dias de preferencia.
				int aux = (familias.get(i).preferenciaEn(d))-1;//obtengo el dia para consultar, las familias piden dias del 1 al 100 por eso le resto 1 al retorno.
				int cupoActual = dias.get(aux);//Obtengo la cantidad de personas confirmadas para el dia obtenido.
				int miembros = familias.get(i).miembros();
				if ((cupoActual + miembros) <= cupoMaximo) {//Pregunto si agregar a la familia no sobrepasa el cupo del dia consultado.
					int suma = cupoActual + miembros;
					dias.set(aux, suma);//Actualizo la cantidad de personas que asistiran ese dia.
					asignaciones.set(familias.get(i).getId(), aux+1);//Le asigno el dia a la familia.
					bonos.set(i, calcularBono(familias.get(i), asignaciones.get(familias.get(i).getId())));//Calculo cuanto hay que pagarle a la familia.
					aceptados = true;
				}
				else
					d++;
			}
		}
	}
	
	public static int calcularBono(Familia familia, int diaAsignado) {//calculo cuanto hay que pagarle a una familia.
		int res = 0;
		int diaPreferido = familia.diaPreferido();
		if( (diaAsignado != diaPreferido)&&(diaAsignado != -1) ){
			int fijo = 25;
			int extraMiembros = 10 * familia.miembros();
			int extraDia = 5 * familia.indiceDePreferencia(diaAsignado);
			res = fijo + extraMiembros + extraDia;
		}
		return res;
	}

	private static void mejorarSolucion(ArrayList<Integer> dias, int cupoMaximo, ArrayList<Familia> familias, ArrayList<Integer> asignaciones, ArrayList<Integer> bonos) {
		for(int i = 0; i < asignaciones.size(); i++)
			for(int j = 0; j < asignaciones.size() - 1; j++) {
				if(intercambiables(familias.get(j), familias.get(j+1), dias, asignaciones, bonos, cupoMaximo))
					intercambiar(familias.get(j), familias.get(j+1), dias, asignaciones, bonos);
			}
	}

	private static boolean intercambiables(Familia familiaA, Familia familiaB, ArrayList<Integer> dias, ArrayList<Integer> asignaciones, ArrayList<Integer> bonos, int cupoMaximo) {
		int diaFamA = asignaciones.get(familiaA.getId());
		int diaFamB = asignaciones.get(familiaB.getId());
		int sumaBonoActual = bonos.get(familiaA.getId()) + bonos.get(familiaB.getId());
		int sumaBonoNueva = (calcularBono(familiaA, diaFamB) + calcularBono(familiaB, diaFamA));
		int ocupacionDia1 = (dias.get(diaFamA - 1) - familiaA.miembros()) + familiaB.miembros();
		int ocupacionDia2 = (dias.get(diaFamB - 1) - familiaB.miembros()) + familiaA.miembros();
		if((ocupacionDia1 <= cupoMaximo) && (ocupacionDia2 <= cupoMaximo))
			if((diaFamA == -1) || (diaFamB == -1) || (sumaBonoNueva < sumaBonoActual))
				return true;
		return false;
	}

	private static void intercambiar(Familia familiaA, Familia familiaB, ArrayList<Integer> dias, ArrayList<Integer> asignaciones, ArrayList<Integer> bonos) {
		//Obtengo el dia asignado de ambas familias.
		int diaFamA = asignaciones.get(familiaA.getId());
		int diaFamB = asignaciones.get(familiaB.getId());
		//Calculo la nueva ocupacion de los dias obtenidos.
		int ocupacionDia1 = (dias.get(diaFamA - 1) - familiaA.miembros()) + familiaB.miembros();
		int ocupacionDia2 = (dias.get(diaFamB - 1) - familiaB.miembros()) + familiaA.miembros();
		//Calculo los nuevos bonos para ambas familias.
		int bonoFamA = calcularBono(familiaA, diaFamB);
		int bonoFamB = calcularBono(familiaB, diaFamA);
		//Actualizo la ocupacion de los dias asignados.
		dias.set(diaFamA - 1, ocupacionDia1);
		dias.set(diaFamB - 1, ocupacionDia2);
		//Actualizo el valor de los bonos.
		bonos.set(familiaA.getId(), bonoFamA);
		bonos.set(familiaB.getId(), bonoFamB);
		//Intercambio el dia asignado de cada familia.
		asignaciones.set(familiaA.getId(), diaFamB);
		asignaciones.set(familiaB.getId(), diaFamA);
	}

	}
