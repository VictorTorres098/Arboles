package arbolesPackage;

import arbolesPackage.AB.Nodo;

public class ABB <T extends Comparable<T>> {
	
	protected Nodo<T> raiz;
	
	public class Nodo<T>{ //clase interna
		
		private T info;
		private Nodo<T> hijoIzq;
		private Nodo<T> hijoDer;
		
		public Nodo(T info) {
			super();
			this.info = info;
		}
		
		public T getInfo() {
			return info;
		}
		
		public void setInfo(T dato) {
			this.info = info;
		}
		
		public Nodo<T> getHijoIzq() {
			return hijoIzq;
		}
		
		public void setHijoIzq(Nodo<T> hijoIzq) {
			this.hijoIzq = hijoIzq;
		}
		
		public Nodo<T> getHijoDer() {
			return hijoDer;
		}
		public void setHijoDer(Nodo<T> hijoDer) {
			this.hijoDer = hijoDer;
		}
		//fin de subclase
	}
	//nodo raiz
	public ABB() {
	}
	
	public boolean vacio() {
		return raiz == null;
	}
	
	public void agregar(T elem) {
		Nodo<T> nuevo = new Nodo<T>(elem);
		if (raiz==null)
			raiz = nuevo;
		else
			agregar(nuevo,raiz);
	}

	private void agregar(Nodo<T> nuevo, Nodo<T> padre) {
		if (nuevo.getInfo().compareTo(padre.getInfo())<0)
			if (padre.getHijoIzq()==null)
				padre.setHijoIzq(nuevo);
			else
				agregar(nuevo,padre.getHijoIzq());
		else if (nuevo.getInfo().compareTo(padre.getInfo())>0)
			if (padre.getHijoDer()==null)
				padre.setHijoDer(nuevo);
			else
				agregar(nuevo,padre.getHijoDer());
	}
	
	public int cantidadDeNodos() {	
		return cantidadDeNodos(raiz);
	}
	
	private int cantidadDeNodos(Nodo<T> raiz) {
		if(raiz == null) {
			return 0;
		}else {
			return 1 + cantidadDeNodos(raiz.getHijoIzq()) + cantidadDeNodos(raiz.getHijoDer());
		}
	}
	
	public void eliminar (T valor) {
	}
	
//	public boolean buscar( T valor) {
//		
//		if(raiz == null) {
//			return false;
//		}else {
//			return buscar(raiz, valor);
//		}
//	}
//	
//	private boolean buscar(Nodo<T> raiz, T valor) {
//		if(raiz.getInfo().compareTo(valor) < 0) {
//			return buscar(raiz.getHijoIzq(), valor);
//		}
//		if(raiz.getInfo().compareTo(valor) > 0) {
//			return buscar(raiz.getHijoDer(), valor);
//		}
//		return raiz.getInfo().compareTo(valor) == 0;
//	}
		
	
}
