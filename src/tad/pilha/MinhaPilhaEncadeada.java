package tad.pilha;

import tad.listasEncadeadas.NodoListaEncadeada;

/**
 * Implementação de uma pilha utilizando estrutura encadeada (lista ligada).
 * Os elementos são armazenados em nós, onde o topo aponta para o último elemento inserido.
 *
 * @author Artur Oliveira Praxedes
 */
public class MinhaPilhaEncadeada implements PilhaIF<Integer> {

    /**
     * Referência para o topo da pilha.
     */
    private NodoListaEncadeada<Integer> cabeca;

    /**
     * Construtor padrão que inicializa a pilha vazia.
     */
    public MinhaPilhaEncadeada() {
        cabeca = null;
    }

    /**
     * Insere um elemento no topo da pilha.
     *
     * @param item o elemento a ser empilhado
     * @throws PilhaCheiaException nunca lançado nesta implementação, pois a pilha é encadeada
     */
    @Override
    public void empilhar(Integer item) throws PilhaCheiaException {
        NodoListaEncadeada<Integer> novoNo = new NodoListaEncadeada<>(item);
        novoNo.setProximo(cabeca);
        cabeca = novoNo;
    }

    /**
     * Remove e retorna o elemento do topo da pilha.
     *
     * @return o elemento removido do topo
     * @throws PilhaVaziaException se a pilha estiver vazia
     */
    @Override
    public Integer desempilhar() throws PilhaVaziaException {
        if (isEmpty()) {
            throw new PilhaVaziaException();
        }
        Integer topo = cabeca.getChave();
        cabeca = cabeca.getProximo();
        return topo;
    }

    /**
     * Retorna o elemento no topo da pilha sem removê-lo.
     *
     * @return o elemento no topo, ou {@code null} se a pilha estiver vazia
     */
    @Override
    public Integer topo() {
        if (isEmpty()) {
            return null;
        }
        return cabeca.getChave();
    }

    /**
     * Retorna uma nova pilha com até {@code k} elementos do topo da pilha atual.
     * 
     * @param k quantidade de elementos a copiar
     * @return nova pilha contendo os {@code k} elementos superiores da pilha atual
     */
    @Override
    public PilhaIF<Integer> multitop(int k) {
        MinhaPilhaEncadeada pilhaAux = new MinhaPilhaEncadeada();
        NodoListaEncadeada<Integer> atual = cabeca;
        int count = 0;
        while (atual != null && count < k) {
            try {
                pilhaAux.empilhar(atual.getChave());
            } catch (PilhaCheiaException e) {
                break;
            }
            atual = atual.getProximo();
            count++;
        }
        return pilhaAux;
    }

    /**
     * Verifica se a pilha está vazia.
     *
     * @return {@code true} se a pilha estiver vazia, {@code false} caso contrário
     */
    @Override
    public boolean isEmpty() {
        return cabeca == null;
    }

    /**
     * Retorna a capacidade da pilha.
     * Como esta é uma pilha encadeada, retorna {@link Integer#MAX_VALUE}.
     *
     * @return capacidade máxima da pilha
     */
    @Override
    public int capacidade() {
        return Integer.MAX_VALUE;
    }

    /**
     * Retorna a quantidade de elementos atualmente na pilha.
     *
     * @return o número de elementos na pilha
     */
    @Override
    public int tamanho() {
        int count = 0;
        NodoListaEncadeada<Integer> atual = cabeca;
        while (atual != null) {
            count++;
            atual = atual.getProximo();
        }
        return count;
    }
}
