package tad.conjuntoDinamico;

/**
 * Classe auxiliar que representa um nodo de uma lista encadeada,
 * armazenando um valor Integer e referência para o próximo nodo.
 * 
 * @author Artur Oliveira Praxedes
 */
class Nodo {
    /** Valor armazenado no nodo */
    Integer chave;

    /** Referência para o próximo nodo na lista */
    Nodo proximo;

    /**
     * Construtor que inicializa o nodo com a chave especificada.
     * 
     * @param chave o valor inteiro armazenado no nodo
     */
    Nodo(Integer chave) {
        this.chave = chave;
        this.proximo = null;
    }
}

/**
 * Implementação encadeada de um conjunto dinâmico para armazenar elementos Integer.
 * Utiliza lista encadeada simples para gerenciar os elementos.
 * 
 * @author Artur Oliveira Praxedes
 */
public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

    /** Referência para o nodo cabeça da lista encadeada */
    private Nodo cabeca;

    /** Quantidade atual de elementos no conjunto */
    private int tamanho;

    /**
     * Construtor padrão que inicializa o conjunto vazio.
     */
    public MeuConjuntoDinamicoEncadeado() {
        this.cabeca = null;
        this.tamanho = 0;
    }

    /**
     * Insere um novo elemento no início da lista.
     * 
     * @param item o elemento a ser inserido
     */
    @Override
    public void inserir(Integer item) {
        Nodo novo = new Nodo(item);
        novo.proximo = cabeca;
        cabeca = novo;
        tamanho++;
    }

    /**
     * Remove o elemento especificado do conjunto.
     * 
     * @param item o elemento a ser removido
     * @return o elemento removido
     * @throws RuntimeException se o elemento não for encontrado
     */
    @Override
    public Integer remover(Integer item) {
        Nodo atual = cabeca, anterior = null;

        while (atual != null) {
            if (atual.chave.equals(item)) {
                if (anterior == null) {
                    cabeca = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                tamanho--;
                return item;
            }
            anterior = atual;
            atual = atual.proximo;
        }

        throw new RuntimeException("Item não encontrado");
    }

    /**
     * Retorna o predecessor do elemento especificado, se existir.
     * 
     * @param item o elemento cujo predecessor será retornado
     * @return o predecessor ou null se não existir
     * @throws RuntimeException se o elemento não for encontrado
     */
    @Override
    public Integer predecessor(Integer item) {
        Nodo atual = cabeca, anterior = null;
        while (atual != null) {
            if (atual.chave.equals(item)) {
                return (anterior != null) ? anterior.chave : null;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        throw new RuntimeException("Item não encontrado");
    }

    /**
     * Retorna o sucessor do elemento especificado, se existir.
     * 
     * @param item o elemento cujo sucessor será retornado
     * @return o sucessor ou null se não existir
     * @throws RuntimeException se o elemento não for encontrado
     */
    @Override
    public Integer sucessor(Integer item) {
        Nodo atual = cabeca;
        while (atual != null && atual.proximo != null) {
            if (atual.chave.equals(item)) {
                return atual.proximo.chave;
            }
            atual = atual.proximo;
        }
        if (atual != null && atual.chave.equals(item)) return null;
        throw new RuntimeException("Item não encontrado");
    }

    /**
     * Retorna o número de elementos armazenados no conjunto.
     * 
     * @return tamanho atual do conjunto
     */
    @Override
    public int tamanho() {
        return tamanho;
    }

    /**
     * Busca e retorna o elemento especificado.
     * 
     * @param item o elemento a ser buscado
     * @return o elemento encontrado
     * @throws RuntimeException se o elemento não for encontrado
     */
    @Override
    public Integer buscar(Integer item) {
        Nodo atual = cabeca;
        while (atual != null) {
            if (atual.chave.equals(item)) {
                return item;
            }
            atual = atual.proximo;
        }
        throw new RuntimeException("Item não encontrado");
    }

    /**
     * Retorna o menor elemento armazenado no conjunto.
     * 
     * @return o menor elemento
     * @throws RuntimeException se o conjunto estiver vazio
     */
    @Override
    public Integer minimum() {
        if (cabeca == null) throw new RuntimeException("Conjunto vazio");

        Integer min = cabeca.chave;
        Nodo atual = cabeca.proximo;
        while (atual != null) {
            if (atual.chave < min) {
                min = atual.chave;
            }
            atual = atual.proximo;
        }
        return min;
    }

    /**
     * Retorna o maior elemento armazenado no conjunto.
     * 
     * @return o maior elemento
     * @throws RuntimeException se o conjunto estiver vazio
     */
    @Override
    public Integer maximum() {
        if (cabeca == null) throw new RuntimeException("Conjunto vazio");

        Integer max = cabeca.chave;
        Nodo atual = cabeca.proximo;
        while (atual != null) {
            if (atual.chave > max) {
                max = atual.chave;
            }
            atual = atual.proximo;
        }
        return max;
    }
}
