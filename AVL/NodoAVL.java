package AVL;

public class NodoAVL<T extends Comparable<T>> {
	T elem;
	NodoAVL<T> izq, der, papa;
	int fe, h;

	NodoAVL(T dato) {
		elem = dato;
		izq = null;
		der = null;
		papa = null;
	}

	public T getElem() {
		return elem;
	}

	public void setElem(T elem) {
		this.elem = elem;
	}

	public NodoAVL<T> getIzq() {
		return izq;
	}

	public void setIzq(NodoAVL<T> izq) {
		this.izq = izq;
	}

	public NodoAVL<T> getDer() {
		return der;
	}

	public void setDer(NodoAVL<T> der) {
		this.der = der;
	}

	public NodoAVL<T> getPapa() {
		return papa;
	}

	public void setPapa(NodoAVL<T> papa) {
		this.papa = papa;
	}

	public int getFe() {
		return fe;
	}

	public void setFe(int fe) {
		this.fe = fe;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void altura() {
		if (izq == null) {
			if (der == null)
				h = 0;
			else
				h = der.h + 1;
		} else {
			if (der == null)
				h = izq.h + 1;
			else
				h = Math.max(izq.h, der.h) + 1;
		}
	}

	public void cuelga(NodoAVL<T> actual) {
		if (actual == null)
			return;
		if (actual.getElem().compareTo(elem) <= 0)
			izq = actual;
		else
			der = actual;
		actual.setPapa(this);
	}

	public void actualizaFE() {
		if (izq == null) {
			if (der == null)
				fe = 0;
			else
				fe = der.h;
		} else {
			if (der == null)
				fe = izq.h;
			else
				fe = der.h - izq.h;
		}
	}

	public void actualizaAltura() {
		if ((izq == null && der != null)) {
			fe = der.fe + 1;
		} else if ((der == null && izq != null)) {
			fe = izq.fe + 1;
		} else {
			fe = Math.max(izq.getFe(), der.getFe()) + 1;
		}
	}

}
