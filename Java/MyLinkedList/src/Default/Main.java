package Default;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySimpleLinkedList miLista = new MySimpleLinkedList();
		miLista.insertBack(0);
		miLista.insertBack(1);
		miLista.insertBack(2);
		miLista.insertBack(3);
		miLista.insertBack(4);
		miLista.insertBack(5);
		System.out.println("mi lista: ");
		System.out.println(miLista);
		
		MySimpleLinkedList otraLista = new MySimpleLinkedList();
		otraLista.insertBack(0);
		otraLista.insertBack(3);
		otraLista.insertBack(0);
		otraLista.insertBack(2);
		System.out.println("otra lista: ");
		System.out.println(otraLista);
		
		System.out.println("lista interseccion: ");
		System.out.println(miLista.intersection(otraLista));
	}

}
