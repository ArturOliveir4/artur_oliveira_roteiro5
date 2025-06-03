package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;

public interface ConjuntoDinamicoIF<E> {
	
	public void inserir(E item);
	
	public E remover(E item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException;
	
	public E predecessor(E item);
	
	public E sucessor(E item);
	
	public int tamanho();
	
	public E buscar(E item) throws ElementoNaoEncontradoException;
	
	public E minimum();
	
	public E maximum();

}
