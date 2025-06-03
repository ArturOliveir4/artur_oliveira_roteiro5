package tad.conjuntoDinamico;

import java.util.Arrays;

import tad.ElementoNaoEncontradoException;

/**
 * Implementação dinâmica de um conjunto para armazenar objetos Integer,
 * utilizando array redimensionável.
 * 
 * @author Artur Oliveira Praxedes
 */
public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

    /** Array interno que armazena os dados do conjunto */
    private Integer[] meusDados = new Integer[10]; // array inicial com tamanho 10
    
    /** Índice da próxima posição disponível para inserção */
    private int posInsercao = 0;
    
    /**
     * Construtor padrão que inicializa o conjunto com capacidade inicial 10.
     */
    public MeuConjuntoDinamico() {
        this.meusDados = new Integer[10]; 
        this.posInsercao = 0;
    }

    /**
     * Insere um novo elemento no conjunto.
     * Caso o array esteja cheio, o array é aumentado em tamanho.
     * 
     * @param item o elemento Integer a ser inserido, não pode ser null
     * @throws IllegalArgumentException se o item for null
     */
    @Override
    public void inserir(Integer item) {
        if(item == null) {
            throw new IllegalArgumentException("Elemento não pode ser null");
        }
        if (posInsercao == meusDados.length) {
            meusDados = aumentarArray();
        }
        meusDados[posInsercao] = item;
        posInsercao++;
    }

    /**
     * Dobra o tamanho do array interno e copia os elementos existentes.
     * 
     * @return o novo array com capacidade aumentada
     */
    private Integer[] aumentarArray() {
        return Arrays.copyOf(meusDados, meusDados.length * 2);
    }

    /**
     * Remove um elemento específico do conjunto.
     * 
     * @param item o elemento a ser removido, não pode ser null
     * @return o elemento removido
     * @throws ConjuntoDinamicoVazioException se o conjunto estiver vazio
     * @throws ElementoNaoEncontradoException se o elemento não existir no conjunto
     * @throws IllegalArgumentException se o item for null
     */
    @Override
    public Integer remover(Integer item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
        if (posInsercao == 0) {
            throw new ConjuntoDinamicoVazioException();
        }
        
        if (item == null) {
            throw new IllegalArgumentException("Elemento não pode ser null");
        }

        int index = -1;
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new ElementoNaoEncontradoException();
        }

        Integer removido = meusDados[index];

        for (int i = index; i < posInsercao - 1; i++) {
            meusDados[i] = meusDados[i + 1];
        }

        meusDados[posInsercao - 1] = null; 
        posInsercao--;

        return removido;
    }

    /**
     * Retorna o predecessor do elemento especificado no conjunto.
     * 
     * @param item o elemento cujo predecessor será buscado
     * @return o predecessor do item ou null se não houver predecessor
     * @throws ConjuntoDinamicoVazioException se o conjunto estiver vazio
     */
    @Override
    public Integer predecessor(Integer item) throws ConjuntoDinamicoVazioException {
        if (posInsercao == 0){
            throw new ConjuntoDinamicoVazioException();
        }

        int index = -1;
        for (int i = 0; i < posInsercao; i++){
            if (meusDados[i].equals(item)){
                index = i;
                break;
            }
        }

        if (index == 0){
            return null;
        }

        return meusDados[index-1];
    }

    /**
     * Retorna o sucessor do elemento especificado no conjunto.
     * 
     * @param item o elemento cujo sucessor será buscado
     * @return o sucessor do item ou null se não houver sucessor
     * @throws ConjuntoDinamicoVazioException se o conjunto estiver vazio
     */
    @Override
    public Integer sucessor(Integer item) throws ConjuntoDinamicoVazioException { 
        if (tamanho() == 0) {
            throw new ConjuntoDinamicoVazioException();
        }

        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                if (i + 1 < posInsercao) {
                    return meusDados[i + 1];
                } else {
                    return null; 
                }
            }
        }
        return null; 
    }

    /**
     * Retorna o número de elementos atualmente armazenados no conjunto.
     * 
     * @return o tamanho atual do conjunto
     */
    @Override
    public int tamanho() {
        return posInsercao;
    }

    /**
     * Busca e retorna o elemento especificado no conjunto.
     * 
     * @param item o elemento a ser buscado, não pode ser null
     * @return o elemento encontrado
     * @throws IllegalArgumentException se o item for null
     * @throws ElementoNaoEncontradoException se o elemento não existir no conjunto
     */
    @Override
    public Integer buscar(Integer item) throws IllegalArgumentException, ElementoNaoEncontradoException {
        if (item == null) {
            throw new IllegalArgumentException("Elemento não pode ser null");
        }
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                return meusDados[i];
            }
        }
        throw new ElementoNaoEncontradoException();
    }

    /**
     * Retorna o menor elemento armazenado no conjunto.
     * 
     * @return o menor elemento
     * @throws ConjuntoDinamicoVazioException se o conjunto estiver vazio
     */
    @Override
    public Integer minimum() throws ConjuntoDinamicoVazioException {
        if(posInsercao == 0){
            throw new ConjuntoDinamicoVazioException();
        }
        
        Integer min = meusDados[0];
        for(int i = 1; i < posInsercao; i++){
            if (meusDados[i] < min){
                min = meusDados[i];
            }
        }
        return min;
    }

    /**
     * Retorna o maior elemento armazenado no conjunto.
     * 
     * @return o maior elemento
     * @throws ConjuntoDinamicoVazioException se o conjunto estiver vazio
     */
    @Override
    public Integer maximum() throws ConjuntoDinamicoVazioException {
        if(posInsercao == 0){
            throw new ConjuntoDinamicoVazioException();
        }
        
        Integer max = meusDados[0];
        for(int i = 1; i < posInsercao; i++){
            if(meusDados[i] > max){
                max = meusDados[i];
            }
        }
        return max;
    }
}
