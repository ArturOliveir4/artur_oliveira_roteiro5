package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;

public interface ListaDuplamenteEncadeadaIF<T extends Comparable<T>> extends ListaEncadeadaIF<T>{
	
	public void inserePrimeiro(T elemento);
	public NodoListaDuplamenteEncadeada<T> removeUltimo() throws ListaVaziaException;
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() throws ListaVaziaException;
	public NodoListaDuplamenteEncadeada<T> search(T chave);
	public NodoListaDuplamenteEncadeada<T> remove(T chave) throws ElementoNaoEncontradoException, ListaVaziaException;

}
