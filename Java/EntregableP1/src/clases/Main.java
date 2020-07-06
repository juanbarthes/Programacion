package clases;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySimpleLinkedList myList = new MySimpleLinkedList();
		myList.insertBack(3);
		myList.insertBack(5);
		myList.insertBack(2);
		myList.insertBack(2);
		myList.insertBack(7);
		myList.insertBack(19);
		myList.insertBack(14);
		myList.insertBack(28);
		myList.insertBack(28);

		System.out.println("Secuencias: " + myList.getSecuences());
	}

}
