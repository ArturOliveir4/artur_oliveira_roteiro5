package tad.fila;

import tad.listasEncadeadas.NodoListaEncadeada;

/**
 * Implementação de fila encadeada utilizando nodos ligados.
 * A fila possui operações de enfileirar, desenfileirar,
 * verificação dos elementos na cabeça e cauda,
 * além de métodos para verificar tamanho e capacidade.
 * 
 * A fila é dinâmica e não possui limite fixo de capacidade.
 * 
 * @author Artur Oliveira Praxedes
 */
public class MinhaFilaEncadeada implements FilaIF<Integer> {

    /** Nodo que representa a cabeça da fila */
    private NodoListaEncadeada<Integer> cabeca;
    
    /** Nodo que representa a cauda da fila */
    private NodoListaEncadeada<Integer> cauda;

    /**
     * Construtor que inicializa uma fila vazia.
     */
    public MinhaFilaEncadeada() {
        cabeca = null;
        cauda = null;
    }

    /**
     * Insere um item na cauda da fila.
     * 
     * @param item elemento a ser enfileirado
     * @throws FilaCheiaException nunca lançada, pois a fila é dinâmica
     */
    @Override
    public void enfileirar(Integer item) throws FilaCheiaException {
        NodoListaEncadeada<Integer> novoNodo = new NodoListaEncadeada<>(item);

        if (isEmpty()) {
            cabeca = novoNodo;
            cauda = novoNodo;
        } else {
            cauda.setProximo(novoNodo);
            cauda = novoNodo;
        }
    }

    /**
     * Remove e retorna o elemento da cabeça da fila.
     * 
     * @return elemento removido da cabeça da fila
     * @throws FilaVaziaException se a fila estiver vazia
     */
    @Override
    public Integer desenfileirar() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException();
        }

        Integer valor = cabeca.getChave();
        cabeca = cabeca.getProximo();

        if (cabeca == null) {
            cauda = null;
        }

        return valor;
    }

    /**
     * Retorna o elemento na cauda da fila sem removê-lo.
     * 
     * @return elemento na cauda ou null se a fila estiver vazia
     */
    @Override
    public Integer verificarCauda() {
        if(cauda != null){
            return cauda.getChave();
        } else {
            return null;
        }
    }

    /**
     * Retorna o elemento na cabeça da fila sem removê-lo.
     * 
     * @return elemento na cabeça ou null se a fila estiver vazia
     */
    @Override
    public Integer verificarCabeca() {
        if(cabeca != null){
            return cabeca.getChave();
        } else {
            return null;
        }
    }

    /**
     * Verifica se a fila está vazia.
     * 
     * @return true se a fila estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return cabeca == null;
    }

    /**
     * Verifica se a fila está cheia.
     * Como a fila é dinâmica, este método sempre retorna false.
     * 
     * @return false
     */
    @Override
    public boolean isFull() {
        return false; 
    }

    /**
     * Retorna a capacidade da fila, que é ilimitada neste caso.
     * 
     * @return Integer.MAX_VALUE para indicar capacidade teórica infinita
     */
    @Override
    public int capacidade() {
        return Integer.MAX_VALUE;	
    }

    /**
     * Retorna o número de elementos atualmente na fila.
     * 
     * @return tamanho atual da fila
     */
    @Override
    public int tamanho() {
        int contador = 0;
        NodoListaEncadeada<Integer> atual = cabeca;
        while (atual != null) {
            contador++;
            atual = atual.getProximo();
        }
        return contador;
    }
}
