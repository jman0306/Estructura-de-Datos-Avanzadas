/* Isabela Valls Chaves
* Mariana Arroyo Mu�oz	
* Juan Manuel Ambriz N��ez 
*/

package Tarea1;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PruebaAO {

	public static void main(String[] args) {
		int n = 17770;// ----------------------------------------------------------> numeroElementos
		Pelicula[] pls = new Pelicula[n];

		try {
			FileReader fr = new FileReader("D:\\Descargas\\movie_titles2.txt");
			BufferedReader br = new BufferedReader(fr);
			String cadena;

			int i = 0, clave = 0, a�o = 0;
			String auxCode = "", auxYear = "", nombre = "";

			for (int j = 0; j < n; j++) {
				cadena = br.readLine();

				Pelicula Pelicula1;

				while (cadena.charAt(i) != ',') {
					auxCode = auxCode + cadena.charAt(i);
					i++;
				}
				i++;
				while (cadena.charAt(i) != ',') {
					auxYear = auxYear + cadena.charAt(i);
					i++;
				}
				i++;
				while (i < cadena.length()) {
					nombre = nombre + cadena.charAt(i);
					i++;
				}

				clave = Integer.parseInt(auxCode);
				a�o = Integer.parseInt(auxYear);
				Pelicula1 = new Pelicula(clave, a�o, nombre);
				pls[j] = Pelicula1;
				i = 0;
				auxCode = "";
				auxYear = "";
				nombre = "";
			}

		} catch (Exception ex) {
		}

		// --------------------------------------prueba1------------------------------------------------------
		
		/*
		selectionSort(pls);
		long inicio = System.currentTimeMillis();
		selectionSort(pls);
		long fin = System.currentTimeMillis();
		double tiempo = (double) ((fin - inicio));
		System.out.println(tiempo + " milisegundos");
*/
	
	
	// --------------------------------------prueba2------------------------------------------------------
	/*
		selectionSortI(pls);// invierte
		long inicio = System.currentTimeMillis();
		mergeSort(pls);
		long fin = System.currentTimeMillis();
		double tiempo = (double) ((fin - inicio));
		System.out.println(tiempo + " milisegundos");
*/

	
	// --------------------------------------prueba3------------------------------------------------------
	
	desordena(pls);// desordena
	long inicio = System.currentTimeMillis();
	mergeSort(pls);
	long fin = System.currentTimeMillis();
	double tiempo = (double) ((fin - inicio));
	System.out.println(tiempo + " milisegundos");

}

	// --------------------------------------metodoImprime------------------------------------------------------

	public static <T> void imprimeArr(T aux[]) {
		for (int i = 0; i < aux.length; i++)
			System.out.println(aux[i].toString());

	}

	// --------------------------------------metodoSuffle------------------------------------------------------

	public static <T extends Comparable> void metodoShuffle(Pelicula[] arr) {
		int i = 0;
		metodoShuffle(arr, i);
	}

	private static <T extends Comparable> void metodoShuffle(Pelicula[] arr, int i) {
		Pelicula aux;
		int r = 0;

		if (i < arr.length) {
			aux = arr[i];
			r = 0; // -----cambiar '0' por metodo que escoja numero entre: (i+1, arr.length)
			arr[i] = arr[r];
			arr[r] = aux;
			metodoShuffle(arr, i++);
		} else
			return;
	}

	// --------------------------------------metodoPrueba------------------------------------------------------


	public static Pelicula[] mergeSort(Pelicula[] A) {

		if (A.length <= 1) {
			return A;
		} else {

			Pelicula[] izq;
			Pelicula[] der;

			der = new Pelicula[A.length / 2];

			if (A.length % 2 == 0) {
				izq = new Pelicula[A.length / 2];
			} else {
				izq = new Pelicula[(A.length / 2 + 1)];
			}

			int i;
			for (i = 0; i < izq.length; i++) {
				izq[i] = A[i];
			}
			int k = 0;
			for (int j = i; j < A.length; j++) {
				der[k] = A[j];
				k++;
			}

			Pelicula[] arrayOrdenado = Merge(mergeSort(izq), mergeSort(der));

			return arrayOrdenado;
		}

	}

	public static Pelicula[] Merge(Pelicula[] a, Pelicula[] b) {
		int i = 0;
		int j = 0;
		Pelicula[] c = new Pelicula[a.length + b.length];

		for (int k = 0; k < c.length; k++) {
			if (a[i].compareTo(b[j]) == 1) {
				c[k] = a[i];
				i++;
			} else {
				c[k] = b[j];
				j++;
			}

			if (i == a.length) {
				k++;
				for (j = j; j < b.length; j++) {
					c[k] = b[j];
					k++;
				}
			} else if (j == b.length) {
				k++;
				for (i = i; i < a.length; i++) {
					c[k] = a[i];
					k++;
				}

			}
		}

		return c;
	}
//-----------------------------------------------------------------------------------------------
	public static String selectionSortI(Pelicula[] arreglo) {
		for (int i = 0; i < arreglo.length; i++) {
			int min = i;
			for (int j = i + 1; j < arreglo.length; j++) {
				if (arreglo[min].compareTo(arreglo[j]) == -1) {// intercambiar min y j para que ordene de menor a mayor
					min = j;
				}
			}
			Pelicula temp = arreglo[i];
			arreglo[i] = arreglo[min];
			arreglo[min] = temp;
		}
		String ord = Arrays.toString(arreglo);
		return ord;
	}
	
	//-----------------------------------------------------------------------------------------------
		public static String selectionSort(Pelicula[] arreglo) {
			for (int i = 0; i < arreglo.length; i++) {
				int min = i;
				for (int j = i + 1; j < arreglo.length; j++) {
					if (arreglo[j].compareTo(arreglo[min]) == -1) {// intercambiar min y j para que ordene de menor a mayor
						min = j;
					}
				}
				Pelicula temp = arreglo[i];
				arreglo[i] = arreglo[min];
				arreglo[min] = temp;
			}
			String ord = Arrays.toString(arreglo);
			return ord;
		}
	
//--------------------------------------------------------------------------------------------------
	public static  String insertionSort(Pelicula[] arr) {// ordena de menor a mayor

		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1 && arr[i].compareTo(arr[i + 1]) == -1) {
				for (int j = 0; j <= i; j++) {
					if (arr[j].compareTo(arr[i + 1]) == -1) {
						Pelicula aux = arr[i + 1];
						for (int r = i + 1; r > j; r--) {
							arr[r] = arr[r - 1];
						}
						arr[j] = aux;
					}

				}
			}
		}

		String ord = Arrays.toString(arr);
		return ord;
	}
	
//-----------------------------------------------------------------------------------------------------
	public static void quickSort(Pelicula A[], int izq, int der) {// ordena de mayor a menor
		Pelicula pivote = A[izq];
		int i = izq;
		int j = der;
		Pelicula aux;

		while (i < j) {
			while (A[i].compareTo(pivote) >= 0 && i < j) {
				i++;
				while (A[j].compareTo(pivote) == -1) {
					j--;
					if (i < j) {
						aux = A[i];
						A[i] = A[j];
						A[j] = aux;
					}
				}
			}
		}

		A[izq] = A[j];
		A[j] = pivote;

		if (izq < j - 1)
			quickSort(A, izq, j - 1);
		if (j + 1 < der)
			quickSort(A, j + 1, der);
	}

//---------------------------------------------------------------------------------------------------
	public static  Pelicula[] desordena(Pelicula[] datos) {
        int i;
        Pelicula aux;
        int posRand;
        
        Random rand = new Random();
        for(i = datos.length -1; i > 0; i--) {
            posRand = rand.nextInt(i + 1);
            aux = datos[i];
            datos[i] = datos[posRand];
            datos[posRand] = aux;
        }
        
        return datos;
    }
}
