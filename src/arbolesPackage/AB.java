package arbolesPackage;

public class AB <T> {
	private Nodo <T> raiz;
	
	public class Nodo <T>{
		private T info;
		private Nodo<T> izq;
		private Nodo<T> der;
		
		public Nodo(T info) {
			this.info = info;
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
	private void agregar(Nodo<T> padre, Nodo<T> nuevo) {
		if(padre.izq == null) {								//Si no tiene nada en su hijo izq agrega el nuevo elemento a izq	
			padre.izq = nuevo;
		}else {												
			if(padre.der == null) {							//Si izq !null lo agrego al hijo der
				padre.der = nuevo;
			}else {											//si ambos no son !null se fija en los hijo izq y der de der
				agregar(padre.der , nuevo);              	//Desicion de implementacion: generar el arbol a derecha
			}
		}
	}
	
	public Nodo<T> buscar(T elem){
		return (raiz == null) ? null : buscar(raiz, elem);
	}
	private Nodo<T> buscar(Nodo<T> nodo, T elem){
		if(nodo.info.equals(elem)) {
			return nodo;
		}else {
			Nodo<T> izq = null;
			Nodo<T> der = null;
			if(nodo.izq !=null) {
				izq = buscar(nodo.izq, elem);
			}
			if(nodo.der != null) {
				der = buscar(nodo.der, elem);
			}
			//decision de implementacion: si esta en ambos lados, mostramos al izquierdo primero
			if(izq != null ) {
				return izq;
			}else {
				return der;
			}
		}
	}
}
