package clases;

public class MySimpleLinkedList {
	
	protected Node first;
	protected Node last;
	private int cant;
	
	public MySimpleLinkedList() {
		this.first = null;
		this.last = null;
		this.cant = 0;
	}
	
	public void insertFront(Object o) {
		//para insertar al priuncipio.
		if(this.isEmpty()) {
			this.first = new Node(o, null);
			this.last = first;
		}
		else {
			Node tmp = new Node(o,null);
			tmp.setNext(this.first);
			this.first = tmp;			
			}
		this.cant++;
	}
	
	public void insertBack(Object o) {
		//para insertar al final.
		if(this.isEmpty()) {
			this.first = new Node(o, null);
			this.last = first;
		}
		else {
			Node tmp = new Node(o, null);
			this.last.setNext(tmp);
			this.last = tmp;
		}
		cant++;
	}
	
	public void insertInOrder(Object o) {
		//para insertar ordenado.
		//solo sirve para listas de numeros.
		if((this.isEmpty()) || ((int)o <=(int)first.getInfo())) {
			this.insertFront(o);
		}
		else 
			if ((int)last.getInfo() <= (int)o)
				this.insertBack(o);
			else {
				Node aux = first;
				while((int)aux.getNext().getInfo() <= (int)o)
					aux = aux.getNext();
				Node tmp = new Node(o,null);
				tmp.setNext(aux.getNext());
				aux.setNext(tmp);
				cant++;
			}
		}
	
	public Object extractFront() {
		// para extraer el primero.
		Object aux = this.first.getInfo();
		first = first.getNext();
		if (first == null)
			last = null;
		this.cant--;
		return aux;
	}

	public boolean isEmpty() {
		//consultar si esta vacia.
		if (this.first == null)
			return true;
		else
			return false;
	}

	public int size() {
		// tamaño de la lista.
		return this.cant;
	}
	
	public Object get(int index) {
		// TODO
		if (index < this.size()) {
			Node temp = this.first;
			while (index > 0) {
				temp = temp.getNext();
				index --;
			}
			return temp.getInfo();
		}
		else
			return null;
	}
	
	public int indexOf (Object o) {
		//para comprobar si existe un elemento y devolver su posicion.
		Node temp = this.first;
		int index = 0;
		while((temp != null)&& !(temp.getInfo().equals(o))) {
			index++;
			temp = temp.getNext();
		}
		if(temp != null)
			return index;
		else
			return -1;
	}
	
	public MySimpleLinkedList intersection(MySimpleLinkedList anotherList) {
		MySimpleLinkedList solution = new MySimpleLinkedList();
		Node aux = this.first;
		for(int i = 0; i < this.size(); i++) {
			if(anotherList.indexOf(aux.getInfo()) != -1) {
				solution.insertInOrder(aux.getInfo());
			}
			aux = aux.getNext();
		}
		return solution;
	}
	
	@Override
    public String toString() {
		//para imprimir la lista
        if (!this.isEmpty()) {
            String s = "[";
            Node n = this.first;
            for (int i = 0; i < this.size(); i++) {
                if (i < (this.size() - 1))
                    s += n.getInfo() + ", ";
                else
                    s += n.getInfo();
                n = n.getNext();
            }
            s += "]";
            return s;
        }
        return "LISTA VACIA";
    }
}
