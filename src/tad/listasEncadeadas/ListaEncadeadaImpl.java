package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;

/**
 * Implementação de uma lista encadeada simples com nó sentinela.
 * 
 * @param <T> tipo genérico que estende Comparable, usado para as chaves dos nós.
 */
public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {
	
	/** Nó sentinela que representa a cabeça da lista. */
	NodoListaEncadeada<T> cabeca = null; 
	
	/** Nó sentinela que representa a cauda da lista. */
	NodoListaEncadeada<T> cauda = null;
	
	/** Tamanho atual da lista. */
	private int tamanho = 0; 
	
	/**
	 * Construtor que inicializa a lista encadeada com nós sentinelas para cabeça e cauda.
	 */
	public ListaEncadeadaImpl() {
		cabeca = new NodoListaEncadeada<T>();
		cauda = new NodoListaEncadeada<T>();
		cabeca.setProximo(cauda);
	}
	

	/**
	 * Verifica se a lista está vazia.
	 * 
	 * @return true se a lista estiver vazia, false caso contrário.
	 */
	@Override
	public boolean isEmpty(){
        return tamanho == 0;	
    }

	/**
	 * Retorna o número de elementos na lista.
	 * 
	 * @return quantidade de elementos presentes na lista.
	 */
	@Override
	public int size(){
        return tamanho;	
    }

	/**
	 * Busca o nó que contém a chave especificada.
	 * 
	 * @param chave chave do elemento a ser buscado.
	 * @return o nodo que contém a chave, ou null se não encontrado.
	 */
	@Override
	public NodoListaEncadeada<T> search(T chave){
	    if(isEmpty()){
	        return null;
	    }

	    NodoListaEncadeada<T> atual = cabeca.getProximo();
	    while(atual != null && atual != cauda){
	        if(chave == null) {
	            if(atual.getChave() == null){
	                return atual;
	            }
	        } else {
	            if(atual.getChave() != null && atual.getChave().compareTo(chave) == 0){
	                return atual;
	            }
	        }
	        atual = atual.getProximo();
	    }
	    return null;
	}


	/**
	 * Insere uma nova chave ao final da lista.
	 * 
	 * @param chave chave do novo elemento a ser inserido.
	 */
	@Override
	public void insert(T chave) {
		NodoListaEncadeada<T> novo = new NodoListaEncadeada<>(chave);
	    NodoListaEncadeada<T> atual = cabeca;

	    while(atual.getProximo() != cauda){
	        atual = atual.getProximo();
	    }

	    novo.setProximo(cauda);
	    atual.setProximo(novo);
	    tamanho++;
		
	}

	/**
	 * Remove o nó que contém a chave especificada.
	 * 
	 * @param chave chave do elemento a ser removido.
	 * @return o nodo removido.
	 * @throws ElementoNaoEncontradoException se a chave não for encontrada na lista.
	 * @throws ListaVaziaException se a lista estiver vazia.
	 */
	@Override
	public NodoListaEncadeada<T> remove(T chave) throws ElementoNaoEncontradoException, ListaVaziaException {
	    if (isEmpty()) {
	        throw new ListaVaziaException();
	    }
	    
	    NodoListaEncadeada<T> anterior = cabeca;
	    NodoListaEncadeada<T> atual = cabeca.getProximo();

	    while (!atual.equals(cauda) && atual.getChave().compareTo(chave) != 0) {
	        anterior = atual;
	        atual = atual.getProximo();
	    }
	    
	    if (atual.equals(cauda)) {
	        throw new ElementoNaoEncontradoException();
	    }
	    
	    anterior.setProximo(atual.getProximo());
	    atual.setProximo(null);
	    tamanho--;
	    return atual;	
	}


	/**
	 * Converte a lista para um array de elementos do tipo T.
	 * 
	 * @param clazz classe do tipo T para criação do array genérico.
	 * @return array contendo os elementos da lista na ordem.
	 */
	@Override
	public T[] toArray(Class<T> clazz) {
	    int tamanho = size();
	    @SuppressWarnings("unchecked")
	    T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, tamanho);

	    NodoListaEncadeada<T> atual = cabeca.getProximo();
	    int i = 0;
	    while (atual != cauda && i < tamanho) {
	        array[i++] = atual.getChave();
	        atual = atual.getProximo();
	    }
	    return array;
	}


	/**
	 * Retorna uma string representando os elementos da lista na ordem (do primeiro ao último).
	 * 
	 * @return string com os elementos separados por vírgula.
	 */
	@Override
	public String imprimeEmOrdem(){
		if(isEmpty()){
			return "";
		} 

        StringBuilder sb = new StringBuilder();
        NodoListaEncadeada<T> atual = cabeca.getProximo();

        while(atual != null && atual != cauda){
            sb.append(atual.getChave());
            if(atual.getProximo() != null && atual.getProximo() != cauda){
                sb.append(", ");
            }
            atual = atual.getProximo();
        }
        
        return sb.toString();
		
	}

	/**
	 * Retorna uma string representando os elementos da lista na ordem inversa (do último ao primeiro).
	 * 
	 * @return string com os elementos separados por vírgula na ordem inversa.
	 */
	@Override
	public String imprimeInverso(){
		if(isEmpty()){
			return "";
		} 

        return imprimeInversoRec(cabeca.getProximo()).toString();
		
	}
	
	/**
	 * Método auxiliar recursivo para construir a string com os elementos na ordem inversa.
	 * 
	 * @param nodo nodo atual da lista.
	 * @return StringBuilder com os elementos na ordem inversa a partir do nodo.
	 */
	private StringBuilder imprimeInversoRec(NodoListaEncadeada<T> nodo){
	    if(nodo == null || nodo == cauda){
	        return new StringBuilder();
	    }
	    StringBuilder sb = imprimeInversoRec(nodo.getProximo());
	    if(sb.length() > 0){
	        sb.append(", ");
	    }
	    sb.append(nodo.getChave());
	    return sb;
	}


	/**
	 * Retorna o sucessor (próximo nodo) do nodo que contém a chave especificada.
	 * 
	 * @param chave chave do elemento que deseja obter o sucessor.
	 * @return nodo sucessor da chave, ou null se for o último elemento.
	 * @throws ElementoNaoEncontradoException se a chave não for encontrada.
	 */
	@Override
	public NodoListaEncadeada<T> sucessor(T chave) throws ElementoNaoEncontradoException {
		NodoListaEncadeada<T> nodo = search(chave);
        if(nodo == null){
            throw new ElementoNaoEncontradoException();        
        } 
        return nodo.getProximo();
    }

	/**
	 * Retorna o predecessor (nodo anterior) do nodo que contém a chave especificada.
	 * 
	 * @param chave chave do elemento que deseja obter o predecessor.
	 * @return nodo predecessor da chave, ou null se for o primeiro elemento.
	 * @throws ElementoNaoEncontradoException se a chave não for encontrada.
	 */
	@Override
	public NodoListaEncadeada<T> predecessor(T chave) throws ElementoNaoEncontradoException {
	    if (isEmpty()) {
	        throw new ElementoNaoEncontradoException();
	    }

	    NodoListaEncadeada<T> anterior = cabeca;
	    NodoListaEncadeada<T> atual = cabeca.getProximo();

	    while (atual != null && atual != cauda) {
	        if (atual.getChave().compareTo(chave) == 0) {
	            if (anterior.equals(cabeca)) {
	                return null; 
	            } else {
	                return anterior;
	            }
	        }
	        anterior = atual;
	        atual = atual.getProximo();
	    }
	    throw new ElementoNaoEncontradoException();
	}


	/**
	 * Insere uma nova chave na lista na posição especificada pelo índice.
	 * 
	 * @param chave chave do novo elemento a ser inserido.
	 * @param index índice da posição onde o elemento deve ser inserido (0-based).
	 * @throws IndexOutOfBoundsException se o índice for inválido.
	 */
	@Override
	public void insert(T chave, int index) {
		if(index < 0 || index > tamanho){
            throw new IndexOutOfBoundsException("Índice fora do intervalo");
        }

        NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<>(chave);
        NodoListaEncadeada<T> atual = cabeca;
        for(int i = 0; i < index; i++){
            atual = atual.getProximo();
        }

        novoNo.setProximo(atual.getProximo());
        atual.setProximo(novoNo);
        tamanho++;
	}
	
	/**
	 * Retorna o nó sentinela da cauda.
	 * 
	 * @return nodo sentinela da cauda.
	 */
	public NodoListaEncadeada<T> getCauda() {
	    return this.cauda;
	}
	
	/**
	 * Retorna o nó sentinela da cabeça.
	 * 
	 * @return nodo sentinela da cabeça.
	 */
	public NodoListaEncadeada<T> getCabeca() {
	    return this.cabeca;
	}
	
}
