package clases;

import java.util.ArrayList;
import java.lang.Math;

public class Arbin {
	private Tnode head; //raiz

	public Tnode getHead() {
		return head;
	}

	public int getHeight() {//Calcula la altura del arbol llamando al metodo recursivo. Su complejidad es O(n) ya que debe pasar por todos los nodos.
		return getHeight(this.head);
	}
	
	private int getHeight(Tnode actual) {//calcula la altura del arbol arrancando desde 1 para la raiz, su complejidad es O(n) ya que recorre toda la estructura.
		if (actual == null)
			return 0;
		else
			return (Math.max(getHeight(actual.getLeft()), getHeight(actual.getRight()))+1);
	}
	
	public Arbin() {//constructor
		super();
		this.head = null;
	}
	
	public boolean isEmpty() {//comprueba si el arbol esta vacio. Su complejidad es O(1) ya que solo esta haciendo una consulta y no depende de ninguna entrada.
		return (this.head == null);
	}
	
	public void insert(int elem) {//inserta el elemento o llama al insert recursivo. Suponiendo un arbol balanceado su complejidad es O(log(n)) suponiendo un arbol balanceado.
		if (this.isEmpty()) {
			this.head = new Tnode(elem);
		}
		else
			insert(elem, this.head);
		
	}
	
		
	
	private void insert (int elem, Tnode actual) {//inserta un elemento usando recursion. Suponiendo un arbol balanceado su complejidad es O(log(n)), debido a que no necesita recorrer toda la estructura sino que recorre solo la rama correcta.
		if (actual.getElem() == elem) {
			System.out.println("El elemento ya existe");
		}
		else {
			if (actual.getElem() > elem) {
				if (actual.getLeft() == null) {
					Tnode aux = new Tnode(elem);
					actual.setLeft(aux);
				}
				else
					insert(elem, actual.getLeft());
			}
			else {
				if (actual.getRight() == null) {
					Tnode aux = new Tnode(elem);
					actual.setRight(aux);
				}
				else
					insert(elem, actual.getRight());
			}
			
		}
	}
	
	public void printInOrder() {// llama a imprimir en orden recursivo. "--" simboliza null.
		
		if (!this.isEmpty())
				printInOrder(this.head);
		else
			System.out.println("--");
	}
	
	private void printInOrder (Tnode actual) {//imprime en orden usando recursion. Su complejidad es O(n) ya que para imprimir debe pasar por todos los nodos del arbol.
		if (actual != null) {
			printInOrder(actual.getLeft());
			System.out.println(actual.getElem());
			printInOrder(actual.getRight());
		}
		else
			System.out.println("--");
	}
	
	public void printPosOrder () {//llama a imprimir en pos orden recursivo. "--" simboliza null.
		if (this.isEmpty()) {
			System.out.println("--");
		}
		else
			printPosOrder(this.head);
	}
	
	private void printPosOrder(Tnode actual) {//imprime en pos orden usando recursion. Su complejidad es O(n) ya que para imprimir debe pasar por todos los nodos del arbol.
		if (actual.getLeft() != null) {
			printPosOrder(actual.getLeft());
		}
		else {
			System.out.println("--");
		}
		if (actual.getRight() != null) {
			printPosOrder(actual.getRight());
		}
		else {
			System.out.println("--");
		}
		System.out.println(actual.getElem());

	}
	
	public void printPreOrder() {//llama a imprimir en pre orden recursivo. "--" simboliza null.
			
			if (!this.isEmpty())
					printPreOrder(this.head);
			else
				System.out.println("--");
		}
	
	private void printPreOrder(Tnode actual) {//imprime en pre orden usando recursion. Su complejidad es O(n) ya que para imprimir debe pasar por todos los nodos del arbol.
		System.out.println(actual.getElem());
		if (actual.getLeft() != null) {
			printPreOrder(actual.getLeft());
		}
		else {
			System.out.println("--");
		}
		if (actual.getRight() != null) {
			printPreOrder(actual.getRight());
		}
		else {
			System.out.println("--");
		}
	}
	
	public boolean hasElem(int elem) {//busca un elemento llamando al hasElem recursivo. 
		if (this.isEmpty())
			return false;
		if (head.getElem() == elem)
			return true;
		else
			return (hasElem(elem, this.head));
	}
	
	private boolean hasElem(int elem, Tnode actual) {//busca a un elemento usando recursion. Suponiendo un arbol balanceado, su complejidad es O(log(n)) porque solo hay un camino posible al elemento solicitado, de modo que no se recorre toda la estructura.
		if (actual.getElem() == elem)
			return true;
		else {
			if (actual.getElem() > elem) {
				if (actual.getLeft() == null)
					return false;
				else 
					return (hasElem(elem, actual.getLeft()));
			}
			else {
				if (actual.getRight() == null)
					return false;
				else 
					return (hasElem(elem, actual.getRight()));
				}
		
			}

	}
	
	public ArrayList<Integer> getFrontier() {//Retorna una lista con todos los nodos hoja llamando al metodo recursivo. La complejidad total es O(n).
		ArrayList<Integer> list = new ArrayList<Integer>();
		getFrontier(this.head, list);
		return list;
	}

	private void getFrontier(Tnode actual, ArrayList<Integer> list) {//Retorna una lista con todos los nodos hoja. Su complejidad total es O(n) ya que para encontrar las hojas recorre todas las ramas posibles.
		if(actual != null) {
			getFrontier(actual.getLeft(), list);
			if(actual.isLeaf())
				list.add(actual.getElem());
			getFrontier(actual.getRight(), list);
		}		
	}
	
	public ArrayList<Integer> getElemAtLevel(int level) {//llama al metodo recursivo para cargar una lista y luego la retorna. En el peor caso su complejidad es O(n) ya que puede llegar a visitar todos los nodos del arbol.
		ArrayList<Integer> list = new ArrayList<Integer>();
		int aux = 0;
		if((!this.isEmpty()) && (level >= 0))
			this.getElemAtLevel(this.head, list, level, aux);
		return list;
	}
	
	private void getElemAtLevel(Tnode actual, ArrayList<Integer> list, int level, int aux) {//carga la lista con todos los elementos pertenecientes al nivel de profundidad solicitado. En el peor caso su complejidad es O(n) ya que puede llegar a visitar todos los nodos del arbol.
		if (aux == level)
			list.add(actual.getElem());
		if (aux < level) {
			if(actual.getLeft() != null)
				getElemAtLevel(actual.getLeft(), list, level, aux+1);
			if(actual.getRight() != null)
				getElemAtLevel(actual.getRight(), list, level, aux+1);
		}
	}

	public ArrayList<Integer> getLongestBranch() {//llama al metodo recursivo para cargar la lista solution y luego la retorna.
		ArrayList<Integer> solution = new ArrayList<Integer>();
		ArrayList<Integer> aux = new ArrayList<Integer>();
		getLongestBranch(this.head, solution, aux);
		return solution;
	}
	
	private void getLongestBranch(Tnode actual, ArrayList<Integer> sol, ArrayList<Integer> aux) {//carga la lista sol con la rama mas larga del arbol. Su complejidad es O(n) en el peor de los casos porque debe recorrer todo el arbol.
		if (actual == null) {
			if (aux.size() > sol.size()) {
				sol.removeAll(sol);
				sol.addAll(aux);}
		}
		else {
			aux.add(actual.getElem());
			getLongestBranch(actual.getLeft(), sol, aux);
			getLongestBranch(actual.getRight(), sol, aux);
			aux.remove(aux.size()-1);
		}
	}
	
	public int getMaxElem() {
		int max = 0;
		if (!this.isEmpty())
			max = getMaxElem(this.head);
		return max;
	}
	
	private int getMaxElem(Tnode actual) {// busca el numero mas grande del arbol. Su complejidad es O(log(h)) en el peor caso, siendo "h" la altura del arbol, ya que el metodo recorre solo una rama.
		if (actual.getRight() == null)
			return actual.getElem();
		else
			return getMaxElem(actual.getRight());
	}
	
	public void delete (int elem) {//Llama al metodo recursivo para localizar y borrar del arbol el nodo solicitado. Suponiendo que el arbol esta balanceado su complejidad es O(log(n)).
		if (this.isEmpty())
			System.out.println("el elemento no existe");
		else 
			delete (this.head, elem);
	}
	
	private void delete(Tnode actual, int elem) {//Localiza y borrar del arbol el nodo solicitado. Suponiendo que el arbol esta balanceado su complejidad es O(log(n)).
		if (actual != null) {
			if (actual.getElem() == elem) {//PARA EL CASO EN EL QUE EL ELEMENTO ESTA EN EL NODO EN QUE ESTOY PARADO.
				if (actual.isLeaf())//SI EL NODO ES HOJA
					actual=null;
				else if ((actual.getLeft() != null) && (actual.getRight() != null)) {//SI EL NODO TIENE 2 HIJOS.
					int maxElem = getMaxElem(actual.getLeft());//busco al nodo mas derecho del hijo izquierdo.
					delete(actual, maxElem);//borro el mas derecho que busque en la linea anterior.
					actual.setElem(maxElem);//reemplazo el nodo que queria eliminar originamlente por el maxElem que ya desvincule en la linea anterior
				}
				else if (actual.getLeft() != null)//SI EL NODO TIENE SOLO EL HIJO IZQUIERDO.
					actual.setLeft(actual.getLeft());
				else //SI EL NODO TIENE SOLO EL HIJO IZQUIERDO.
					actual.setRight(actual.getRight());
		}
			else {
				if (elem < actual.getElem()) {
					if (actual.getLeft() != null) {
						if (actual.getLeft().getElem() == elem) {//SI EL ELEMENTO A ELIMINAR ESTA EN EL HIJO IZQUIERDO.
							if (actual.getLeft().isLeaf())//el nodo a eliminar es un nodo hoja.
								actual.setLeft(null);
							else
								if ((actual.getLeft().getLeft() != null) && (actual.getLeft().getRight() != null)) {//el nodo a eliminar tiene 2 hijos.
									int maxElem = getMaxElem(actual.getLeft().getLeft());
									delete(actual.getLeft(), maxElem);
									actual.getLeft().setElem(maxElem);
								}
								else //el nodo a eliminar tiene un solo hijo asiq hay que saltar directo a ese hijo.
									if (actual.getLeft().getLeft() != null)//hay que saltar hasta el hijo izquierdo.
										actual.setLeft(actual.getLeft().getLeft());
									else //hay que saltar hasta el hijo derecho.
										actual.setLeft(actual.getLeft().getRight());
						}
						else 
							delete(actual.getLeft(), elem);//continuo la busqueda por izquierda
					}
					else System.out.println("el elemento a eliminar no existe");
				}
			  else	
				  if (elem > actual.getElem()) {//A PARTIR DE ACA ES LO MISMO PERO CONSULTANDO HACIA EL HIJO DERECHO.
					if (actual.getRight() != null) {
						if (actual.getRight().getElem() == elem) {
							if (actual.isLeaf())
								actual.setRight(null);
							else
								if ((actual.getRight().getLeft() != null) && (actual.getRight().getRight() != null)) {
									int maxElem = getMaxElem(actual.getRight().getLeft());
									delete(actual.getRight() , maxElem);
									actual.getRight().setElem(maxElem);
								}
								else {
									if (actual.getRight().getLeft() != null)
										actual.setRight(actual.getRight().getLeft());
									else
										actual.setRight(actual.getRight().getRight());
								}
						}
						else
							delete(actual.getRight(), elem);
					}
					else System.out.println("el elemento a eliminar no existe");
				}
			}
			}			
	}
	
}