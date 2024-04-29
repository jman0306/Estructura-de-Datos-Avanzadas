/* Isabela Valls Chaves
* Mariana Arroyo Mu�oz	
* Juan Manuel Ambriz N��ez 
*/

package Tarea1;

import java.util.*;

public class Ordenamiento {

	// --------------------------------------selectionSort------------------------------------------------------
	public static <T extends Comparable<T>> String selectionSort(T[] arreglo) {// ordena de menor a mayor
		for (int i = 0; i < arreglo.length; i++) {
			int min = i;
			for (int j = i + 1; j < arreglo.length; j++) {
				if (arreglo[j].compareTo(arreglo[min]) == -1) {
					min = j;
				}
			}
			T temp = arreglo[i];
			arreglo[i] = arreglo[min];
			arreglo[min] = temp;
		}
		String ord = Arrays.toString(arreglo);
		return ord;
	}

	// --------------------------------------insertionSort------------------------------------------------------

	public static <T extends Comparable<T>> String insertionSort(T[] arr) {// ordena de menor a mayor

		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1 && arr[i].compareTo(arr[i + 1]) == -1) {
				for (int j = 0; j <= i; j++) {
					if (arr[j].compareTo(arr[i + 1]) == -1) {
						T aux = arr[i + 1];
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

	// --------------------------------------quickSort------------------------------------------------------

	public static <T extends Comparable<T>> void quickSort(T A[], int izq, int der) {// ordena de mayor a menor
		T pivote = A[izq];
		int i = izq;
		int j = der;
		T aux;

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

	// --------------------------------------mergeSort------------------------------------------------------

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
}
