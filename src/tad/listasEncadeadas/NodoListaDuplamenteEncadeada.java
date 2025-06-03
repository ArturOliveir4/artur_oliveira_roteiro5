package tad.listasEncadeadas;

/**
 * Representa um nodo de uma lista duplamente encadeada, que contém referência para o nodo anterior e para o próximo.
 * Estende {@link NodoListaEncadeada} adicionando a referência para o nodo anterior.
 * 
 * @param <T> tipo genérico que estende Comparable, usado para a chave do nodo.
 */
public class NodoListaDuplamenteEncadeada<T extends Comparable<T>> extends NodoListaEncadeada<T> {
	
	/** Referência para o nodo anterior na lista. */
	private NodoListaDuplamenteEncadeada<T> anterior;
	
	/**
	 * Construtor que inicializa o nodo com a chave informada.
	 * 
	 * @param chave valor associado a este nodo.
	 */
	public NodoListaDuplamenteEncadeada(T chave) {
		super(chave);
		this.anterior = null;
	}
	
	/**
	 * Construtor padrão que inicializa o nodo com chave nula.
	 */
	public NodoListaDuplamenteEncadeada() {
		super(null);
		this.anterior = null;
	}
	
	/**
	 * Retorna o nodo anterior deste nodo na lista duplamente encadeada.
	 * 
	 * @return nodo anterior, ou null se não houver.
	 */
	public NodoListaDuplamenteEncadeada<T> getAnterior() {
		return anterior;
	}
	
	/**
	 * Define o nodo anterior deste nodo.
	 * 
	 * @param anterior nodo que será definido como anterior.
	 */
	public void setAnterior(NodoListaDuplamenteEncadeada<T> anterior) {
		this.anterior = anterior;
	}
	
	/**
	 * Retorna o próximo nodo na lista, tipado para lista duplamente encadeada.
	 * 
	 * @return próximo nodo da lista duplamente encadeada.
	 */
	@Override
	public NodoListaDuplamenteEncadeada<T> getProximo() {
	    return (NodoListaDuplamenteEncadeada<T>) super.getProximo();
	}

	/**
	 * Define o próximo nodo na lista.
	 * 
	 * @param proximo nodo que será definido como próximo.
	 */
	@Override
	public void setProximo(NodoListaEncadeada<T> proximo) {
	    super.setProximo(proximo);
	}

}
