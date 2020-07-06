package clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

	public static void main(String[] args) {

		// Creo un grafo dirigdo donde las etiquetas de los arcos son valores Float
		GrafoDirigido<Integer> g = new GrafoDirigido<Integer>();
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
			g.agregarVertice(t.next().getId());
		}		
		
		//CREO UN ITERADOR Y PRUEBO IMPRIMIR LA LISTA DE VERTICES
		/*Iterator<Integer> it = g.obtenerVertices();
		System.out.println("la lista de vertices del grafo: ");
		while (it.hasNext()) {
			System.out.println("vertice ID: " + it.next());
		}*/
		
		//CONECTO ALGUNOS VERTICES MEDIANTE ARCOS
		g.agregarArco(0, 1, 0);
		g.agregarArco(0, 2, 0);
		g.agregarArco(1, 3, 3);
		g.agregarArco(2, 5, 1);
		g.agregarArco(2, 7, 18);
		g.agregarArco(3, 4, 5);
		g.agregarArco(3, 6, 8);
		g.agregarArco(5, 6, 2);
		g.agregarArco(7, 8, 7);
		g.agregarArco(4, 11, 3);
		g.agregarArco(6, 12, 2);
		g.agregarArco(6, 10, 6);
		g.agregarArco(8, 9, 4);
		g.agregarArco(11, 12, 9);
		g.agregarArco(9, 10, 1);
		
		//CREO UN ITERADOR Y COMPRUEBO LOS ADYACENTES DE UN VERTICE
		/*Iterator<Integer> it = g.obtenerAdyacentes(6);
		System.out.println("la lista de adyacentes del vertice: ");
		while (it.hasNext()) {
			System.out.println("adyacente ID: " + it.next());
		}*/
		
		//CREO UN ITERADOR PARA IMPRIMIR TODOS LOS ARCOS DEL GRAFO
		/*Iterator<Arco<Integer>> it = g.obtenerArcos();
		System.out.println("Lista de todos los arcos del grafo: ");
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}*/
		
		
		
		
		dfs(g, tareas);
		
	}
	
	//COMPLEJIDAD DEL DFS: Como hice la implementacion de lista de litas su complejidad es O((Cantidad de Vertices) + (Cantidad de Arcos))
	public static void dfs(GrafoDirigido<Integer> g, ArrayList<Tarea> tareas) {//ASUMO que el usuario siempre las inserta las tareas en orden ascendente, ya que me permite trabajar mejor.
		LinkedList<Tarea> solucion = new LinkedList<Tarea>();
		LinkedList<Tarea> actual = new LinkedList<Tarea>();
		AtomicInteger maxTiempo = new AtomicInteger();
		maxTiempo.set(0);
		int tiempoActual = 0;
		Iterator<Integer> it = g.obtenerVertices();
		while(it.hasNext()) {
			int u = it.next(); 
			dfs_visit(g, tareas, solucion, actual,  u, maxTiempo, tiempoActual);
		}
		System.out.println("maxTiempo es: " + maxTiempo.get());
		System.out.println("la secuencia critica es: ");
		Iterator<Tarea> sol = solucion.iterator();
		while(sol.hasNext()) {
			Tarea aux = sol.next();
			System.out.println(aux.toString());
		}
		//remuevo los elementos de todas las colecciones porque como las imprimi antes, ya no las necesito.
		solucion.removeAll(solucion);
		actual.removeAll(actual);
		solucion = null;
		actual = null;

	}
	
	private static void dfs_visit(GrafoDirigido<Integer> g, ArrayList<Tarea> tareas, LinkedList<Tarea> solucion, LinkedList<Tarea> actual, int u, AtomicInteger maxTiempo, int tiempoActual) {
		actual.add(tareas.get(u));
		tiempoActual += tareas.get(u).getCosto();
		Iterator<Integer> it = g.obtenerAdyacentes(u);
		int v = -1;
		while (it.hasNext()) {
			v = it.next();
			tiempoActual += g.obtenerArco(u, v).getEtiqueta();
			dfs_visit(g, tareas, solucion, actual, v, maxTiempo, tiempoActual);
			tiempoActual -= g.obtenerArco(u, v).getEtiqueta();
		}
		if (tiempoActual > maxTiempo.get()) {
			maxTiempo.set(tiempoActual);;
			solucion.removeAll(solucion);
			solucion.addAll(actual);
		}
		actual.removeLast();
	}

}
