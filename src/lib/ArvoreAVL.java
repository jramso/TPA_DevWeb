package lib;

import java.util.Comparator;


/**
 *
 * @author jramso
 * @author fernandajaimara
 * @author maressakaren
 * @author MateusLannes
 * @author Rafael-byte-tech
 * 
 */
public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }

    public void adicionar(T novoValor){
        No <T> novoNo = new No<>(novoValor);
        this.raiz = adicionaRecursivo(this.raiz, novoNo);
    }

    protected No<T> adicionaRecursivo(No<T> no,No<T> novoValor){
        raiz = super.adicionarRecursivo(no, novoValor);
          
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
    public void removerAVL(T valor) {
        No<T> noRemovido = pesquisar(this.raiz,valor);
        if (noRemovido == null) {
            return;
        }

        if (noRemovido.getFilhoEsquerda() == null && noRemovido.getFilhoDireita() == null) {
            // nó folha
            if (noRemovido == this.raiz) {
                this.raiz = null;
            } else {
                if (noRemovido.getPai().getFilhoEsquerda() == noRemovido) {
                    noRemovido.getPai().setFilhoEsquerda(null);
                } else {
                    noRemovido.getPai().setFilhoDireita(null);
                }
            }
        } else if (noRemovido.getFilhoEsquerda() != null && noRemovido.getFilhoDireita() == null) {
            // nó com um filho à esquerda
            noRemovido.getFilhoEsquerda().setPai(noRemovido.getPai());
            if (noRemovido == this.raiz) {
                this.raiz = noRemovido.getFilhoEsquerda();
            }
        } else if (noRemovido.getFilhoEsquerda() == null && noRemovido.getFilhoDireita() != null) {
            // nó com um filho à direita
            noRemovido.getFilhoDireita().setPai(noRemovido.getPai());
            if (noRemovido == this.raiz) {
                this.raiz = noRemovido.getFilhoDireita();
            }
        } else if (noRemovido.getFilhoEsquerda() != null && noRemovido.getFilhoDireita() != null) {
            // nó com dois filhos
            No<T> sucessor = sucessorInorder(noRemovido);
            noRemovido.setValor(sucessor.getValor());
            remover(sucessor.getValor());
        }

        // Atualizar os fatores de balanceamento da árvore
        if (noRemovido.getPai() != null) {
            int fator = fatorDeBalanceamento(noRemovido.getPai());
            // Realize as rotações de balanceamento, se necessário
        }
    }
    private No<T> sucessorInorder(No<T> no) {
        No<T> current = no.getFilhoDireita();
        while (current != null && current.getFilhoEsquerda() != null) {
            current = current.getFilhoEsquerda();
        }
        return current;
    }
}



