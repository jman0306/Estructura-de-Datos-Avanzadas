package AVL;

public class ArbolAVL<T extends Comparable<T>> {

	NodoAVL<T> raiz;
	int cont;

	public ArbolAVL() {
		raiz = null;
		cont = 0;
	}

	public ArbolAVL(T elem) {
		raiz = new NodoAVL<T>(elem);
		cont = 1;
	}

	public boolean isEmpty() {
		return cont == 0;
	}

	public int size() {
		return cont;
	}

//------------------------------------Buscar-------------------------------------
	public NodoAVL<T> busca(T elem) {
		return busca(this.raiz, elem);
	}

	private NodoAVL<T> busca(NodoAVL<T> arbolito, T elementito) {
		if (arbolito == null)
			return arbolito;
		if (arbolito.getElem().compareTo(elementito) < 0)
			return busca(arbolito.getDer(), elementito);
		if (arbolito.getElem().compareTo(elementito) > 0)
			return busca(arbolito.getIzq(), elementito);
		return arbolito;
	}

//----------------------------------Borrar----------------------------------
	public void eliminar(T dato) {
		raiz = borrar(raiz, dato);
	}

	private NodoAVL<T> borrar(NodoAVL<T> raiz, T dato) {

		if (raiz == null) {
			return raiz;
		}

		if (dato.compareTo(raiz.elem) < 0) {
			raiz.izq = borrar(raiz.izq, dato);
		} else if (dato.compareTo(raiz.elem) > 0) {
			raiz.der = borrar(raiz.der, dato);
		} else {
			if ((raiz.izq == null) || (raiz.der == null)) {
				NodoAVL<T> temp = null;
				if (temp == raiz.izq) {
					temp = raiz.der;
				} else {
					temp = raiz.izq;
				}

				if (temp == null) {
					temp = raiz;
					raiz = null;
				} else {
					raiz = temp;
				}
			} else {

				NodoAVL<T> temp = getNodoMax(raiz.der);

				raiz.elem = temp.elem;

				raiz.der = borrar(raiz.der, temp.elem);
			}
		}

		if (raiz == null) {
			return raiz;
		}

		raiz.h = Math.max(obtenerAltura(raiz.izq), obtenerAltura(raiz.der)) + 1;

		int balance = raiz.getFe();

		if (balance > 1 && (raiz.izq).getFe() >= 0) {
			return rotDer(raiz);
		}

		if (balance > 1 && (raiz.izq).getFe() < 0) {
			raiz.izq = rotIzq(raiz.izq);
			return rotDer(raiz);
		}

		if (balance < -1 && (raiz.der).getFe() <= 0) {
			return rotIzq(raiz);
		}

		if (balance < -1 && (raiz.der).getFe() > 0) {
			raiz.der = rotDer(raiz.der);
			return rotIzq(raiz);
		}

		return raiz;
	}

//----------------------------------Rotar-----------------------------------	

	public NodoAVL<T> rotIzq(NodoAVL<T> c) {
		NodoAVL<T> aux = c.izq;
		c.izq = aux.der;
		aux.der = c;
		c.fe = Math.max((c.izq).fe, (c.der).fe) + 1;
		aux.fe = Math.max((aux.izq).fe, (aux.der).fe) + 1;
		return aux;
	}

	public NodoAVL<T> rotDer(NodoAVL<T> c) {
		NodoAVL<T> aux = c.der;
		c.der = aux.izq;
		aux.izq = c;
		c.fe = Math.max((c.izq).fe, (c.der).fe) + 1;
		aux.fe = Math.max((aux.izq).fe, (aux.der).fe) + 1;
		return aux;
	}

	public NodoAVL<T> rotIzqIzq(NodoAVL<T> c) {
		NodoAVL<T> temp;
		c.izq = rotDer(c.izq);
		temp = rotIzq(c);
		return temp;
	}

	public NodoAVL<T> rotDerDer(NodoAVL<T> c) {
		NodoAVL<T> temp;
		c.der = rotIzq(c.der);
		temp = rotDer(c);
		return temp;
	}

//--------------------------------Insertar---------------------------------------	

	public NodoAVL<T> insertarAVL(NodoAVL<T> n, NodoAVL<T> sa) {
		NodoAVL<T> np = sa;
		if (n.elem.compareTo(sa.elem) < 0) {
			if (sa.izq == null) {
				sa.izq = n;
			} else {
				sa.izq = insertarAVL(n, sa.izq);
				if ((sa.izq).fe - (sa.der).fe == 2) {
					if (n.elem.compareTo(sa.izq.elem) < 0) {
						np = rotIzq(sa);
					} else
						np = rotIzqIzq(sa);
				}
			}
			sa.actualizaFE();
			sa.actualizaAltura();
		} else {
			if (n.elem.compareTo(sa.elem) > 0) {
				if (sa.der == null) {
					sa.der = n;
				} else {
					sa.der = insertarAVL(n, sa.der);
					if ((sa.der).fe - (sa.izq).fe == 2) {
						if (n.elem.compareTo(sa.der.elem) > 0) {
							np = rotDer(sa);
						} else {
							np = rotDerDer(sa);
						}
					}
				}
			} else {
				System.out.println("Nodo Duplicado");
			}
			sa.actualizaFE();
			sa.actualizaAltura();
		}
		return np;
	}

	public void insertar(T d) {
		NodoAVL<T> n = new NodoAVL<T>(d);

		if (raiz == null) {
			raiz = n;
		} else {
			raiz = insertarAVL(n, raiz);
		}

	}

//--------------------------------Recorridos---------------------------------------

	public void inOrden(NodoAVL<T> r) {
		if (r != null) {
			inOrden(r.izq);
			System.out.println(r.elem + ", ");
			inOrden(r.der);
		}
	}

	public void preOrden(NodoAVL<T> r) {
		if (r != null) {
			System.out.println(r.elem + ", ");
			preOrden(r.izq);
			preOrden(r.der);
		}
	}

	public void posOrden(NodoAVL<T> r) {
		if (r != null) {
			posOrden(r.izq);
			posOrden(r.der);
			System.out.println(r.elem + ", ");
		}
	}

//--------------------------------Imprimir---------------------------------------

	public void imprimirPorNivel() {
		imprimirPorNivel(raiz, 1);
		System.out.println();
	}

	private void imprimirPorNivel(NodoAVL<T> temp, int nivel) {
		if (temp != null) {
			imprimirPorNivel(temp.izq, nivel + 1);

			System.out.println("elem: " + temp.elem + "fe: " + temp.fe + "(" + nivel + ") - ");
			imprimirPorNivel(temp.der, nivel + 1);
		}

	}

//-----------------------------NodoElemMax-------------------------------------------
	private NodoAVL getNodoMax(NodoAVL node) {
		NodoAVL actual = node;

		while (actual.der != null) {
			actual = actual.der;
		}

		return actual;
	}

//-----------------------------obtenerAltura------------------------------------------
	public int obtenerAltura(NodoAVL node) {
		int cont = 0;

		if (raiz != null && node != null) {
			NodoAVL auxd = node;
			NodoAVL auxi = node;
			while (auxd.der == null && auxi.izq == null) {
				if (auxd == null || auxi == null) {
					if (auxd == null) {
						auxd = auxd.der;
						cont++;
					} else {
						auxi = auxi.izq;
						cont++;
					}
				} else {
					auxd = auxd.der;
					auxi = auxi.izq;
					cont++;
				}
			}
		}
		return cont;
	}
}
