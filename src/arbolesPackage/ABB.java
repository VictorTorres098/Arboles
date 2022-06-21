package arbolesPackage;

import java.util.ArrayList;

public class ABB<T extends Comparable<T>> extends AB<T>{
	//Complejidad logaritmica, siempre te quedas con la mitad cada vez O(Log(N)) si esta balanceado
	//Si esta desbalanceado puede ser hasta una lista(todos los nodos de un solo lado) Y llegar a ser O(N)
	
	//para ello se crearon algoritmos de rebalanceo
	
	//Tiene que estar protected en esta clase como en la de AB porque si esta privada no lo puede veer al metodo
	//pero es necesario sobreecribirlo, entonces es necesario ponerlos en protected
	@Override
	protected boolean buscar(Nodo<T> nodo, T elem) {
		if(nodo==null)
			return false;
		
		if(elem.equals(nodo.getInfo()))
			return true;
		//llama recursiva
		if(elem.compareTo(nodo.getInfo()) < 0) //si es menor q 0 significa que la de la izq < que lo que esta a la der
			return buscar(nodo.getIzq(), elem);
		
		return buscar(nodo.getDer(), elem); //calcula las rama derecha 
	}
	@Override
	protected void agregar(Nodo<T> nodo, Nodo<T> nuevo) {
		if(nuevo.getInfo().compareTo(nodo.getInfo()) < 0)
			if(nodo.getIzq() == null)
				nodo.setIzq(nuevo);
			else
				agregar(nodo.getIzq(), nuevo);
		else
			if(nuevo.getInfo().compareTo(nodo.getInfo()) > 0)
				if(nodo.getDer() == null) 
					nodo.setDer(nuevo);
				else
					agregar(nodo.getDer(), nuevo); //en este caso no va haber elementos repetidos, por eso 
												//en caso de ser igual no se agrega el elem
	}
	public int nivel(T elem) {
		if(getRaiz() == null || !buscar(elem)) //si el arbol esta vacio o El elemento no esta
			return -1;							//retorna -1
		return nivel(elem,getRaiz());			
	}
	private int nivel(T elem, Nodo<T> nodo) {
		if(nodo.getInfo().equals(elem))			//si el elemento esta en la raiz 
			return 1;							//retorna 1
		if(nodo.getInfo().compareTo(elem) < 0)	//si ele elemento es menor que el nodo actual busca en su hijo der
			return 1 + nivel(elem, nodo.getDer()); //llamada recursiva pero va sumando uno cada vez que baja de nivel	
		else
			return 1 + nivel(elem, nodo.getIzq()); //el mismo proceso se repite aca 
	}	//al retornar suma los +1 de todos los returns y nos devuelve en que nivel se encuentra el elemento
	
	public T minimo() {
		if(getRaiz() == null)
			return null;
		return minimo(getRaiz());
	}
	private T minimo(Nodo<T> nodo) {
		if(nodo.getIzq() == null)
			return nodo.getInfo();
		return minimo(nodo.getIzq());	//llega hasta el ultimo nodo izq el cual seria el nodo con menor valor
	}
	public T maximo() {
		if(getRaiz() == null) 
			return null;
		return maximo(getRaiz());
	}
	private T maximo(Nodo<T> nodo) {
		if(nodo.getDer() == null)
			return nodo.getInfo();
		return maximo(nodo.getDer()); //llega hasta el ultimo nodo der el cual seria el nodo con mayor valor
	}
	// Un metodo que retorne el elemento en la posicion i,
	// segun el recorrido inorden
	// Si el i no es un valor valido, retornar null
	// El minimo elemento es con i=0
	public T iesimo(int i) {
		if(i < 0 || i > cantNodos() - 1)
			return null;
		if(i == 0)
			return minimo();
		else
			return iesimo(getRaiz(), i);
	}
	private T iesimo(Nodo<T> nodo, int i) {
		return null;
	}
	// Dados dos elementos devuelve los valores de los nodos
	// que hay entre ellos (incluidos los bordes)
	// o {} si alguno no pertenece
	// x ej 
	// unABB.elemsEntre(1, 5) = {12345}
	public String elemsEntre(T elem1, T elem2) {
		if(!buscar(elem1) || !buscar(elem2))
			return "{}";
		if(getRaiz() == null)
			return null;
		
		return "{" + elemsEntre(elem1, elem2, getRaiz()) + "}";
		
	}

	private String elemsEntre(T elem1, T elem2, Nodo<T> nodo) {
		StringBuilder sb = new StringBuilder();

		if (nodo != null) {
			
			sb.append(elemsEntre(elem1, elem2, nodo.getIzq()));
			
			if (nodo.getInfo().compareTo(elem2) <= 0 && nodo.getInfo().compareTo(elem1) >= 0) {
				
				sb.append(nodo.getInfo());
				sb.append(" ");
				sb.append(elemsEntre(elem1, elem2, nodo.getDer()));
			}
		}
		return sb.toString();
	}
	// dado un valor devuelve la cantidad de elementos de la subrama a la que pertenece elem
	// o 0 en caso contrario
	public int elemsEnSubrama(T elem) {
		if (!this.buscar(elem))
			return 0;
		return elemsEnSubrama(getRaiz(), elem);
	}
	private int elemsEnSubrama(Nodo<T> actual, T elem) {
		
		if(actual == null)
			return 0;
		
		if(actual.getInfo().compareTo(elem) < 0)
			return 1 + elemsEnSubrama(actual.getDer(), elem) + elemsEnSubrama(actual.getIzq(), elem);
			
		return elemsEnSubrama(actual.getDer(), elem) + elemsEnSubrama(actual.getIzq(), elem);
	}
	//retorna la suma de los elementos en el arbol que son mayores que la diferencia entre mayor y menor elemento
	public int sumaMoayoresDifExtremos() {
		if(getRaiz() == null)
			return 0;
		else if(getRaiz() != null && cantNodos() == 1) {
			return (int) getRaiz().getInfo();
		}
		int minimoABB = (int) minimo();
		int maximoABB = (int) maximo();
		return sumaMayoresDifExtremos(getRaiz(), minimoABB - maximoABB);
	}
	private int sumaMayoresDifExtremos(Nodo<T> nodo, int difExtremos) {
		if(nodo == null)
			return 0;
		int valorNodo = (int) nodo.getInfo();
		if( valorNodo > difExtremos)
			return valorNodo + sumaMayoresDifExtremos(nodo.getIzq(), difExtremos) + sumaMayoresDifExtremos(nodo.getDer(), difExtremos);
		return sumaMayoresDifExtremos(nodo.getIzq(), difExtremos) + sumaMayoresDifExtremos(nodo.getDer(), difExtremos);
	}
	
	public int mayoresNoHojas(T elem) {
		if(getRaiz() == null)
			return 0;
		return mayoresNoHojas(getRaiz(), elem);
	}
	private int mayoresNoHojas(Nodo<T> nodo, T elem) {
		if(nodo == null)
			return 0;
		if((nodo.getIzq() != null || nodo.getDer() != null) && nodo.getInfo().compareTo(elem) > 0)
			return 1 + mayoresNoHojas(nodo.getIzq(), elem) + mayoresNoHojas(nodo.getDer(), elem);
		return mayoresNoHojas(nodo.getIzq(), elem) + mayoresNoHojas(nodo.getDer(), elem);
	}
	public String dosHijos() {
		if(getRaiz() == null)
			return "";
		return dosHijos(getRaiz());
	}
	private String dosHijos(Nodo<T> nodo) {
		if(nodo == null)
			return "";
		if(nodo.getIzq() != null && nodo.getDer() != null)
			return nodo.getInfo() + ", " + dosHijos(nodo.getIzq()) + dosHijos(nodo.getDer());
		return dosHijos(nodo.getIzq()) + dosHijos(nodo.getDer());
	}
	public int sumarSubArbol(int num) {
		return sumarSubArbol(num, getRaiz());
	}
	private int sumarSubArbol(int num, Nodo<Integer> nodo) {
		if(nodo == null)
			return 0;
		if(nodo.getInfo() == num)
			return sumarElementos(nodo);
		if(nodo.getInfo() < num)
			return sumarSubArbol(num, nodo.getDer());
		else
			return sumarSubArbol(num, nodo.getIzq());
	}
	private int sumarElemetos(Nodo<Integer> nodo) {
		if(nodo == null)
			return 0;
		else
			return sumarElementos(nodo.getDer().getInfo()) + sumarElementos(nodo.getIzq().getInfo());
	}
	
}
