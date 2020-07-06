package clases;

import java.util.ArrayList;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) {
		Arbin tree = new Arbin();
		int level = 2;
		//loadRandomTree(tree);
		// Cargo un arbol completo de ejemplo para demostrar el funcionamiento:
		tree.insert(20);
		tree.insert(10);
		tree.insert(30);
		tree.insert(5);
		tree.insert(15);
		tree.insert(25);
		tree.insert(39);
		tree.insert(3);
		tree.insert(6);
		tree.insert(12);
		tree.insert(18);
		tree.insert(23);
		tree.insert(27);
		tree.insert(35);
		tree.insert(40);
		System.out.println("a continuacion el arbol impreso in order");
        tree.printInOrder();
        tree.printInOrder();
        System.out.println();
        System.out.println();
        System.out.println("a continuacion el arbol impreso en pre order");
        tree.printPreOrder();
        System.out.println();
        System.out.println();
        System.out.println("a continuacion el arbol impreso en pos order");
        tree.printPosOrder();
        System.out.println();
        System.out.println();
		System.out.println("la altura del arbol es: " + tree.getHeight());
		System.out.println();
		System.out.println("el mayor elemento es: " + tree.getMaxElem());
		System.out.println();
		if (tree.hasElem(6))
			System.out.println("el elemento fue encontrado!");
		else
			System.out.println("el elemento no fue encontrado...");
		System.out.println();
		System.out.println("La lista frontera es: ");
		System.out.println(tree.getFrontier());
		System.out.println();
		System.out.println("Los elementos en el nivel " + level + " son: " + tree.getElemAtLevel(level));
		System.out.println();
		System.out.println("la rama mas larga es: " + tree.getLongestBranch());
		System.out.println();
		//A continuacion voy a eliminar un elemento e imprimir el arbol resultante:
		tree.delete(39);//Probar con cualquier numero para probar todos los casos
        System.out.println("a continuacion el arbol impreso in order despues de borrar un nodo");
	}
	
	public static void loadRandomTree(Arbin tree) {//Sirve para generar un arbol aleatorio como lo pide la consigna de la catedra
		ArrayList<Integer> list = new ArrayList<Integer>();
        int max_elements = 15;
        int interval = 40;
        //cargo una lista con nros random
        while (list.size() != max_elements) {
            Integer i = (int)(Math.random() * interval + 1);
            if (!list.contains(i)) {
                list.add(i);
            }
        }
        //uso los elementos de la lista para cargar el arbol
        for (Integer it : list) {
            tree.insert(it);
        }
        System.out.println("esta es una lista de las inserciones que se haran en el arbol: ");
        System.out.println();
        System.out.println(list);
	}

}
