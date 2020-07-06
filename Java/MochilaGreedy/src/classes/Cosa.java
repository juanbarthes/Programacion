package classes;

public class Cosa {
	private String name;
	private float valor;
	private float peso;
	public Cosa(String name, float valor, float peso) {
		super();
		this.name = name;
		this.valor = valor;
		this.peso = peso;
	}
	public String getName() {
		return name;
	}
	public float getValor() {
		return valor;
	}
	public float getPeso() {
		return peso;
	}
	@Override
	public String toString() {
		return "Cosa [name=" + name + ", valor=" + valor + ", peso=" + peso + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cosa other = (Cosa) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public float getPesoValor () {
		return (this.getValor()/this.getPeso());
	}
	
}
