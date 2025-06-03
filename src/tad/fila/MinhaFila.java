package tad.fila;

/**
 * Implementação de uma fila usando array fixo e estratégia circular
 * para gerenciar os apontadores de cabeça e cauda.
 * 
 * @author Artur Oliveira Praxedes
 */
public class MinhaFila implements FilaIF<Integer> {
	
	/** Tamanho máximo da fila (capacidade do array) */
	private int tamanho = 5;
	
	/** Índice para inserção na cauda da fila */
	private int cauda = 0;
	
	/** Índice para remoção na cabeça da fila */
	private int cabeca = 0;
	
	/** Quantidade atual de elementos na fila */
	private int quantidade = 0;
	
	/** Array que armazena os elementos da fila */
	private Integer[] meusDados = null;

	/**
	 * Construtor que inicializa a fila com capacidade especificada.
	 * 
	 * @param tamanhoInicial capacidade inicial da fila
	 */
	public MinhaFila(int tamanhoInicial){
		tamanho = tamanhoInicial;
		this.meusDados = new Integer[tamanho];
	}
	
	/**
	 * Construtor padrão que inicializa a fila com capacidade padrão (5).
	 */
	public MinhaFila(){
		meusDados = new Integer[tamanho];
	}

	/**
	 * Enfileira um novo elemento na cauda da fila.
	 * 
	 * @param item elemento a ser inserido
	 * @throws FilaCheiaException se a fila estiver cheia
	 */
	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		if (isFull()) {
			throw new FilaCheiaException();
		}
		meusDados[cauda] = item;
		cauda = (cauda + 1) % tamanho;
		quantidade++;
	}

	/**
	 * Desenfileira e retorna o elemento na cabeça da fila.
	 * 
	 * @return elemento removido da cabeça da fila
	 * @throws FilaVaziaException se a fila estiver vazia
	 */
	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}
		quantidade--;
		Integer item = meusDados[cabeca];
		cabeca = (cabeca + 1) % tamanho;
		return item;	
	}

	/**
	 * Retorna o elemento na cauda da fila sem removê-lo.
	 * 
	 * @return elemento na cauda, ou null se a fila estiver vazia
	 */
	@Override
	public Integer verificarCauda() {
		if(isEmpty()){ 
			return null;
		}
		int indiceCaudaAnterior = (cauda - 1 + tamanho) % tamanho;
		return meusDados[indiceCaudaAnterior];	
	}

	/**
	 * Retorna o elemento na cabeça da fila sem removê-lo.
	 * 
	 * @return elemento na cabeça, ou null se a fila estiver vazia
	 */
	@Override
	public Integer verificarCabeca() {
		if(isEmpty()){
			return null;
		}
		return meusDados[cabeca];	
	}

	/**
	 * Verifica se a fila está vazia.
	 * 
	 * @return true se vazia, false caso contrário
	 */
	@Override
	public boolean isEmpty() {
		return quantidade == 0;	
	}

	/**
	 * Verifica se a fila está cheia.
	 * 
	 * @return true se cheia, false caso contrário
	 */
	@Override
	public boolean isFull() {
		return quantidade == tamanho;	
	}
	
	/**
	 * Retorna a capacidade máxima da fila.
	 * 
	 * @return capacidade da fila
	 */
	@Override
	public int capacidade() {
	    return tamanho;
	}

	/**
	 * Retorna o número atual de elementos na fila.
	 * 
	 * @return quantidade de elementos na fila
	 */
	@Override
	public int tamanho() {
	    return quantidade;
	}
}