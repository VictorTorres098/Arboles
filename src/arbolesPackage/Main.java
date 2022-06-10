package arbolesPackage;

public class Main {

	public static void main(String[] args) {
		AB<Integer> arbol = new AB<Integer>();
		arbol.agregar(1);
		arbol.agregar(2);
		arbol.agregar(3);
		arbol.agregar(4);
		arbol.agregar(5);
		arbol.agregar(6);
		arbol.agregar(7);
		System.out.println(arbol.buscar(0));
//		ABB<Integer> arbol = new ABB<Integer>();
//		arbol.agregar(6);
//		arbol.agregar(5);
//		arbol.agregar(7);
//		arbol.agregar(8);
//		arbol.agregar(9);
		
		

	}

}
