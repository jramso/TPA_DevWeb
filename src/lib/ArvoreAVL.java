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
    // A classe ArvoreAVL estende ArvoreBinaria e é parametrizada pelo tipo T.

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
        // O construtor da ArvoreAVL recebe um comparador para definir a ordem dos elementos na árvore.
        // Chama o construtor da classe pai (ArvoreBinaria) para inicializar a árvore com o comparador.
    }

    public void adicionar(T novoValor) {
        // Método para adicionar um novo valor à árvore AVL.
        No<T> novoNo = new No<>(novoValor); // Cria um novo nó com o valor a ser adicionado.
        this.raiz = adicionaRecursivo(this.raiz, novoNo);
        // Chama o método de adição recursiva, passando a raiz atual da árvore e o novo nó.
        // Atualiza a raiz da árvore com o resultado da adição.
    }

    protected No<T> adicionaRecursivo(No<T> no, No<T> novoValor) {
        // Método protegido para adição recursiva de um novo nó à árvore.
        raiz = super.adicionarRecursivo(no, novoValor);
        // Chama o método de adição recursiva da classe pai (ArvoreBinaria) para adicionar o novo nó.
          
        if (fatorDeBalanceamento(raiz) > 1) {
            // Verifica se o fator de balanceamento da raiz é maior que 1 (desequilíbrio à direita).
            if (fatorDeBalanceamento(raiz.getFilhoDireita()) > 0) {
                raiz = rotacaoEsquerda(raiz);
                // Realiza uma rotação à esquerda na raiz.
            } else {
                raiz = rotacaoDireitaEsquerda(raiz);
                // Realiza uma rotação à direita seguida de uma rotação à esquerda na raiz.
            }
        } else if (fatorDeBalanceamento(raiz) < -1) {
            // Verifica se o fator de balanceamento da raiz é menor que -1 (desequilíbrio à esquerda).
            if (fatorDeBalanceamento(raiz.getFilhoEsquerda()) < 0) {
                raiz = rotacaoDireita(raiz);
                // Realiza uma rotação à direita na raiz.
            } else {
                raiz = rotacaoEsquerdaDireita(raiz);
                // Realiza uma rotação à esquerda seguida de uma rotação à direita na raiz.
            }
        }
        // Retorna a raiz atualizada após a adição e as rotações.
        return raiz;
    }
    
    private No<T> rotacaoEsquerda(No<T> no) {
        // Realiza uma rotação simples à esquerda em um nó.
        No<T> noAuxiliar = no.getFilhoDireita();
        no.setFilhoDireita(noAuxiliar.getFilhoEsquerda());
        noAuxiliar.setFilhoEsquerda(no);
        return noAuxiliar; // Retorna o novo nó que se tornará a nova raiz.
    }

    private No<T> rotacaoEsquerdaDireita(No<T> no) {
        // Realiza uma rotação à esquerda seguida de uma rotação à direita em um nó.
        no.setFilhoEsquerda(rotacaoEsquerda(no.getFilhoEsquerda()));
        return rotacaoDireita(no); // Realiza a rotação à direita após a rotação à esquerda.
    }

    private No<T> rotacaoDireita(No<T> no) {
        // Realiza uma rotação simples à direita em um nó.
        No<T> noAuxiliar = no.getFilhoEsquerda();
        no.setFilhoEsquerda(noAuxiliar.getFilhoDireita());
        noAuxiliar.setFilhoDireita(no);
        return noAuxiliar; // Retorna o novo nó que se tornará a nova raiz.
    }

    private No<T> rotacaoDireitaEsquerda(No<T> no) {
        // Realiza uma rotação à direita seguida de uma rotação à esquerda em um nó.
        no.setFilhoDireita(rotacaoDireita(no.getFilhoDireita()));
        return rotacaoEsquerda(no); // Realiza a rotação à esquerda após a rotação à direita.
    }

    public int fatorDeBalanceamento(No<T> no) {
        // Calcula o fator de balanceamento de um nó.
        int alturaDireita = alturaRecursivo(no.getFilhoDireita());
        int alturaEsquerda = alturaRecursivo(no.getFilhoEsquerda());
        return alturaDireita - alturaEsquerda; // Retorna a diferença entre as alturas das subárvores.
    }
}
