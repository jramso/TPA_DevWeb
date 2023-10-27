package lib;

import java.util.Comparator;

public class ArvoreAVL <T>extends ArvoreBinaria<T>{
    

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }

    protected No<T> addRecursivo( No<T> raiz, No<T> novo){
        raiz = super.adicionaRecursivo(raiz,novo);
        //Continuar implementando essa


        return raiz;
    }

    @Override
protected No<T> adicionaRecursivo(No<T> atual, No<T> novoValor){
        atual = super.adicionaRecursivo(atual, novoValor);

        int fatorDeBalanceamento = atual.fatorDeBalanceamento();

        if(fatorBalanceamento > 1){
            if((atual.getFilhoDireita().fatorDeBalanceamento() > 0)){
                atual = rotacaoEsquerda(atual); 
            }else{
                atual = rotacaoDireitaEsquerda(atual); 
            }
        } else if(fatorDeBalanceamento < -1){
            if((atual.getFilhoEsquerda().fatorDeBalanceamento() < 0)){
                atual = rotacaoDiretita(atual); 
            }else{
                atual = rotacaoEsquerdaDireita(atual); 
            }
        }

        return atual;
    }

    private No<T> rotacaoEsquerda(No<T> no){
        No <T> noAuxiliar = no.getFilhoDireita();
        no.setFilhoDireita(noAuxiliar.getFilhoEsquerda());
        noAuxiliar.setFilhoEsquerda(no);

        return noAuxiliar;
    }

    private No<T> rotacaoEsquerdaDireita(No<T> no){
        no.setFilhoEsquerda(rotacaoEsquerda(no.getFilhoEsquerda()));
        return rotacaoDireita(no);
    }

    private No<T> rotacaoDireita(No<T> no){
        No <T> noAuxiliar = no.getFilhoEsquerda();
        no.setFilhoEsquerda(noAuxiliar.getFilhoDireita());
        noAuxiliar.setFilhoDireita(no);

        return noAuxiliar;
    }

    private Node<T> rotacaoDireitaEsquerda(No<T> no){
        no.setFilhoDireita(rotacaoDireita(no.getFilhoDireita()));
        return rotacaoEsquerda(no);
    }
    

    public int fatorDeBalanceamento(){
        return alturaRecursivo(this.raiz.getFilhoDireita()) - alturaRecursivo(this.raiz.getFilhoEsquerda());
    
    //Implementar métodos para efetuar o balanceamento e sobrescrever método de adicionar elemento...
    }
}
