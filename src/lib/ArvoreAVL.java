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

    public int fatorDeBalanceamento(){
        return alturaRecursivo(this.raiz.getFilhoDireita()) - alturaRecursivo(this.raiz.getFilhoEsquerda());
    
    //Implementar métodos para efetuar o balanceamento e sobrescrever método de adicionar elemento...
    }
}
