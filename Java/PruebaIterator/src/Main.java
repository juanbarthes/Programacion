import java.util.Comparator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Persona p1= new Persona(100, "pelado PPPÚTTOO!!!");
		Persona p2= new Persona(152, "juan");
		Persona p3= new Persona(2, "fede");
		Persona p4= new Persona(6345, "JEFE");
		Persona p5= new Persona(14, "BERDUN");
		Coleccion col=new Coleccion();
		col.add(p1);
		col.add(p2);
		col.add(p3);
		col.add(p4);
		col.add(p5);
		Comparator<Persona> c1= new AgeComparator();
		col.ordenarNombre(c1);
		System.out.println(col.toString());

		
	}

}
