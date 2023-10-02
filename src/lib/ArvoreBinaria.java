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

    public ArvoreBinaria(Comparator<T> comp) {
        this.comparador = comp;
    }
    
    

    @Override
    public void adicionar(T novoValor) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.raiz = adicionaRecursivo(this.raiz, novoValor);

    }

    private No<T> adicionaRecursivo(No<T> no,T novoValor){
        if(no==null){ 
            return new No<>(novoValor);
        }

        if(comparador.compare(novoValor, no.getValor()) < 0){
            no.setFilhoEsquerda(adicionaRecursivo(no.getFilhoEsquerda(), novoValor));

        } else if(comparador.compare(novoValor, no.getValor()) > 0){
            no.setFilhoDireita(adicionaRecursivo(no.getFilhoDireita(), novoValor));
        }

        //adicionar quando a comparação for igual a 0
        return no;
    
    }
   

    @Override
    public T pesquisar(T valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remover(T valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public int altura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int altura(No<T> no) {
       
        if (no == null) 
            return -1;
        else {
            int alturaDireita = altura(no.getFilhoDireita());
            int alturaEsquerda = altura((no.getFilhoEsquerda()));
            
            if (alturaDireita > alturaEsquerda) 
                return alturaDireita + 1;    
            else
                return alturaEsquerda + 1;
        }
    }   
    
    @Override
    public int quantidadeNos() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return quantidadeNosRecursiva(this.raiz);
    }

    private int quantidadeNosRecursiva(No no){
        
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
                System.out.println(atual.getValor() + " - " + altura(atual));
                if (atual.getFilhoEsquerda() != null)
                    fila.add(atual.getFilhoEsquerda());
                if (atual.getFilhoDireita() != null){
                    fila.add(atual.getFilhoDireita());
                }                          
            }
        }
    }
    
    @Override
    public String caminharEmOrdem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }
    
    public void caminhaEmOrdem(){
        System.out.println("Saída do Caminhamento em Ordem");
        caminhaEmOrdem(this.raiz);
        System.out.println("Fim da Saída do Caminhamento em Ordem");
    }

    private void caminhaEmOrdem(No<T> raiz) {
        if(raiz != null){
            caminhaEmOrdem(raiz.getFilhoEsquerda());
            System.out.println(raiz.getValor());
            caminhaEmOrdem(raiz.getFilhoDireita());
        }
    }
    
    @Override
    public T obterProximo(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void reiniciarNavegacao(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
} 

