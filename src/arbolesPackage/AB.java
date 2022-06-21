package arbolesPackage;

public class AB <T> {
	private Nodo <T> raiz;
	
	public static class Nodo <T>{
		private T info;
		private Nodo<T> izq;
		private Nodo<T> der;
		
		public Nodo(T elem) {
			info = elem;
		}
		public T getInfo() {
			return info;
		}
		public void setInfo(T info) {
			this.info = info;
		}
		public Nodo<T> getIzq(){
			return izq;
		}
		public void setIzq(Nodo<T> izq) {
			this.izq = izq;
		}
		public Nodo<T> getDer(){
			return der;
		}
		public void setDer(Nodo<T> izq) {
			this.der = izq;
		}
		@Override
		public String toString() {
			return info.toString();
		}
		
	}
	
	public void agregar(T elem) {
		Nodo<T> nuevo = new Nodo<T>(elem);
		if(raiz == null) {
			raiz = nuevo;
		}else {
			agregar(raiz, nuevo);
		}
	}
	protected void agregar(Nodo<T> padre, Nodo<T> nuevo) {
		if(padre.getIzq() == null) {							//Si no tiene nada en su hijo izq agrega el nuevo elemento a izq	
			padre.setIzq(nuevo);
		}else {												
			if(padre.getDer() == null) {							//Si izq !null lo agrego al hijo der
				padre.setDer(nuevo);
			}else {											//si ambos no son !null se fija en los hijo izq y der de der
				agregar(padre.getDer() , nuevo);              	//Desicion de implementacion: generar el arbol a derecha
			}
		}
	}
	
	public boolean buscar(T elem){
		if(raiz == null)
			return false;
		return buscar(raiz, elem);
	}
	
	protected boolean buscar(Nodo<T> nodo, T elem){
		if(nodo == null)
			return false;
		if(nodo.getInfo().equals(elem))
			return true;
		return buscar(nodo.getDer(), elem) || buscar(nodo.getIzq(), elem);
	}
	public int cantNodos() {
		return(raiz == null) ? 0 : cantNodos(raiz);
	}
	//complejidad O(N) porque para el pero caso hay que recorrer todos los nodos
	private int cantNodos(Nodo<T> nodo) {
		int cantIzq = (nodo.getIzq() == null ) ? 0 : cantNodos(nodo.getIzq());
		int cantDer = (nodo.getDer() == null) ? 0 : cantNodos(nodo.getDer());
		
		return 1 + cantIzq + cantDer;
	}
	
	//cada operacion de altura() es O(1)
	//se producen una llamada recursiva por cada nodo
	//entonces se produce O(1).n = O(N)
	
 	public int altura() {
		return (raiz == null) ? 0 : altura(raiz);
	}
	private int altura(Nodo<T> nodo) {
		//busco la altura recursivamente de cada lado
		int altIzq = (nodo.izq == null) ? 0 : altura(nodo.izq);
		int altDer = (nodo.der == null) ? 0 : altura(nodo.der);
		//+1 porque suma la altura del padre de todos
		return 1 + Math.max(altIzq, altDer);
	}
	//version 1
	public boolean balanceado() {
		return(raiz == null) ? true : balanceado(raiz);
	}
	//la complejidad de balanceado es O(n2) ya que en cada nodo que te pares, tenes que ver la altura
		//y a la vez tenes que pasar por todos los nodos, osea debes de ver la altura de todos los nodos
		//O(n)*O(n) = O (n2)
		private boolean balanceado(Nodo<T> nodo) {
			boolean ret = true;
			int altIzq = 0;
			int altDer = 0;
			
			if(nodo.getIzq() != null) {
				altIzq = altura(nodo.getIzq());
				ret = ret && balanceado(nodo.getIzq());
			}
			
			if(nodo.getDer() != null) {
				altDer = altura(nodo.getDer());
				ret = ret && balanceado(nodo.getDer());
			}
			
			ret = ret && Math.abs(altIzq - altDer) <= 1;
			
			return ret;
		}
	//version2: a verces convien hacer el caso base sobre el nodo
	public boolean balanceado2() {
			return balanceado2(raiz);
	}
		
	private boolean balanceado2(Nodo<T> nodo) {
			if(nodo == null)
				return true;
			
			else {
				int altIzq = (nodo.getIzq() == null) ? 0 : altura(nodo.getIzq());
				int altDer = (nodo.getDer() == null) ? 0 : altura(nodo.getDer());
				
				return Math.abs(altIzq - altDer) <= 1 && balanceado(nodo.getIzq()) &&
				       balanceado(nodo.getDer());
			}
				
	}
	public int cantHojas() {
		if(raiz == null) 
			return 0;
		return cantHojas(raiz);
	}
	private int cantHojas(Nodo<T> nodo) {
		if(nodo == null)
			return 0;
		if(nodo.getIzq() == null && nodo.getDer() == null)
			return 1;
		return cantHojas(nodo.getDer()) + cantHojas(nodo.getIzq());
	}
	public Nodo<T> getRaiz(){
		return raiz; 
	}
	public int getValorRaiz() {
		int raizNum = (int) raiz.getInfo();
		return raizNum;
	}
	
	public int sumaInternos() {
		if(raiz == null)
			return 0;
		
		return sumaInternos((Nodo<Integer>) raiz);
	}
	private int sumaInternos(Nodo<Integer> nodo) {
		if(nodo == null)
			return 0;
		if(nodo.equals(getRaiz()))
			return sumaInternos(nodo.getIzq()) + sumaInternos(nodo.getDer());
		if(!esHoja(nodo))
			return sumaInternos(nodo.getIzq()) + sumaInternos(nodo.getDer());
		return sumaInternos(nodo.getIzq()) + sumaInternos(nodo.getDer());
	}
	//es O(n) porque se tiene que recorrer todos los nodos para determinar cuales son las hojas
	//y sumarlos, y la operacion esHoja() es O(1) que se debe realizar por cada mpdp osea
	//es(1) * n nodos, O(N)
	private boolean esHoja(Nodo<Integer> nodo) {
		if(nodo.getIzq() == null && nodo.getDer() == null)
			return true;
		else
			return false;
	}
	public static boolean esABB(AB<Integer> ab) {
		if(ab.getRaiz() == null)
			return false;
		return esABB(ab.getRaiz());
	}
	//bonus track (0,5)
	//Decir cual es la complejidad del metodo en a. Explicar si cambia la complejidad
	//cuando el arbol esta balanceado. Justificando la respuesta.
	
	//es O(N) que tengo que recorrer todos los nodos para corroborrar que se cumpla la condicion de ANN
	//no cambia la complejidad si esta balanceado o no ya que en cualquier caso debo verificar que todos los 
	//subarboles del original sean arboles de busqueda binario, es decir que por cada nodo debo verificar que sea arbol binario de busqueda
	private static boolean esABB(Nodo<Integer> nodo) {
		if(nodo == null)
			return true;
		
		if(nodo.getIzq()!= null && nodo.getDer()!= null) {
			if(nodo.getInfo().compareTo(nodo.getIzq().getInfo()) > 0
			&& nodo.getInfo().compareTo(nodo.getDer().getInfo()) < 0)
				return true && esABB(nodo.getIzq()) && esABB(nodo.getDer());
			
			return false;
		}
		return true;
	}
	//retorna un String con todos los elementos del Arbol, siguiendo el recorrido inOrden
	public String toString() {
		return "{" + toStringInorden(raiz) + "]";
	}
	private String toStringInorden(Nodo<T> nodo) {
		StringBuilder sb = new StringBuilder();
		if(nodo!= null) {
			sb.append(toStringInorden(nodo.getDer()));
			sb.append(nodo.getInfo()).append(" ");
			sb.append( toStringInorden(nodo.getIzq()) );
		}
		return sb.toString();
	}
	public String preOrden() {
		return "{" + toStringPreorden(raiz) + "}";
	}
	
	private String toStringPreorden(Nodo<T> nodo) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		if (nodo!=null) {
			sb.append(nodo.getInfo()).append(" ");
			sb.append( toStringPreorden(nodo.getIzq()) );
			sb.append( toStringPreorden(nodo.getDer()) );
		}
		return sb.toString();
	}
	public String toString2(){
		return (raiz == null) ? "" : toString2(raiz);
	}
	
	private String toString2(Nodo<T> nodo){
		String ret = nodo.getInfo().toString();
		if (nodo.getIzq() != null) ret = ret + toString2(nodo.getIzq());
		if (nodo.getDer() != null) ret = ret + toString2(nodo.getDer());
		return ret;
	}	
}
