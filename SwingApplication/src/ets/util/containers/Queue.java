package ets.util.containers;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Conteneur impl�mentant une s�mantique FIFO (First In First Out).
 * 
 * @author Martin Desharnais
 */
public class Queue<T> implements Iterable<T> {

	// ////////////////////////////////////////////////
	// Constructeur(s)
	// ////////////////////////////////////////////////

	/**
	 * Construit une file vide.
	 */
	public Queue() {
		array = new Object[1];
		top = -1;
	}

	// ////////////////////////////////////////////////
	// Accesseurs(s)
	// ////////////////////////////////////////////////

	/**
	 * Retourne le nombre d'�l�ments dans la file
	 */
	public int size() {
		return top + 1;
	}

	/**
	 * Retourne si la file est vide ou non.
	 */
	public boolean empty() {
		return size() == 0;
	}

	/**
	 * Retourne l'�l�ment situ� au d�but de la file.
	 */
	public T front() throws Exception {
		if (top == -1)
			throw new Exception();

		return (T) array[0];
	}

	/**
	 * Retourne l'�l�ment situ� � la fin de la file.
	 */
	public T back() throws Exception {
		if (top == -1)
			throw new Exception();

		return (T) array[top];
	}

	/**
	 * Retourne un it�rateur permettant de parcourir la file.
	 */
	public Iterator<T> iterator() {
		return new QueueIterator<T>(array, top);
	}

	// ////////////////////////////////////////////////
	// Mutateur(s)
	// ////////////////////////////////////////////////

	/**
	 * Ajoute un �l�ment � la fin de la file.
	 */
	public void push(T newElement) {
		if (top == array.length - 1) {
			Object[] temp = new Object[array.length * 2];

			for (int i = 0; i < array.length; ++i)
				temp[i] = array[i];

			array = temp;
		}

		array[++top] = newElement;
	}

	/**
	 * Supprime l'�l�ment situ� au d�but de la file.
	 */
	public void pop() throws Exception {
		if (top == -1)
			throw new Exception();

		for (int i = 0; i < top; ++i)
			array[i] = array[i + 1];

		--top;
	}

	// ////////////////////////////////////////////////
	// Attribut(s)
	// ////////////////////////////////////////////////

	private Object[] array;
	private int top;

	/**
	 * It�rateur permettant de parcourir une s�quence.
	 * 
	 * @author Martin Desharnais
	 */
	private class QueueIterator<E> implements Iterator<E> {

		// ////////////////////////////////////////////////
		// Constructeurs(s)
		// ////////////////////////////////////////////////

		/**
		 * Construit un nouvel it�rateur.
		 */
		public QueueIterator(Object[] array, int top) {
			this.array = array;
			this.top = top;
			this.current = -1;
		}

		// ////////////////////////////////////////////////
		// Accesseur(s)
		// ////////////////////////////////////////////////

		/**
		 * Retourne si il y a un �l�ment suivant dans la s�quence.
		 */
		public boolean hasNext() {
			return current != top;
		}

		/**
		 * Retourne l'�l�ment suivant de la s�quence.
		 */
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();

			return (E) array[++current];
		}

		// ////////////////////////////////////////////////
		// Mutateur(s)
		// ////////////////////////////////////////////////

		/**
		 * Supprime l'�l�ment suivant de la s�quence.
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}

		// ////////////////////////////////////////////////
		// Attribut(s)
		// ////////////////////////////////////////////////

		Object[] array;
		int top;
		int current;
	}
}
