import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Vocabulario {
	private Vector<String> palabras;
	
	public Vocabulario(String s) {
		palabras = new Vector<String>();
		
		String strArray[] =  s.split(" ");
		
		
		for (int i = 0; i < strArray.length; i++) {
			palabras.add(strArray[i]);
		}
	}


	public int getDiferentes() {
		Set<String> palabrasSinRepetir = new HashSet<String>(); 
		for(String it: palabras) {
			palabrasSinRepetir.add(it);
		}
		return (palabrasSinRepetir.size());
	}
}
