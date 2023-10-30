/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author victoriocarvalho
 * @author jramso
 * @author fernandajaimara
 * @author maressakaren
 * @author MateusLannes
 * @author Rafael-byte-tech
 * 
 */
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {
    
    protected No<T> raiz = null;
    protected Comparator<T> comparador; 
    
    protected No<T> atual = null;
    private ArrayList<No<T>> pilhaNavegacao = null;
    private No<T> proximoNo = null;
    private No<T> pai;


    public ArvoreBinaria(Comparator<T> comp) {
        this.comparador = comp;
        
    }

    @Override
    public void adicionar(T novoValor) {
        No<T> novoNo = new No<>(novoValor);
        No<T> atual = raiz;
        No<T> pai = null;

        while (atual != null) {
            pai = atual;
            if (comparador.compare(novoValor, atual.getValor()) < 0) {
                atual = atual.getFilhoEsquerda();
            } else {
                atual = atual.getFilhoDireita();
            }
        }

        novoNo.setPai(pai);
        if (pai == null) {
            raiz = novoNo;
        } else if (comparador.compare(novoValor, pai.getValor()) < 0) {
            pai.setFilhoEsquerda(novoNo);
        } else {
            pai.setFilhoDireita(novoNo);
        }

        this.proximoNo = encontrarMenorNo(this.raiz);
    }

    protected No<T> adicionarRecursivo(No<T> no, No<T> novoValor) {
        if (no == null) {
            return novoValor;
        }

        if (comparador.compare(novoValor.getValor(), no.getValor()) < 0) {
            no.setFilhoEsquerda(adicionarRecursivo(no.getFilhoEsquerda(), novoValor));
        } else {
            no.setFilhoDireita(adicionarRecursivo(no.getFilhoDireita(), novoValor));
        }

        return no;
    }
   

    @Override
    public T pesquisar(T valor) {
        return pesquisar(raiz,valor).getValor();
    }

    /**
     * Método que pesquisa e retorna um elemento da árvore se existir
     * @param no - sera utilizado para passar a partir de qual Nó sera pesquisado o elemento (normalmente a partir da raiz)
     * @param valor - valor a ser pesquisado nos nós
     * @return No<T> - anteriormente de tipo T (valor dentro do nó) mas para questoes de reuso foi atualizado para o tipo No<T> se precisar do valor utilize um getValor()
     */
    private No<T> pesquisar(No<T> no,T valor){
        if (no == null){
            return null;
        }
        //compara com a "raiz"
        int comparar = comparador.compare(valor, no.getValor());

        
        if (comparar==0){
            //encontrado
            return no;
        
        }else if (comparar<0){
            //busca a esquerda
            this.pai = no;
           return pesquisar(no.getFilhoEsquerda(),valor);
        }else{
            //busca a direita
            this.pai = no;
            return pesquisar(no.getFilhoDireita(),valor);
        }
    }

    @Override
    public T remover(T valor) {
        return removerNo(raiz, valor);
    }

    /**
     * Método que escolhe a forma apropriada para remover o nó da arvore
     * @param no - era utilizado para passar a partir de qual Nó sera pesquisado o elemento para remover (normalmente a partir da raiz)
     * @param valor - o valor que será removido se existir
     * @return T - tipo de valor dos Nós
     */
    private T removerNo(No<T> no, T valor) {

        // Pesquisa o nó a ser removido
        No<T> removido = pesquisar(no, valor);
        if (removido == null) {
            System.out.println("Elemento não encontrado!");
            return null;
        }
    
        // Verifica se o nó a ser removido tem filho
        if (removido.getFilhoDireita() == null || removido.getFilhoEsquerda() == null) {
            // O nó a ser removido tem apenas um filho ou nenhum filho
    
            // Obtém o filho do nó a ser removido
            No<T> filho = removido.getFilhoDireita() != null ? removido.getFilhoDireita() : removido.getFilhoEsquerda();
    
            // Remove o nó a ser removido
            if (removido == raiz) {
                raiz = filho;
            } else {
                // Obtém o this.pai do nó a ser removido
                this.pai = removido.getPai();
    
                // Remove o nó a ser removido do this.pai
                if (comparador.compare(removido.getValor(), this.pai.getValor()) < 0) {
                    this.pai.setFilhoEsquerda(filho);
                } else {
                    this.pai.setFilhoDireita(filho);
                }
            }
    
            // Retorna o valor do nó removido
            return removido.getValor();
        } else {
            // O nó a ser removido tem dois filhos
    
            // Obtém o sucessor do nó a ser removido
            No<T> sucessor = encontrarSucessor(removido);
    
            // Remove o sucessor
            T valorSucessor = removerNo(removido.getFilhoDireita(), sucessor.getValor());
    
            // Troca o valor do nó a ser removido com o valor do sucessor
            removido.setValor(valorSucessor);
    
            // Retorna o valor do nó removido
            return removido.getValor();
        }
    }
    
   
    /**
     * Método que encontra o maior filho de uma árvore ou sub-arvore
     * @param no - a partir de qual elemento é buscado o maior
     * @return No<T> - tipo nó
     */
    private No<T> encontrarMaiorFilhoEsquerda(No<T> no) {
        if (no.getFilhoDireita() == null) {
            return no; // Não há filho à direita, então este é o maior valor à esquerda
        }
        
        return encontrarMaiorFilhoEsquerda(no.getFilhoDireita());
    }
    
    @Override
    public int altura() {
        int alt = alturaRecursivo(this.raiz);
        return alt;
    }

    /**
     * Método que retorna a altura da árvore de maneira Recursiva
     * @param no - a partir de qual elemento é buscado a altura
     * @return int - inteiro contador de níveis
     */
    protected int alturaRecursivo(No<T> no) {
       
        if (no == null) 
            return -1;
        else {
            int alturaDireita = alturaRecursivo(no.getFilhoDireita());
            int alturaEsquerda = alturaRecursivo((no.getFilhoEsquerda()));
            
            if (alturaDireita > alturaEsquerda) 
                return alturaDireita + 1;    
            else
                return alturaEsquerda + 1;
        }
    }   
    
    @Override
    public int quantidadeNos() {
        return quantidadeNosRecursiva(this.raiz);
    }

    /**
     * Método que retorna a quantidade de nós da arvore ou subarvore de maneira recursiva
     * @param no - a partir de qual elemento é contado a quantidade de nós
     * @return int - quantidade de nos
     */
    private int quantidadeNosRecursiva(No<T> no){
        
        if (no == null){
            return 0;
        }
        else{
            return (quantidadeNosRecursiva(no.getFilhoEsquerda()) + quantidadeNosRecursiva(no.getFilhoDireita()) + 1);
        }
    }

    @Override
    public String caminharEmNivel() {
        return "["+caminhaEmNivel()+"]";
    }
    /**
     * Método que preenche uma fila que ordena os elementos por níveis e coloca a saída em uma String
     * @return String
     */
    private String caminhaEmNivel(){
        ArrayList<No<T>> fila = new ArrayList<No<T>>();
        String resultado = "";
        if (this.raiz == null)
            System.out.println("Caminhamento por Nível - Árvore Vazia");
        else{
            No<T> atual;
            fila.add(this.raiz);
            while (fila.size() > 0 ){
                
                atual = fila.get(0);
                resultado += atual.getValor() + "\n";
                fila.remove(0);
                if (atual.getFilhoEsquerda() != null)
                    fila.add(atual.getFilhoEsquerda());
                if (atual.getFilhoDireita() != null){
                    fila.add(atual.getFilhoDireita());
                }                          
            }
        }
        return resultado;
    }
    
    @Override
    public String caminharEmOrdem() {
        return "[" + caminharEmOrdem(this.raiz) + "]";
    }

    /**
     * Método que monta a String de encaminhamento em ordem
     * @param raiz -  a partir de qual elemento e feito o caminho
     * @return String
     */
    private String caminharEmOrdem(No<T> raiz) {
        if (raiz == null) {
            return "";
        }

        String resultado = "";
        resultado += caminharEmOrdem(raiz.getFilhoEsquerda());
        resultado += raiz.getValor() + "\n";
        resultado += caminharEmOrdem(raiz.getFilhoDireita());

        return resultado;
    }

    @Override
    
    public T obterProximo() {
        if (proximoNo == null) {
            return null; // Árvore vazia ou todos os elementos já foram retornados
        }

        T valor = proximoNo.getValor(); //primeira vez pega a do menor no
        proximoNo = encontrarSucessor(proximoNo); // Encontrar o próximo nó na ordem crescente // "avisa" o proximo
        return valor;
    }
    
    @Override
    public void reiniciarNavegacao(){
        this.proximoNo = encontrarMenorNo(this.raiz);
    }
    

    //metodos adicionais

    /**
     *  Método que encontra o menor nó da arvore ou subarvore a partir de um no
     * @param no - no que serve de raiz
     * @return No<T> - tipo No
    */
    private No<T> encontrarMenorNo(No<T> no) {
        //o nó recebido deve ser a raiz ou sera o menor nó a partir daquele "galho"
        No<T> atual = no;
        while (atual != null && atual.getFilhoEsquerda() != null) {
            atual = atual.getFilhoEsquerda();
        }
        return atual;
    }

    /**
     * Método que encontra o próximo nó para o método de obter próximo - encontrando o valor seguinte ao chamado anteriormente
     * @param no - raiz
     * @return No <T> - tipo nó
     */
    private No<T> encontrarSucessor(No<T> no) {
        if (no.getFilhoDireita() != null) {
            // se tiver filho a direita o sucessor é o menor do lado desse filho
            return encontrarMenorNo(no.getFilhoDireita());
        }
    
        No<T> sucessor = null;
        // busca o sucessor a partir da raiz
        while (raiz != null) {
            int comp = comparador.compare(no.getValor(), raiz.getValor());
            if (comp < 0) {
                sucessor = raiz;
                raiz = raiz.getFilhoEsquerda();
            } else if (comp > 0) {
                raiz = raiz.getFilhoDireita();
            } else {
                break; // O nó foi encontrado
            }
        }
    
        return sucessor;
    }

} 

