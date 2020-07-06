package classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Integer> billetes = new LinkedList<Integer>();
		billetes.add(500);
		billetes.add(200);
		billetes.add(5);
		billetes.add(2);
		billetes.add(100);
		billetes.add(1000);
		billetes.add(20);
		billetes.add(1);
		billetes.add(50);
		billetes.add(10);
		int cambio = 5415;
		darCambio(cambio, billetes);
	}
	
	public static void darCambio(int cambio, LinkedList<Integer> billetes) {
		LinkedList<Integer> solucion = new LinkedList<Integer>();
		Iterator<Integer> it = billetes.iterator();
		int suma = 0;//sirve para llevar la cuenta de cuanto dinero se va entregando al usuario.
		int mayor = 0;//sirve para guardar el billete de mayor denominacion del conjunto.
		while ((suma < cambio) && (!billetes.isEmpty())) {
			int aux = 0;
			 while(it.hasNext()){//selecciono el mayor billete posible
				aux = it.next();
				if(aux > mayor)
					mayor = aux;
			}
			//System.out.println(mayor);
			while ((suma + mayor) <= cambio) {
				suma += mayor;
				solucion.add(mayor);
			}
			billetes.remove((Object)mayor);
			mayor = 0;
			it = billetes.iterator();
		}
		System.out.println("la lista de billetes solucion: ");
		System.out.println(solucion);
	}
	
}