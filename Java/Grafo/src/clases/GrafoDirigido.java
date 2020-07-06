package clases;

import java.util.Iterator;
import java.util.LinkedList;

public class GrafoDirigido<T> implements Grafo<T> {

	private LinkedList<Vertice<T>> vertices;
	
	public GrafoDirigido() {//CONSTRUCTOR
		vertices = new LinkedList<Vertice<T>>();
	}
	@Override
	public void agregarVertice(int verticeId) {//AGREGA UN VERTICE AL GRAFO. COMPLEJIDAD O(1) YA QUE AGREGA SIEMPRE AL FINAL.
		// TODO Auto-generated method stub
		Vertice<T> v = new Vertice<T>(verticeId);
		if (!(vertices.contains(v))){
			vertices.add(v);
		}
	}

	@Override
	public void borrarVertice(int verticeId) {//BOORA UN VERTICE DEL GRAFO. COMPLEJIDAD O((Cantidad de Vertices)+(Cantidad de Arcos)) YA QUE ADEMAS DE QUITAR EL VERTICE DE LA LISTA TAMBIEN DEBE QUITAR DE LA LISTA DE ARCOS TODOS LOS ARCOS QUE VAYAN HACIA EL VERTICE ELIMINADO.
		// TODO Auto-generated method stub
		Vertice<T> v = this.getVertice(verticeId);
		this.vertices.remove(v);
		Iterator<Vertice<T>> it = vertices.iterator();
		while(it.hasNext()) {
			Vertice<T> v2 = it.next(); 
			Arco<T> arc = v2.getArco(verticeId);
			if (arc != null)
				v2.getArcos().remove(arc);
			
		}
		

	}

	private Vertice<T> getVertice(int id){//DEVUELVE EL VERTICE SOLICITADO. COMPLEJIDAD O(Cantidad de Vertices) YA QUE EN EL PEOR CASO RECORRE TODA LA LISTA DE VERTICES.
		Iterator<Vertice<T>> it = vertices.iterator();
		while (it.hasNext()) {
			Vertice<T> v = it.next();
			if(v.getId() == id)
				return v;
		}
		return null;
	}
	
	@Override
	
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {//AGREGA UN ARCO ENTRE LOS VERTICES SOLICITADOS. COMPLEJIDAD O((Cantidad de Vertices)+(v.getArcos.size)) YA QUE ANTES DE AGREGAR EL ARCO LOCALIZA EL VERTICE DE ORIGEN, Y LUEGO COMPRUEBA QUE NO EXISTA LA CONEXION QUE SE QUIERE AGREGAR, RECORRIENDO LOS ARCOS DEL VERTICE ORIGEN.
		// TODO Auto-generated method stub
		Vertice<T> v = this.getVertice(verticeId1);
		if(v != null) {
			Arco<T> arc = v.getArco(verticeId2);
			if(arc == null)
				v.addArco(verticeId2, etiqueta);
		}
	}


	@Override
	public void borrarArco(int verticeId1, int verticeId2) {//BORRA EL ARCO ENTRE LOS VERTICES SOLICITADOS. COMPLEJIDAD O((Cantidad de Vertices)+(v.getArcos.size)) YA QUE DEBE LOCALIZAR EL VERTICE ORIGEN Y LUEGO BUSCAR EL ARCO SOLICITADO EN SU LISTA DE ARCOS.
		// TODO Auto-generated method stub
		Vertice<T> v = this.getVertice(verticeId1);
		if(v != null)
			v.borrarArco(verticeId2);
	}

	@Override
	public boolean contieneVertice(int verticeId) {//COMPRUEBA SI EXISTE EL VERTICE SOLICITADO EN EL GRAFO. COMPLEJIDAD O(Cantidad de Vertices) YA QUE EN EL PEOR CASO RECORRE TODA LA LISTA DE VERTICES DEL GRAFO.
		// TODO Auto-generated method stub
		Vertice<T> v = this.getVertice(verticeId);
		return (v != null);
		
	}

	
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {//COMPRUEBA SI EXISTE UN ARCO ENTRE 2 VERTICES. COMPLEJIDAD O((Cantidad de Vertices)+(v.getArcos.size)) YA QUE DEBE RECORRER LA LISTA DE VERTICES Y LUEGO LA LISTA DE ARCOS DE DICHO VERTICE.
		// TODO Auto-generated method stub
		Vertice<T> v = this.getVertice(verticeId1);
		if(v != null) {
			Arco<T> arc = v.getArco(verticeId2);
			if(arc != null)
				return true;
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {//DEVUELVE EL ARCO ENTRE LOS 2 VERTICES SOLICITADOS. COMPLEJIDAD O((Cantidad de Vertices)+(v.getArcos.size) YA QUE PRIMERO LOCALIZA EL VERTICE ORIGEN Y LUEGO BUSCA EN SU LISTA DE ARCOS EL ARCO SOLICITADO.
		Vertice<T> v = this.getVertice(verticeId1);
		if(v != null) {
			Arco<T> arc = v.getArco(verticeId2);
			if(arc != null)
				return arc;
		}
		return null;
			
	}

	@Override
	public int cantidadVertices() {//DEVUELVE LA CCANTIDAD DE VERTICES DEL GRAFO. COMPLEJIDAD O(Cantidad de Vertices).
		// TODO Auto-generated method stub
		return vertices.size();
	}

	@Override
	public int cantidadArcos() {//DEVUELVE LA CANTIDAD DE ARCOS DEL GRAFO. COMPLEJIDAD O((Cantidad de vertices)+(Cantidad de Arcos del grafo)) YA QUE DEBE RECORRER TODO EL GRAFO Y LOS ARCOS DE CADA VERTICE.
		// TODO Auto-generated method stub
		int cantidad = 0;
		Iterator<Vertice<T>> it = vertices.iterator();
		while(it.hasNext())
			cantidad += it.next().getArcos().size();
		return cantidad;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {//RETORNA UN ITERADOR SOBRE UNA LISTA DE ID'S DE LOS VERTICES. COMPLEJIDAD O(Cantidad de Vertices) YA QUE ACCEDE A CADA VERTICE PARA PEDIR SU ID.
		// TODO Auto-generated method stub
		Iterator<Vertice<T>> it = this.vertices.iterator();
		LinkedList<Integer> l = new LinkedList<Integer>();
		while (it.hasNext()) {
			l.add(it.next().getId());
		}
		Iterator<Integer> it2 = l.iterator();
		return it2;
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {//RETORNA UN ITERADOR SOBRE UNA LISTA CON LOS ID'S DE LOS ADYACENTES DEL VERTICE SOLICITADO. COMPLEJIDAD O((Cantidad de vertices)+(v.getArcos.size) YA QUE DEBE LOCALIZAR EL VERTICE Y LUEGO ACCEDER A CADA UNO DE SUS ARCOS PARA PEDIR LA ID.
		// TODO Auto-generated method stub
		Vertice<T> v = this.getVertice(verticeId);
		LinkedList<Integer> l = new LinkedList<Integer>();
		if(v != null) {
			Iterator<Arco<T>> it = v.getArcos().iterator();
			while (it.hasNext()) {
				l.add(it.next().getVerticeDestino());
			}
		}
		Iterator<Integer> it2 = l.iterator();
		return it2;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {//RETORNA UN ITERADOR SOBRE UNA LISTA QUE CONTIENE TODOS LOS ARCOS DEL GRAFO. COMPLEJIDAD O(Cantidad de Vertices) YA QUE RECORRE TODA LA LISTA DE VERTICES PARA OBTENER SUS LISTAS DE ARCOS.
		// TODO Auto-generated method stub
		Iterator<Vertice<T>> it = this.vertices.iterator();
		LinkedList<Arco<T>> l = new LinkedList<Arco<T>>();
		while (it.hasNext()) {
			l.addAll(it.next().getArcos());
		}
		Iterator<Arco<T>> it2 = l.iterator();
		return it2;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {//RETORNA UN ITERADOR SOBRE UNA LISTA QUE CONTIENE TODOS LOS ARCOS DEL VERTICE SOLICITADO. COMPLEJIDAD O(Cantidad de Vertices) YA QUE RECORRE TODA LA LISTA DE VERTICES.
		// TODO Auto-generated method stub
		Vertice<T> v = this.getVertice(verticeId);
		Iterator<Arco<T>> it = v.getArcos().iterator();
		return it;
	}

}

