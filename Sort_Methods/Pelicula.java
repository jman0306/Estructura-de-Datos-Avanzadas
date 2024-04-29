 /* Isabela Valls Chaves
 * Mariana Arroyo Mu�oz	
 * Juan Manuel Ambriz N��ez 
 */

package Tarea1;

import java.util.Objects;

public class Pelicula {

	int code;
	int year;
	String nombre;

	public Pelicula(int code, int year, String nombre) {
		this.code = code;
		this.year = year;
		this.nombre = nombre;
	}

	public Pelicula() {
	}

	public int getCode() {
		return code;
	}

	public int getYear() {
		return year;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return code == other.code;
	}

	public int compareTo(Pelicula otra) {
		int resp = 0;

		if (otra.getCode() < code)
			resp = -1;
		else {
			if (otra.getCode() > code)
				resp = 1;
			else
				resp = 0;
		}
		return resp;
	}

	public String toString() {
		String resp = (code + "," + year + "," + nombre);
		return resp;
	}

}
