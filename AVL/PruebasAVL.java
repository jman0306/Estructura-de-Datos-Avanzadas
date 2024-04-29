package AVL;

public class PruebasAVL {

	public static void main(String[] args) {


        ArbolAVL<Integer> prueba = new ArbolAVL<Integer>();
        prueba.insertar(30);
        prueba.insertar(2);
        prueba.insertar(0);
        prueba.insertar(100);
        prueba.insertar(90);
        prueba.insertar(40);
        prueba.insertar(-100);
        prueba.insertar(20);
        prueba.insertar(1);
        prueba.insertar(-110);
        prueba.insertar(10);
        prueba.imprimirPorNivel();



	}

}
