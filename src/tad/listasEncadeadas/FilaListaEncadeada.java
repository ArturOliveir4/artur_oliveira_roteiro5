package tad.listasEncadeadas;

import tad.fila.FilaCheiaException;
import tad.fila.FilaIF;
import tad.fila.FilaVaziaException;

/**
 * Implementação de uma fila utilizando uma lista encadeada.
 * Os elementos são do tipo NodoListaEncadeada<Integer>.
 * 
 * Esta fila não possui limite fixo de capacidade.
 * 
 * @author Artur Oliveira Praxedes
 */
public class FilaListaEncadeada implements FilaIF<NodoListaEncadeada<Integer>> {
	
	/** Lista encadeada subjacente que armazena os dados da fila */
	private ListaEncadeadaImpl<Integer> fila;

	/**
	 * Construtor que inicializa a fila com uma lista encadeada vazia.
	 */
	public FilaListaEncadeada() {
		this.fila = new ListaEncadeadaImpl<>();
	}

	/**
	 * Enfileira um elemento na fila, inserindo seu valor (chave) na lista.
	 * 
	 * @param item Nodo com o valor a ser enfileirado
	 * @throws FilaCheiaException nunca lançada, pois a fila é dinâmica
	 */
	@Override
	public void enfileirar(NodoListaEncadeada<Integer> item) throws FilaCheiaException {
		fila.insert(item.getChave());
	}

	/**
	 * Desenfileira o elemento da cabeça da fila e o retorna.
	 * 
	 * @return Nodo removido da fila
	 * @throws FilaVaziaException se a fila estiver vazia
	 */
	@Override
	public NodoListaEncadeada<Integer> desenfileirar() throws FilaVaziaException {
		if (fila.isEmpty()) {
			throw new FilaVaziaException();
		}
		NodoListaEncadeada<Integer> anterior = fila.getCabeca();
		NodoListaEncadeada<Integer> primeiro = anterior.getProximo();

		anterior.setProximo(primeiro.getProximo()); 
		return primeiro;
	}

	/**
	 * Verifica e retorna o nodo na cauda da fila.
	 * 
	 * @return nodo na cauda ou null se a fila estiver vazia
	 */
	@Override
	public NodoListaEncadeada<Integer> verificarCauda() {
		NodoListaEncadeada<Integer> atual = fila.getCabeca().getProximo();
		if (atual.isNull()) return null;

		while (!atual.getProximo().isNull()) {
			atual = atual.getProximo();
		}
		return atual;
	}

	/**
	 * Verifica e retorna o nodo na cabeça da fila.
	 * 
	 * @return nodo na cabeça ou null se a fila estiver vazia
	 */
	@Override
	public NodoListaEncadeada<Integer> verificarCabeca() {
		NodoListaEncadeada<Integer> primeiro = fila.getCabeca().getProximo();
		return primeiro.isNull() ? null : primeiro;
	}

	/**
	 * Verifica se a fila está vazia.
	 * 
	 * @return true se estiver vazia, false caso contrário
	 */
	@Override
	public boolean isEmpty() {
		return fila.isEmpty();
	}

	/**
	 * Verifica se a fila está cheia.
	 * Como a fila é dinâmica, esta operação sempre retorna false.
	 * 
	 * @return false
	 */
	@Override
	public boolean isFull() {
		return false; 
	}
	
	/**
	 * Retorna a capacidade teórica máxima da fila.
	 * Como a fila é dinâmica, retorna Integer.MAX_VALUE.
	 * 
	 * @return capacidade máxima da fila
	 */
	@Override
	public int capacidade() {
	    return Integer.MAX_VALUE;
    }

	/**
	 * Retorna o número atual de elementos da fila.
	 * 
	 * @return quantidade de elementos na fila
	 */
	@Override
	public int tamanho() {
		int contador = 0;
	    NodoListaEncadeada<Integer> atual = fila.getCabeca().getProximo(); 

	    while (!atual.isNull()) {
	        contador++;
	        atual = atual.getProximo();
	    }

	    return contador;
	}
}
