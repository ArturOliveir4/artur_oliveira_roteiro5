package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {
	
	private int tamanho = 5;
	private Integer[] meusDados = null;
	private int topo = -1;


	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		this.meusDados = new Integer[tamanho];
	}
	
	public MinhaPilha() {
		this.meusDados = new Integer[tamanho];
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException{
		if(topo == meusDados.length - 1) {
			throw new PilhaCheiaException();
		}else{
			meusDados[++topo] = item;
		}
		
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException{
		if(!isEmpty()){
			return meusDados[topo--];
		}else{
			throw new PilhaVaziaException();
		}
	}

	@Override
	public Integer topo() {
		if(!isEmpty()){
			return meusDados[topo];
		}else {
			return null;
		}
	}

	@Override
	public PilhaIF<Integer> multitop(int k) throws PilhaVaziaException {
	    if(k <= 0){
	        throw new IllegalArgumentException("O parâmetro k precisa ser maior que zero");
	    }

	    if(isEmpty()){
	        throw new PilhaVaziaException();
	    }

	    int limite = Math.min(k, topo + 1);
	    MinhaPilha novaPilha = new MinhaPilha(limite);

	    for(int i = topo; i > topo - limite; i--){
	        try{
	            novaPilha.empilhar(meusDados[i]);
	        }catch (PilhaCheiaException e){
	            e.printStackTrace();
	        }
	    }

	    return novaPilha;
	    
	}
	
	@Override
	public boolean isEmpty() {
		return topo == -1;
	}
	
	@Override
	public int capacidade() {
	    return meusDados.length;
	}

	@Override
	public int tamanho() {
	    return topo + 1;
	}

	
	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}
		if(obj == null || getClass() != obj.getClass()){
			return false;
		}

		MinhaPilha outra = (MinhaPilha) obj;

		if(this.topo != outra.topo) return false;

		for(int i = 0; i <= topo; i++){
			if(!this.meusDados[i].equals(outra.meusDados[i])){
				return false;
			}
		}

		return true;
		
	}
	
}
