package lista04ex04;

public class FilaVetor<T> implements Fila<T> {
    private T[] info;
    private int limite;
    private int inicio;
    private int tamanho;
    
    public FilaVetor(int limite) {
        this.info = (T[]) new Object[limite];
        this.limite = limite;
        this.tamanho = 0;
        this.inicio = 0;
    }
    
    @Override    
    public void inserir(T v) {
        if (limite == tamanho) {
            throw new RuntimeException("Capacidade esgotada da fila");
        }
        info[((inicio + tamanho) % limite)] = v;
        tamanho++;
    }
    
    @Override    
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < this.tamanho; i++) {
            builder.append(info[(inicio+i)%limite]+", ");
        }
        builder.append("]");
        return builder.toString();
    }
    
    
    public FilaVetor<T> concatenar(FilaVetor<T> f2){
        FilaVetor<T> nova = new FilaVetor<>(limite + f2.limite);
        int i1,i2;
        for (int i = 0; i < tamanho; i++) {
            i1 = (inicio + i) % limite;
            nova.inserir(info[i1]);
        }
        for (int i = 0; i < f2.tamanho; i++) {
            i2 = (f2.inicio + i) % f2.limite;
            nova.inserir(f2.info[i2]);
        }
        return nova;
    }
    
    @Override    
    public T retirar() {
        T v = peek();
        info[inicio] = null;
        inicio = (inicio+1) % limite;
        tamanho--;
        return v;
    }
    
    @Override    
    public T peek() {
        if (estaVazia())
            throw new RuntimeException("Pilha está vazia");
        return info[inicio];
    }
    
    @Override    
    public boolean estaVazia() {
        return (tamanho == 0);
    }
    
    @Override    
    public void liberar() {
        info = (T[]) new Object[limite];
        tamanho = 0;
        inicio = 0;
    }
}