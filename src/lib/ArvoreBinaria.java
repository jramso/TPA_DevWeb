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
 */
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {
    
    protected No<T> raiz = null;
    protected Comparator<T> comparador; 
    
    protected No<T> atual = null;
    private ArrayList<No<T>> pilhaNavegacao = null;
    private No<T> proximoNo = null;


    public ArvoreBinaria(Comparator<T> comp) {
        this.comparador = comp;
        
    }

    @Override
    public void adicionar(T novoValor) {
        No <T> novoNo = new No<>(novoValor);
        this.raiz = adicionaRecursivo(this.raiz, novoNo);
        this.proximoNo = encontrarMenorNo(this.raiz);
    }

    protected No<T> adicionaRecursivo(No<T> no,No<T> novoValor){
        if(no==null){ 
            return novoValor;
        }

        if(comparador.compare(novoValor.getValor(), no.getValor()) < 0){
            no.setFilhoEsquerda(adicionaRecursivo(no.getFilhoEsquerda(), novoValor));

        } else if(comparador.compare(novoValor.getValor(), no.getValor()) > 0){
            no.setFilhoDireita(adicionaRecursivo(no.getFilhoDireita(), novoValor));
        }

        //adicionar quando a comparação for igual a 0
        return no;
    
    }
   

    @Override
    public T pesquisar(T valor) {
        return pesquisar(raiz,valor);
    }

    private T pesquisar(No<T> no,T valor){
        if (no == null){
            return null;
        }
        //compara com a "raiz"
        int comparar = comparador.compare(valor, no.getValor());

        
        if (comparar==0){
            //encontrado
            return no.getValor();
        
        }else if (comparar<0){
            //busca a esquerda
           return pesquisar(no.getFilhoEsquerda(),valor);
        }else{
            //busca a direita
            return pesquisar(no.getFilhoDireita(),valor);
        }
    }

    @Override
    public T remover(T valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public int altura() {
        int alt = alturaRecursivo(this.raiz);
        return alt;
    }

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }

    public void caminhaEmNivel(){
        ArrayList<No<T>> fila = new ArrayList<No<T>>();
        if (this.raiz == null)
            System.out.println("Caminhamento por Nível - Árvore Vazia");
        else{
            System.out.println("Caminhamento por Nível: ");
            No<T> atual;
            fila.add(this.raiz);
            while (fila.size() > 0 ){
                
                atual = fila.get(0);
                fila.remove(0);
                System.out.println(atual.getValor() + " - " + alturaRecursivo(atual));
                if (atual.getFilhoEsquerda() != null)
                    fila.add(atual.getFilhoEsquerda());
                if (atual.getFilhoDireita() != null){
                    fila.add(atual.getFilhoDireita());
                }                          
            }
        }
    }
    
    public String caminharEmOrdem() {
        return "[" + caminharEmOrdem(this.raiz) + "]";
    }

    private String caminharEmOrdem(No<T> raiz) {
        if (raiz == null) {
            return "";
        }

        String resultado = "";
        resultado += caminharEmOrdem(raiz.getFilhoEsquerda());
        resultado += raiz.getValor() + " ";
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        this.atual = this.raiz;

        while (this.atual.getFilhoEsquerda() != null)
        {
            this.atual = this.atual.getFilhoEsquerda();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    //metodos adicionais

    // Método que encontra o menor nó a partir da raiz
    private No<T> encontrarMenorNo(No<T> no) {
        //o nó recebido deve ser a raiz ou sera o menor nó a partir daquele "galho"
        No<T> atual = no;
        while (atual != null && atual.getFilhoEsquerda() != null) {
            atual = atual.getFilhoEsquerda();
        }
        return atual;
    }

    // metodo que encontra o próximo nó para "obterProximo"
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

