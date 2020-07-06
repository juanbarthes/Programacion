package clases;

import java.util.ArrayList;
import java.util.Iterator;

public class Main2 {//ESTE MAIN FUE CREADO PARA DEMOSTRAR EL CORRECTO FUNCIONAMIENTO DE TODAS LAS FUNCIONALIDADES DE LA CLASE Y ASI NO SOBRECARGAR DE INFORMACION EL MAIN PRINCIPAL DE LA CONSIGNJA ENTREGABLE.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrafoDirigido<Integer> gp = new GrafoDirigido<Integer>();
		//AGREGO ALGUNOS VERTICES
				ArrayList<Tarea> tareas = new ArrayList<Tarea>();
				tareas.add(new Tarea(0, 0, "DESCRIPCION"));
				tareas.add(new Tarea(1, 4, "DESCRIPCION"));
				tareas.add(new Tarea(2, 18, "DESCRIPCION"));
				tareas.add(new Tarea(3, 4, "DESCRIPCION"));
				tareas.add(new Tarea(4, 13, "DESCRIPCION"));
				tareas.add(new Tarea(5, 22, "DESCRIPCION"));
				tareas.add(new Tarea(6, 18, "DESCRIPCION"));
				tareas.add(new Tarea(7, 12, "DESCRIPCION"));
				tareas.add(new Tarea(8, 3, "DESCRIPCION"));
				tareas.add(new Tarea(9, 2, "DESCRIPCION"));
				tareas.add(new Tarea(10, 3, "DESCRIPCION"));
				tareas.add(new Tarea(11, 1, "DESCRIPCION"));
				tareas.add(new Tarea(12, 5, "DESCRIPCION"));
				
				//CARGO EL GRAFO MEDIANTE EL ARREGLO "tareas"
				Iterator<Tarea> t = tareas.iterator();
				while (t.hasNext()) {
					gp.agregarVertice(t.next().getId());
				}
				gp.agregarVertice(12);//PRUEBO AGREGAR VERTICE REPETIDO
				
				//CONECTO ALGUNOS VERTICES MEDIANTE ARCOS
				gp.agregarArco(0, 1, 0);
				gp.agregarArco(0, 2, 0);
				gp.agregarArco(1, 3, 3);
				gp.agregarArco(2, 5, 1);
				gp.agregarArco(2, 7, 18);
				gp.agregarArco(3, 4, 5);
				gp.agregarArco(3, 6, 8);
				gp.agregarArco(5, 6, 2);
				gp.agregarArco(7, 8, 7);
				gp.agregarArco(4, 11, 3);
				gp.agregarArco(6, 12, 2);
				gp.agregarArco(6, 10, 6);
				gp.agregarArco(8, 9, 4);
				gp.agregarArco(11, 12, 9);
				gp.agregarArco(9, 10, 1);
				gp.agregarArco(9, 10, 1);//PRUEBO AGREGAR UN ARCO REPETIDO

				
				//CREO UN ITERADOR Y PRUEBO IMPRIMIR LA LISTA DE VERTICES
				Iterator<Integer> it = gp.obtenerVertices();
				System.out.println("la lista de vertices del grafo: ");
				while (it.hasNext()) {
					System.out.println("vertice ID: " + it.next());
				}
				
				System.out.println();
				//CREO UN ITERADOR Y COMPRUEBO LOS ADYACENTES DE UN VERTICE
				int pideAdyacentes = 9;
				Iterator<Integer> it2 = gp.obtenerAdyacentes(pideAdyacentes);
				System.out.println("la lista de adyacentes del vertice "+pideAdyacentes+":");
				while (it2.hasNext()) {
					System.out.println("adyacente ID: " + it2.next());
				}
				
				System.out.println();
				//CREO UN ITERADOR PARA IMPRIMIR TODOS LOS ARCOS DEL GRAFO
				Iterator<Arco<Integer>> it3 = gp.obtenerArcos();
				System.out.println("Lista de todos los arcos del grafo: ");
				while (it3.hasNext()) {
					System.out.println(it3.next().toString());
				}
				
				System.out.println();
				//CREO UN ITERADOR PARA IMPRIMIR TODOS LOS ARCOS DE UN VERTICE
				Iterator<Arco<Integer>> it4 = gp.obtenerArcos(2);
				System.out.println("Lista de todos los arcos del vertice: ");
				while (it4.hasNext()) {
					System.out.println(it4.next().toString());
				}
				
				System.out.println();
				//COMPRUEBO SI EXISTE UN VERTICE
				int id = 5;
				System.out.println("comprobando si existe el vertice "+id+"...");
				if(gp.contieneVertice(id))
					System.out.println("el vertice "+id+" existe!");
				else
					System.out.println("el vertice "+id+" no existe");
				System.out.println();
				//COMPRUEBO SI EXISTE UN ARCO ENTRE 2 VERTICES
				int origen =6;
				int destino = 10;
				System.out.println("comprobando si existe un arco entre los vertices "+origen+" y "+destino+"...");
				if(gp.existeArco(origen, destino))
					System.out.println("existe un arco!");
				else
					System.out.println("no existe un arco entre los vertices solicitados");
				System.out.println();
				//BORRO UN VERTICE (Y POR ENDE TODOS LOS ARCOS QUE LLEVAN A EL)
				int eliminar = 10;
				System.out.println("eliminando vertice "+eliminar+"...");
				gp.borrarVertice(eliminar);
				System.out.println();
				
				//VUELVO A IMPRIMIR LOS VERTICES Y LOS ARCOS PARA VER COMO QUEDO EL GRAFO LUEGO DE BORRAR EL VERTICE
				System.out.println("lista de vertices del grafo luego de eliminar el vertice "+eliminar+":");
				Iterator<Integer> it5 = gp.obtenerVertices();
				while (it5.hasNext()) {
					System.out.println("vertice ID: " + it5.next());
				}
				System.out.println();
				System.out.println("lista de arcos del grafo luego de eliminar los arcos que llevaban a "+eliminar+":");
				System.out.println();
				Iterator<Arco<Integer>> it6 = gp.obtenerArcos();
				while (it6.hasNext()) {
					System.out.println(it6.next().toString());
				}
	}

}
