package tad.listasEncadeadas;

/**
 * Representa um nodo genérico de uma lista simplesmente encadeada.
 * Cada nodo contém uma chave do tipo genérico {@code T} e referência para o próximo nodo.
 * 
 * @param <T> tipo genérico que estende {@link Comparable}, usado para a chave do nodo.
 */
public class NodoListaEncadeada<T extends Comparable<T>> {
	
	/** Chave associada a este nodo. */
	protected T chave;
	
	/** Referência para o próximo nodo na lista. */
	protected NodoListaEncadeada<T> proximo = null;
	
	/**
	 * Construtor padrão que inicializa a chave e o próximo nodo como null.
	 */
	public NodoListaEncadeada() {
		this.setChave(null);
		this.setProximo(null);
	}
	
	/**
	 * Construtor que inicializa o nodo com a chave fornecida.
	 * 
	 * @param chave valor associado a este nodo.
	 */
	public NodoListaEncadeada(T chave) {
		this.setChave(chave);
		this.setProximo(null);
	}
	
	/**
	 * Construtor que inicializa o nodo com a chave e o próximo nodo fornecidos.
	 * 
	 * @param chave valor associado a este nodo.
	 * @param proximo referência para o próximo nodo na lista.
	 */
	public NodoListaEncadeada(T chave, NodoListaEncadeada<T> proximo) {
		this.setChave(chave);
		this.setProximo(proximo);
	}

	/**
	 * Retorna a chave armazenada neste nodo.
	 * 
	 * @return chave do nodo.
	 */
	public T getChave() {
		return chave;
	}

	/**
	 * Define a chave deste nodo.
	 * 
	 * @param chave valor a ser armazenado neste nodo.
	 */
	public void setChave(T chave) {
		this.chave = chave;
	}

	/**
	 * Retorna o próximo nodo na lista.
	 * 
	 * @return próximo nodo ou null se não existir.
	 */
	public NodoListaEncadeada<T> getProximo() {
		return proximo;
	}

	/**
	 * Define o próximo nodo na lista.
	 * 
	 * @param proximo nodo que será o próximo deste nodo.
	 */
	public void setProximo(NodoListaEncadeada<T> proximo) {
		this.proximo = proximo;
	}
	
	/**
	 * Verifica se a chave deste nodo é nula.
	 * 
	 * @return true se a chave for nula, false caso contrário.
	 */
	public boolean isNull() {
		return (chave == null);
	}

	/**
	 * Compara este nodo com outro objeto para verificar igualdade.
	 * Dois nodos são iguais se suas chaves forem iguais.
	 * 
	 * @param obj objeto a ser comparado.
	 * @return true se os nodos forem iguais, false caso contrário.
	 */
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    NodoListaEncadeada<?> other = (NodoListaEncadeada<?>) obj;
	    return chave != null ? chave.equals(other.chave) : other.chave == null;
	}

	/**
	 * Retorna a representação em String da chave deste nodo.
	 * 
	 * @return representação em String da chave ou null se a chave for nula.
	 */
	@Override
	public String toString() {
		if (!this.isNull())
			return this.chave.toString();
		return null;
	}
	
	/**
	 * Método para obter o nodo anterior — não aplicável para lista simplesmente encadeada,
	 * portanto retorna null. Pode ser sobrescrito em subclasses.
	 * 
	 * @return null, pois nodo anterior não existe em lista simplesmente encadeada.
	 */
	public NodoListaEncadeada<T> getAnterior() {
        return null;
    }
}
