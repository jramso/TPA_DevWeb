package lib;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }

    public void adicionar(T novoValor){
        No <T> novoNo = new No<>(novoValor);
        this.raiz = adicionaRecursivo(this.raiz, novoNo);
    }

    protected No<T> adicionaRecursivo(No<T> no,No<T> novoValor){
        raiz = super.adicionaRecursivo(no, novoValor);
          
        if (fatorDeBalanceamento(raiz) > 1) {
            if (fatorDeBalanceamento(raiz.getFilhoDireita()) > 0) {
                raiz = rotacaoEsquerda(raiz);
            } else {
                raiz = rotacaoDireitaEsquerda(raiz);
            }
        } else if (fatorDeBalanceamento(raiz) < -1) {
            if (fatorDeBalanceamento(raiz.getFilhoEsquerda()) < 0) {
                raiz = rotacaoDireita(raiz);
            } else {
                raiz = rotacaoEsquerdaDireita(raiz);
                
            }
        }
    
        return raiz;
    }
    
    private No<T> rotacaoEsquerda(No<T> no) {
        No<T> noAuxiliar = no.getFilhoDireita();
        no.setFilhoDireita(noAuxiliar.getFilhoEsquerda());
        noAuxiliar.setFilhoEsquerda(no);

        return noAuxiliar;
    }

    private No<T> rotacaoEsquerdaDireita(No<T> no) {
        no.setFilhoEsquerda(rotacaoEsquerda(no.getFilhoEsquerda()));
        return rotacaoDireita(no);
    }

    private No<T> rotacaoDireita(No<T> no) {
        No<T> noAuxiliar = no.getFilhoEsquerda();
        no.setFilhoEsquerda(noAuxiliar.getFilhoDireita());
        noAuxiliar.setFilhoDireita(no);

        return noAuxiliar;
    }

    private No<T> rotacaoDireitaEsquerda(No<T> no) {
        no.setFilhoDireita(rotacaoDireita(no.getFilhoDireita()));
        return rotacaoEsquerda(no);
    }


    public int fatorDeBalanceamento(No<T> no) {
        int alturaDireita = alturaRecursivo(no.getFilhoDireita());
        int alturaEsquerda = alturaRecursivo(no.getFilhoEsquerda());
        return alturaDireita - alturaEsquerda;
    }

    // Outros métodos da classe

    // Lembre-se de implementar os métodos de balanceamento

}
