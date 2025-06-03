package tad.pilha;

/**
 * Implementação de uma pilha genérica para inteiros com capacidade limitada.
 * A pilha segue a estrutura LIFO (Last In, First Out).
 * 
 * @author Artur Oliveira Praxedes
 * @version 1.0
 */
public class MinhaPilha implements PilhaIF<Integer> {
	
	/**
	 * Capacidade máxima da pilha.
	 */
	private int tamanho = 5;

	/**
	 * Array que armazena os elementos da pilha.
	 */
	private Integer[] meusDados = null;

	/**
	 * Índice do topo da pilha.
	 * Inicialmente -1, indica pilha vazia.
	 */
	private int topo = -1;

	/**
	 * Constrói uma pilha com capacidade personalizada.
	 * 
	 * @param tamanho capacidade máxima da pilha
	 */
	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		this.meusDados = new Integer[tamanho];
	}
	
	/**
	 * Constrói uma pilha com capacidade padrão (5).
	 */
	public MinhaPilha() {
		this.meusDados = new Integer[tamanho];
	}

	/**
	 * Empilha (adiciona) um elemento no topo da pilha.
	 * 
	 * @param item elemento a ser empilhado
	 * @throws PilhaCheiaException se a pilha estiver cheia
	 */
	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (topo == meusDados.length - 1) {
			throw new PilhaCheiaException();
		} else {
			meusDados[++topo] = item;
		}
	}

	/**
	 * Desempilha (remove) e retorna o elemento do topo da pilha.
	 * 
	 * @return o elemento do topo da pilha
	 * @throws PilhaVaziaException se a pilha estiver vazia
	 */
	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (!isEmpty()) {
			return meusDados[topo--];
		} else {
			throw new PilhaVaziaException();
		}
	}

	/**
	 * Retorna o elemento do topo da pilha sem removê-lo.
	 * 
	 * @return o elemento do topo, ou null se a pilha estiver vazia
	 */
	@Override
	public Integer topo() {
		if (!isEmpty()) {
			return meusDados[topo];
		} else {
			return null;
		}
	}

	/**
	 * Retorna uma nova pilha contendo os top {@code k} elementos da pilha atual.
	 * Se {@code k} for maior que o tamanho atual da pilha, retorna todos os elementos.
	 * 
	 * @param k número de elementos do topo a serem retornados
	 * @return uma nova pilha contendo até {@code k} elementos do topo
	 * @throws IllegalArgumentException se {@code k} for menor ou igual a zero
	 * @throws PilhaVaziaException se a pilha estiver vazia
	 */
	@Override
	public PilhaIF<Integer> multitop(int k) throws PilhaVaziaException {
	    if (k <= 0) {
	        throw new IllegalArgumentException("O parâmetro k precisa ser maior que zero");
	    }

	    if (isEmpty()) {
	        throw new PilhaVaziaException();
	    }

	    int limite = Math.min(k, topo + 1);
	    MinhaPilha novaPilha = new MinhaPilha(limite);

	    for (int i = topo; i > topo - limite; i--) {
	        try {
	            novaPilha.empilhar(meusDados[i]);
	        } catch (PilhaCheiaException e) {
	            e.printStackTrace();
	        }
	    }

	    return novaPilha;
	}
	
	/**
	 * Verifica se a pilha está vazia.
	 * 
	 * @return true se a pilha estiver vazia, false caso contrário
	 */
	@Override
	public boolean isEmpty() {
		return topo == -1;
	}
	
	/**
	 * Retorna a capacidade máxima da pilha.
	 * 
	 * @return a capacidade máxima da pilha
	 */
	@Override
	public int capacidade() {
	    return meusDados.length;
	}

	/**
	 * Retorna o número de elementos atualmente na pilha.
	 * 
	 * @return o tamanho atual da pilha
	 */
	@Override
	public int tamanho() {
	    return topo + 1;
	}

	/**
	 * Compara esta pilha com outro objeto para verificar igualdade.
	 * Duas pilhas são iguais se têm o mesmo tamanho e os mesmos elementos na mesma ordem.
	 * 
	 * @param obj o objeto a ser comparado
	 * @return true se forem iguais, false caso contrário
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		MinhaPilha outra = (MinhaPilha) obj;

		if (this.topo != outra.topo)
			return false;

		for (int i = 0; i <= topo; i++) {
			if (!this.meusDados[i].equals(outra.meusDados[i])) {
				return false;
			}
		}

		return true;
	}
	
}
