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
    private No<T> paiNo;


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
        return pesquisar(raiz,valor).getValor();
    }

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
            this.paiNo = no;
           return pesquisar(no.getFilhoEsquerda(),valor);
        }else{
            //busca a direita
            this.paiNo = no;
            return pesquisar(no.getFilhoDireita(),valor);
        }
    }

    @Override
    public T remover(T valor) {
        return removerNo(raiz, valor);
    }

    private T removerNo(No<T> no, T valor) {
       

        //nó atual não for nulo e diferente do nó a ser removido
        No<T> removido = pesquisar(no, valor);
        if (removido==null){
            System.out.println("Elemento nao encontrado!");
            return null;
        }else{

            //se o no a ser removido nao tiver filhos
            if (removido.getFilhoDireita()==null && removido.getFilhoEsquerda() == null){
                if(paiNo!=null){
                    //se o no a ser removido for o da esquerda, o Pai recebe filho a esquerda como null
                    if(comparador.compare(removido.getValor(), paiNo.getValor()) < 0){
                        paiNo.setFilhoEsquerda(null);
                    }else{
                        paiNo.setFilhoDireita(null);
                    }
                }else{
                    //no a ser removido é o pai/raiz
                    raiz.setValor(null);
                }
            
            }else{
                // entao tem filho
                //tem filho so a esquerda
                if(removido.getFilhoDireita()==null){
                    //no pai fica com filho
                    //criado pelo avo
                    paiNo.setFilhoEsquerda(removido.getFilhoEsquerda());
                    removido.setFilhoEsquerda(null);
                //tem filho so a direita
                }
                if(removido.getFilhoEsquerda()==null){
                    //no pai fica com filho
                    //criado pelo avo
                    paiNo.setFilhoDireita(removido.getFilhoDireita());
                    removido.setFilhoDireita(null);
                }
                if(removido.getFilhoEsquerda()!=null && removido.getFilhoDireita()!=null){
                    //tem os dois filhos
                    //pega o maior filho a esquerda dele
                    No<T> maiorNo = encontrarMaiorFilhoEsquerda(removido.getFilhoEsquerda());
                    //troca o valor dos nós
                    T valorNo = removido.getValor();
                    removido.setValor(maiorNo.getValor());
                    if (removido!=raiz){
                        
                        if (removido.getFilhoEsquerda()==maiorNo){
                            removido.setFilhoEsquerda(null);
                        }
                        
                    }else{
                        raiz.setValor(maiorNo.getValor());
                        maiorNo=null;
                        
                    }


                    return valorNo;


                }
            }
        }

        return removido.getValor();
    }
   
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
        return "["+caminhaEmNivel()+"]";
    }

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
    
    public String caminharEmOrdem() {
        return "[" + caminharEmOrdem(this.raiz) + "]";
    }

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

